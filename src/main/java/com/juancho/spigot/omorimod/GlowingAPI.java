package com.juancho.spigot.omorimod;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;
import com.juancho.spigot.omorimod.utils.Entity;

public class GlowingAPI {

    Plugin plugin;
    App app;

    public GlowingAPI(Plugin plugin) {
        this.plugin = plugin;
        this.app = (App) plugin;
    }

    public void sendGlowing(org.bukkit.entity.Entity entity, Player player) {

        Entity ent = new Entity(entity);
        String str = ent.getEmotion().rawName();
        String teamString = entity.getUniqueId().toString();
        if (entity instanceof Player) {
            teamString = entity.getName();
        }
        // get HUD of the player
        Scoreboard sb = App.getScoreboard(player.getName());

        // remove entity from all teams
        ArrayList<String> emotionStrings = new ArrayList<String>(Arrays.asList("HAPPY","SAD","ANGRY","NEUTRAL"));

        for (String emotionString : emotionStrings) {
            try {
                sb.getTeam(emotionString).removeEntry(teamString);
            } catch (Exception e) {
                e.printStackTrace();
                app.getLogger().info("Error removing entity from teams");
            }
        }

        // add entity to the team of the emotion
        for (String emotionString : emotionStrings) {
            if (str.equals(emotionString)) {
                try {
                    sb.getTeam(emotionString).addEntry(teamString);
                } catch (Exception e) {
                    e.printStackTrace();
                    app.getLogger().info("Error adding entity to team");
                }
            }
        }


        
        


        int entityID = entity.getEntityId();
        ProtocolManager pm = app.getProtocolManager();
        PacketContainer packet = pm.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        
        // Escribir primero la ID del entity
        packet.getIntegers().write(0, entityID); // Entity ID
        WrappedDataWatcher watcher = new WrappedDataWatcher(); // Crear un nuevo watcher
        // El watcher sirve para guardar los datos de un entity
        // Para guardar un dato, se necesita un serializer
        Serializer serializer = Registry.get(Byte.class); // El serializer es un byte
        watcher.setEntity(entity); // Setear el entity al watcher
        watcher.setObject(0, serializer, (byte) (0x40)); // Setear el dato al watcher
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects()); // Escribir el watcher en el packet
               
        try {
            pm.sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendGlowing(org.bukkit.entity.Entity entity, Player player, int radius) {

        Entity ent = new Entity(entity);
        String str = ent.getEmotion().rawName();
        String teamString = entity.getUniqueId().toString();
        if (entity instanceof Player) {
            teamString = entity.getName();
        }
        // get HUD of the player
        Scoreboard sb = App.getScoreboard(player.getName());

        // remove entity from all teams
        ArrayList<String> emotionStrings = new ArrayList<String>(Arrays.asList("HAPPY","SAD","ANGRY","NEUTRAL"));

        for (String emotionString : emotionStrings) {
            try {
                sb.getTeam(emotionString).removeEntry(teamString);
            } catch (Exception e) {
                e.printStackTrace();
                app.getLogger().info("Error removing entity from teams");
            }
        }

        // add entity to the team of the emotion
        for (String emotionString : emotionStrings) {
            if (str.equals(emotionString)) {
                try {
                    sb.getTeam(emotionString).addEntry(teamString);
                } catch (Exception e) {
                    e.printStackTrace();
                    app.getLogger().info("Error adding entity to team");
                }
            }
        }


        
        

        if (player.getLocation().distance(entity.getLocation()) < radius) {
            int entityID = entity.getEntityId();
            ProtocolManager pm = app.getProtocolManager();
            PacketContainer packet = pm.createPacket(PacketType.Play.Server.ENTITY_METADATA);
            
            // Escribir primero la ID del entity
            packet.getIntegers().write(0, entityID); // Entity ID
            WrappedDataWatcher watcher = new WrappedDataWatcher(); // Crear un nuevo watcher
            // El watcher sirve para guardar los datos de un entity
            // Para guardar un dato, se necesita un serializer
            Serializer serializer = Registry.get(Byte.class); // El serializer es un byte
            watcher.setEntity(entity); // Setear el entity al watcher
            watcher.setObject(0, serializer, (byte) (0x40)); // Setear el dato al watcher
            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects()); // Escribir el watcher en el packet
                
            try {
                pm.sendServerPacket(player, packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    
}
