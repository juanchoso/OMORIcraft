package com.juancho.spigot.omorimod;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

import com.juancho.spigot.omorimod.utils.Entity;
import com.juancho.spigot.omorimod.utils.HUD;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Entity entity = new Entity(player);
        player.sendMessage(ChatColor.BLUE + "¡Bienvenido a NonsenseCraft!");
        player.sendMessage(ChatColor.YELLOW + "Estamos jugando actualmente la temporada " + ChatColor.AQUA + Integer.toString(App.season) + ChatColor.YELLOW + ".");

        // Enviarle el MOTD
        DBManager db = new DBManager();
        String motd = db.getMOTD();
        db.getPlayer(player.getName());
        db.close();
        player.sendMessage(ChatColor.YELLOW + "[Servidor] " + ChatColor.AQUA + motd);


        // Asignarle su scoreboard
        player.setScoreboard(App.getScoreboard(player.getName()));
        HUD hud = new HUD(player);
        App.huds.add(hud);
        hud.update();



    }
}
