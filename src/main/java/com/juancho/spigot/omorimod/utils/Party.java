package com.juancho.spigot.omorimod.utils;

import java.nio.channels.MembershipKey;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Party {
    ArrayList<Player> players = new ArrayList<Player>();

    public boolean isPlayerInParty(String player) {
        for (Player p : players) {
            if (p.getName().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
}
