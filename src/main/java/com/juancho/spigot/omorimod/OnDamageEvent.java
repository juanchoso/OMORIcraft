package com.juancho.spigot.omorimod;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.juancho.spigot.omorimod.utils.Entity;
import com.juancho.spigot.omorimod.utils.emotions.Angry;
import com.juancho.spigot.omorimod.utils.emotions.Emotion;
import com.juancho.spigot.omorimod.utils.emotions.EmotionFactory;
import com.juancho.spigot.omorimod.utils.emotions.Happy;
import com.juancho.spigot.omorimod.utils.emotions.Sad;

;

public class OnDamageEvent implements Listener {

    Plugin plugin;

    public void broadcastEmotion(org.bukkit.entity.Entity entity) {
        // Emotion aura will be broadcasted to players in a 10 block radius
        // We'll iterate through all players to see the ones who are in range
        for (Player player : entity.getWorld().getPlayers()) {
            App a = (App) plugin;
            GlowingAPI gAPI = a.getGlowingAPI();
            if (player.getLocation().distance(entity.getLocation()) <= 10) {
                gAPI.sendGlowing(entity, player);
            }
        }
    }

    public OnDamageEvent(Plugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        org.bukkit.entity.Entity entity = event.getEntity();
        Entity omori_entity = new Entity(entity);

        if (entity instanceof LivingEntity) {
            LivingEntity le = (LivingEntity) entity;
            double maxhp = le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

            if (event.getFinalDamage() >= maxhp * 0.33) {
                double angryRate = 0.6;
                Random r = new Random();
                double roll = r.nextDouble();
                if (roll <= angryRate) {
                    omori_entity.updateEmotion(new Angry());
                }
            }
        }

        if (event.getCause().equals(DamageCause.DROWNING)) {
            double sadRate = 0.9;
            Random r = new Random();
            double roll = r.nextDouble();
            if (roll <= sadRate) {
                omori_entity.updateEmotion(new Sad());
            }
        }


    }


    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        org.bukkit.entity.Entity damager = event.getDamager();
        org.bukkit.entity.Entity damaged = event.getEntity();

        Entity damager_entity = new Entity(damager);
        Entity damaged_entity = new Entity(damaged);

        if (damager_entity.getOwner() != null) {
            Entity real_damager = damager_entity.getOwner();
            damager = real_damager.getEntity();
            damager_entity = real_damager;
        }
        

        if (damager instanceof Player) {
            App a = (App) plugin;
            GlowingAPI gAPI = a.getGlowingAPI();
            gAPI.sendGlowing(damaged, (Player) damager);
        }
        if (damaged instanceof Player) {
            App a = (App) plugin;
            GlowingAPI gAPI = a.getGlowingAPI();
            gAPI.sendGlowing(damager, (Player) damaged);
        }

        broadcastEmotion(damaged);


        Emotion damager_emotion = damager_entity.getEmotion();
        Emotion damaged_emotion = damaged_entity.getEmotion();

        double damager_emotion_multiplier = damager_emotion.getMultiplierAttack(damaged_emotion);

        double dmg = event.getDamage();

        if (damager_emotion instanceof Angry) {
            dmg += 2.2;
        }
        if (damaged_emotion instanceof Angry) {
            dmg += 1.5;
        }

        dmg = dmg * damager_emotion_multiplier;
        boolean crit = damager_emotion.shouldCrit();
        boolean miss = damager_emotion.shouldMiss();

        if (damager instanceof Creeper) {
            if (damager_emotion instanceof Happy) {
                crit = true;
                miss = false;
            }
        }

        if (miss == false && (damager_emotion_multiplier > 1 || crit)) {
            if (damager instanceof Player) {
                Player p = (Player) damager;
                p.playSound(p.getLocation(), "custom:omori.sound.critical", SoundCategory.MASTER, 3, 1);
            }
            if (damaged instanceof Player) {
                Player p = (Player) damaged;
                p.playSound(p.getLocation(), "custom:omori.sound.critical", SoundCategory.MASTER, 3, 1);
            }
        }

        if (miss) {
            if (damager instanceof Player) {
                Player p = (Player) damager;
                p.playSound(p.getLocation(), "custom:omori.sound.miss", 3, 1);
            }
            if (damaged instanceof Player) {
                Player p = (Player) damaged;
                p.playSound(p.getLocation(), "custom:omori.sound.miss", 3, 1);
            }
        }

        double kb_power = damager_emotion.knockbackAddition();
        

        if (crit && miss == false) {
            dmg *= 2;
            kb_power *= 1.7;
            Vector kb_dir = damager.getLocation().toVector().subtract(damaged.getLocation().toVector()).setY(0).normalize();
            damaged.setVelocity(damaged.getVelocity().add(kb_dir.multiply(-kb_power)));
        }
        ;
        if (miss) {
            dmg = 0;
            event.setCancelled(true);
        }
        ;

        // Tanking system on SADNESS and HUNGER
        if (damaged_emotion instanceof Sad) {

            if (damaged instanceof Player) {
                Player p = (Player) damaged;

                int food = p.getFoodLevel();
                if (dmg > 0 && food > 0) {
                    if (food - 1 > 0) {
                        p.setFoodLevel(food - 1);
                    } else {
                        p.setFoodLevel(0);
                    }
                    ;
                    dmg -= 2.5;
                    if (dmg < 0) {
                        dmg = 0;
                    }
                    ;
                }

            }
        }

        event.setDamage(dmg);

        // Messages
        if (damager instanceof Player) {
            Player p = (Player) damager;
            if (miss) {
                damager.sendMessage(ChatColor.ITALIC + "" + ChatColor.GRAY + "* Tu ataque falló!");
            } else {
                if (crit) {
                    damager.sendMessage(ChatColor.ITALIC + "" + ChatColor.GRAY + "* Tu ataque fue crítico!");
                }
            }
        }
        ;

        if (damaged instanceof Player) {
            if (miss) {
                damaged.sendMessage(ChatColor.ITALIC + "" + ChatColor.GRAY + "* El ataque de tu rival falló!");
            } else {
                if (crit) {
                    damaged.sendMessage(ChatColor.ITALIC + "" + ChatColor.GRAY + "* Recibiste un golpe crítico!");
                }
            }
        }
        ;

    }
}