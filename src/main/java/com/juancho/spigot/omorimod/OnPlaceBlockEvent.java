package com.juancho.spigot.omorimod;

import java.util.Collection;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.juancho.spigot.omorimod.utils.Entity;
import com.juancho.spigot.omorimod.utils.emotions.Angry;
import com.juancho.spigot.omorimod.utils.emotions.Emotion;
import com.juancho.spigot.omorimod.utils.emotions.Happy;
import com.juancho.spigot.omorimod.utils.emotions.Sad;

public class OnPlaceBlockEvent implements Listener {

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {

        Block placedBlock = event.getBlock();
        Player player = event.getPlayer();
        Entity entity = new Entity(player); 
        Emotion broadcastEmotion = null;
        
        Material material = placedBlock.getType();
        if (material == Material.CORNFLOWER) {
            broadcastEmotion = new Sad();
        }
        if (material == Material.DANDELION || material == Material.SUNFLOWER) {
            broadcastEmotion = new Happy();
        }
        if (material == Material.POPPY || material == Material.ROSE_BUSH || material == Material.RED_TULIP) {
            broadcastEmotion = new Angry();
        }

        if (broadcastEmotion != null) {
            Collection<org.bukkit.entity.Entity> broadcasting = placedBlock.getWorld().getNearbyEntities(placedBlock.getLocation(), 8, 8, 8);
            for (org.bukkit.entity.Entity e : broadcasting) {
                Entity omori_entity = new Entity(e);
                omori_entity.updateEmotion(broadcastEmotion);   
            }
        }

    }
    
}
