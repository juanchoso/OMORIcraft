package com.juancho.spigot.omorimod.utils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import com.comphenix.net.bytebuddy.build.Plugin;
import com.juancho.spigot.omorimod.App;
import com.juancho.spigot.omorimod.GlowingAPI;
import com.juancho.spigot.omorimod.utils.emotions.Emotion;
import com.juancho.spigot.omorimod.utils.emotions.EmotionFactory;

public class Entity {
    Entity owner = null;
    org.bukkit.entity.Entity entity;
    Emotion emotion;
    int level;
    int experience;
    int foodStreak;
    int lastFood;

    public Entity(org.bukkit.entity.Entity entity) {
        this.entity = entity;
        this.level = getInteger("level", 1);
        this.emotion = EmotionFactory.getEmotion(getString("emotion", "NEUTRAL"));
        this.foodStreak = getInteger("foodStreak", 0);
        this.lastFood = getInteger("lastFood", 0);

        if (entity instanceof org.bukkit.entity.Player) {
            // nothing yet
        } else {

            int ownerID = getInteger("ownerID", -1);
            if (ownerID != -1) {
                for( org.bukkit.entity.Entity e : entity.getWorld().getEntities()) {
                    if (e.getEntityId() == ownerID) {
                        this.owner = new Entity(e);
                    }
                }
            }

            String value = getString("emotion", "NONE");
            if (value == "NONE" ){
                Random random = new Random();
                ArrayList<Emotion> emotions = EmotionFactory.getEmotions();
                Emotion selectedEmotion = emotions.get(random.nextInt(emotions.size()));
                this.emotion = selectedEmotion;
                setString("emotion", selectedEmotion.rawName());
            }
        }

    }

    public void ateFood(Material item) {
        int foodID = item.ordinal();
        if (foodID == lastFood) {
            foodStreak++;
            setInteger("foodStreak", foodStreak);
        } else {
            foodStreak = 0;
            lastFood = foodID;
            setInteger("foodStreak", foodStreak);
            setInteger("lastFood", lastFood);
        }
    }

    public int getFoodStreak() {
        return foodStreak;
    }


    public org.bukkit.entity.Entity getEntity() {
        return entity;
    }

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
        setInteger("ownerID", owner.getEntity().getEntityId());
    }

    public int getInteger(String key, int defaultValue) {
        if (entity.getPersistentDataContainer().has(new NamespacedKey(App.plugin, key), PersistentDataType.INTEGER)) {
            return entity.getPersistentDataContainer().get(new NamespacedKey(App.plugin, key), PersistentDataType.INTEGER);
        } else {
            return defaultValue;
        }
    }

    public void setInteger(String key, int value) {
        entity.getPersistentDataContainer().set(new NamespacedKey(App.plugin, key), PersistentDataType.INTEGER, value);
    }

    public String getString(String key, String defaultValue) {
        if (entity.getPersistentDataContainer().has(new NamespacedKey(App.plugin, key), PersistentDataType.STRING)) {
            return entity.getPersistentDataContainer().get(new NamespacedKey(App.plugin, key), PersistentDataType.STRING);
        } else {
            return defaultValue;
        }
    }

    public void setString(String key, String value) {
        entity.getPersistentDataContainer().set(new NamespacedKey(App.plugin, key), PersistentDataType.STRING, value);
    }

    public Emotion getEmotion() {
        if (emotion == null) {
            return EmotionFactory.getEmotion(getString("emotion", "NEUTRAL"));
        }
        return emotion;
    }

    public Emotion updateEmotion(Emotion emotion) {
        this.emotion = emotion;
        setString("emotion", emotion.rawName());
        if (entity instanceof org.bukkit.entity.Player) {
            App.updateHud(entity.getName());
        }
        broadcastEmotion();
        return emotion;
    }

    public void broadcastEmotion() {
        for (Player player : entity.getWorld().getPlayers()) {
            App a = (App) App.getPlugin();
            GlowingAPI gAPI = a.getGlowingAPI();
            gAPI.sendGlowing(entity, player, 10);
        }
    }

    public void broadcastEmotion(int radius) {
        for (Player player : entity.getWorld().getPlayers()) {
            App a = (App) App.getPlugin();
            GlowingAPI gAPI = a.getGlowingAPI();
            gAPI.sendGlowing(entity, player, radius);
        }
    }

}
