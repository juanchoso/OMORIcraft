package com.juancho.spigot.omorimod.utils;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.juancho.spigot.omorimod.App;

public class HUD {
    Player player; // jugador al que le corresponde el HUD
    String name;
    ArrayList<String> lines = new ArrayList<String>();

    public HUD(Player player) {
        this.player = player;
        this.name = player.getName();
    }

    public String getName() {
        return name;
    }

    public void update() {

        Player _player = Bukkit.getPlayer(name);
        Entity entity = new Entity(_player);
        lines.clear();
        lines.add(" "); // spacer
        lines.add(ChatColor.BOLD + entity.getEmotion().formatedNameString()); // emotion
        lines.add("  "); // spacer

        Scoreboard sb = App.getScoreboard(name);
        Objective o = sb.getObjective(name);
        o.unregister();
        o = sb.registerNewObjective(name, "dummy", ChatColor.BOLD + "Tu estado");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (int i = 0; i < lines.size(); i++) {
            o.getScore(lines.get(i)).setScore(lines.size() - i);
        }
        // printear a consola spigot que se actualizó
        // System.out.println("Se actualizó el HUD de " + player.getName());



    }

    public org.bukkit.entity.Entity getPlayer() {
        return player;
    }

}
