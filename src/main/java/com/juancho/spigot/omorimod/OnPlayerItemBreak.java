package com.juancho.spigot.omorimod;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;

import com.juancho.spigot.omorimod.utils.Entity;
import com.juancho.spigot.omorimod.utils.emotions.Angry;
import com.juancho.spigot.omorimod.utils.emotions.Emotion;
import com.juancho.spigot.omorimod.utils.emotions.EmotionFactory;
import com.juancho.spigot.omorimod.utils.emotions.Sad;

public class OnPlayerItemBreak implements Listener {

    @EventHandler
    public void onPlayerItemBreak(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        Entity entity = new Entity(player);
        
        double angryRate = 0.7;
        double sadRate = 0.05;

        Random r = new Random();
        double roll = r.nextDouble();
        
        if (roll <= angryRate) {
            entity.updateEmotion(new Angry());
        }
        if (roll > angryRate && roll <= angryRate+sadRate) {
            entity.updateEmotion(new Sad());
        }

        
    }

    
}
