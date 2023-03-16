package com.juancho.spigot.omorimod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.juancho.spigot.omorimod.utils.Party;

import net.md_5.bungee.api.ChatColor;


public class CommandPartyLeave implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        // check if player is in party
        // if not, create party
        // if yes, send message
        Player sender = (Player) arg0;
        boolean isInParty = App.isPlayerInParty(sender.getName());


        if (isInParty) {
            Party party = App.getParty(sender.getName());
            party.removePlayer(sender);
            arg0.sendMessage(ChatColor.GREEN + "¡Has abandonado la party!");
            return false;
        } else {
            arg0.sendMessage(ChatColor.RED + "¡No te encuentras en una party!");
            return false;
            
        }
    }

}
