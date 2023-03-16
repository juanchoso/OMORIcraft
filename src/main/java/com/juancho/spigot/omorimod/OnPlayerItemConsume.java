package com.juancho.spigot.omorimod;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.juancho.spigot.omorimod.utils.emotions.Angry;
import com.juancho.spigot.omorimod.utils.emotions.Sad;

public class OnPlayerItemConsume implements Listener {

    ArrayList<Material> cooked_foods = new ArrayList<Material>();
    ArrayList<Material> sugar_foods = new ArrayList<Material>();
    ArrayList<Material> raw_foods = new ArrayList<Material>();


    public OnPlayerItemConsume() {
        // Cookeds
        cooked_foods.add(Material.COOKED_BEEF);
        cooked_foods.add(Material.COOKED_CHICKEN);
        cooked_foods.add(Material.COOKED_COD);
        cooked_foods.add(Material.COOKED_MUTTON);
        cooked_foods.add(Material.COOKED_PORKCHOP);
        cooked_foods.add(Material.COOKED_RABBIT);
        cooked_foods.add(Material.COOKED_SALMON);

        // Sugars
        sugar_foods.add(Material.COOKIE);

        // Raw foods
        raw_foods.add(Material.BEEF);
        raw_foods.add(Material.CHICKEN);
        raw_foods.add(Material.COD);
        raw_foods.add(Material.MUTTON);
        raw_foods.add(Material.PORKCHOP);
        raw_foods.add(Material.RABBIT);
        raw_foods.add(Material.SALMON);


    }



    @EventHandler
    public void onPlayerItemConsume(org.bukkit.event.player.PlayerItemConsumeEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        com.juancho.spigot.omorimod.utils.Entity entity = new com.juancho.spigot.omorimod.utils.Entity(player);
        
        // What did the player consume
        org.bukkit.inventory.ItemStack item = event.getItem();
        org.bukkit.Material material = item.getType();

        double happyRoll = 0;
        double angryRoll = 0;
        double sadRoll = 0;

        entity.ateFood(material);
        int fstreak = entity.getFoodStreak();
        sadRoll = (double) 0.03*fstreak;

        // if it's cooked beef, cooked chicken, cooked cod, cooked mutton
        if (cooked_foods.contains(material)) {
            happyRoll = 0.6;
        }

        if (sugar_foods.contains(material)) {
            happyRoll = 0.8;
        }

        if (raw_foods.contains(material)) {
            angryRoll = 0.95;
        }

        if (happyRoll > 0) {
            Random r = new Random();
            double roll = r.nextDouble();
            if (roll < happyRoll) {
                com.juancho.spigot.omorimod.utils.emotions.Happy happy = new com.juancho.spigot.omorimod.utils.emotions.Happy();
                entity.updateEmotion(happy);
            }
        }

        if (angryRoll > 0) {
            Random r = new Random();
            double roll = r.nextDouble();
            if (roll < angryRoll) {
                Angry angry = new com.juancho.spigot.omorimod.utils.emotions.Angry();
                entity.updateEmotion(angry);
            }
        }

        if (sadRoll > 0) {
            Random r = new Random();
            double roll = r.nextDouble();
            if (roll < sadRoll) {
                Sad sad = new com.juancho.spigot.omorimod.utils.emotions.Sad();
                entity.updateEmotion(sad);
            }
        }



    }
    
}
