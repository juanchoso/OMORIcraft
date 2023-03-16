package com.juancho.spigot.omorimod;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnItemPickUpEvent implements Listener {

    HashMap<Material, Double> itemHappyRate = new HashMap<Material, Double>();

    

    public OnItemPickUpEvent() {
        // Ingots
        itemHappyRate.put(Material.COAL, 0.05);
        itemHappyRate.put(Material.IRON_ORE, 0.3);
        itemHappyRate.put(Material.GOLD_ORE, 0.35);
        itemHappyRate.put(Material.EMERALD, 0.4);
        itemHappyRate.put(Material.DIAMOND, 0.6);
        itemHappyRate.put(Material.NETHERITE_INGOT, 0.8);

    }



    @EventHandler
    public void onItemPickUp(org.bukkit.event.entity.EntityPickupItemEvent event) {
        org.bukkit.entity.Entity entity = event.getEntity();
        if (entity instanceof org.bukkit.entity.Player) {
            Item item = event.getItem();
            Material itemType = item.getItemStack().getType();
            if (itemHappyRate.containsKey(itemType)) {
                double happyRate = itemHappyRate.get(itemType);
                Random r = new Random();
                double roll = r.nextDouble();
                if (roll <= happyRate) {
                    com.juancho.spigot.omorimod.utils.Entity omori_entity = new com.juancho.spigot.omorimod.utils.Entity(entity);
                    omori_entity.updateEmotion(new com.juancho.spigot.omorimod.utils.emotions.Happy());
                }
            }
        }
    }
}
