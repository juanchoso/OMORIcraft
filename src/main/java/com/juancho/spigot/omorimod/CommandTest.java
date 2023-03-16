package com.juancho.spigot.omorimod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import com.juancho.spigot.omorimod.utils.Party;


public class CommandTest implements CommandExecutor {

    Plugin plugin;

    CommandTest(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        Player sender = (Player) arg0;

        // le mostraremos los teeams
        String text = "";
        App a = (App) plugin;
        
        Team happyTeam = a.getHappyTeam();
        Team angryTeam = a.getAngryTeam();
        Team sadTeam = a.getSadTeam();
        Team neutralTeam = a.getNeutralTeam();
        
        text += "Happy: " + happyTeam.getEntries().toString() + " | ";
        text += "Angry: " + angryTeam.getEntries().toString() + " | ";
        text += "Sad: " + sadTeam.getEntries().toString() + " | ";
        text += "Neutral: " + neutralTeam.getEntries().toString() + " | ";

        sender.sendMessage(text);
        return true;
    }

}
