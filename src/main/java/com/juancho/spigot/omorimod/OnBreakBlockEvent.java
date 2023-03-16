package com.juancho.spigot.omorimod;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.juancho.spigot.omorimod.utils.Entity;
import com.juancho.spigot.omorimod.utils.emotions.Emotion;
import com.juancho.spigot.omorimod.utils.emotions.EmotionFactory;

public class OnBreakBlockEvent implements Listener { 
    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Entity entity = new Entity(player);
        // Choose random from list of emotions
        // ArrayList<Emotion> emotions = EmotionFactory.getEmotions();
        // Random random = new Random();
        // Emotion emotion = emotions.get(random.nextInt(emotions.size()));
        // entity.updateEmotion(emotion);

    }
}
