package com.juancho.spigot.omorimod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.juancho.spigot.omorimod.utils.Party;

import net.md_5.bungee.api.ChatColor;


public class CommandPartyCreate implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        // check if player is in party
        // if not, create party
        // if yes, send message
        Player sender = (Player) arg0;
        
        boolean isInParty = App.isPlayerInParty(sender.getName());


        if (isInParty) {
            arg0.sendMessage(ChatColor.RED + "¡Ya te encuentras en una party!");
            return false;
        } else {
            Party party = new Party();
            party.addPlayer(sender);
            App.parties.add(party);
            arg0.sendMessage(ChatColor.GREEN + "¡Has creado la Party con éxito!");
            return true;
        }
    }

}
