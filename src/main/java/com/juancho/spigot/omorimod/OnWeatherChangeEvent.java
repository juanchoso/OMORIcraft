package com.juancho.spigot.omorimod;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.juancho.spigot.omorimod.utils.emotions.Sad;

public class OnWeatherChangeEvent implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        // check if raining
        if (event.toWeatherState()) {
            for (Entity entity : event.getWorld().getEntities()) {
                double sadRoll = 0.7;

                Random r = new Random();
                double roll = r.nextDouble();
                
                if (roll <= sadRoll) {
                    com.juancho.spigot.omorimod.utils.Entity omori_entity = new com.juancho.spigot.omorimod.utils.Entity(entity);
                    omori_entity.updateEmotion(new Sad());
                }                

            }
        }
    }
}
