package com.juancho.spigot.omorimod;

import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.juancho.spigot.omorimod.utils.HUD;
import com.juancho.spigot.omorimod.utils.Party;

import org.bukkit.ChatColor;

public class App extends JavaPlugin {

    public static GlowingAPI glowingAPI;
    public static ProtocolManager protocolManager;
    public static String version_string = "0.1beta";
    public static int season = 1;
    public static ArrayList<Party> parties = new ArrayList<Party>();
    public static HashMap<String, Scoreboard> scoreboards = new HashMap<String, Scoreboard>();
    public static ScoreboardManager sbmanager;
    public static Plugin plugin;
    public static ArrayList<HUD> huds = new ArrayList<HUD>();
    public Scoreboard emotionScoreboard;
    public Team happyTeam;
    public Team angryTeam;
    public Team sadTeam;
    public Team neutralTeam;

    public static Plugin getPlugin() {
        return plugin;
    }

    public Team getHappyTeam() {
        return happyTeam;
    }

    public Team getAngryTeam() {
        return angryTeam;
    }

    public Team getSadTeam() {
        return sadTeam;
    }

    public Team getNeutralTeam() {
        return neutralTeam;
    }

    public GlowingAPI getGlowingAPI() {
        return glowingAPI;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public Entity getEntity(int ID) {
        for (Entity entity : this.getServer().getWorld("world").getEntities()) {
            if (entity.getEntityId() == ID) {
                return entity;
            }
        }
        return null;
    }

    public static void updateHud(String nameString) {
        for (HUD hud : huds) {
            if (hud.getName().equals(nameString)) {
                hud.update();
            }
        }
    }

    //region SCOREBOARDS
    public static Scoreboard getScoreboard(String player) {
        if (scoreboards.containsKey(player)) {
            return scoreboards.get(player);
        }
        else {
            // create one
            Scoreboard sb = sbmanager.getNewScoreboard();
            Objective o = sb.registerNewObjective(player, "dummy", player);
            Team happy = sb.registerNewTeam("HAPPY");
            Team angry = sb.registerNewTeam("ANGRY");
            Team sad = sb.registerNewTeam("SAD");
            Team neutral = sb.registerNewTeam("NEUTRAL");
            happy.setColor(ChatColor.YELLOW);
            angry.setColor(ChatColor.RED);
            sad.setColor(ChatColor.BLUE);
            neutral.setColor(ChatColor.WHITE);
            o.setDisplaySlot(DisplaySlot.SIDEBAR);
            // Score spacer = o.getScore(ChatColor.BLUE + "   ");
            // spacer.setScore(10);
            // Score emotion = o.getScore(ChatColor.BLUE + "SAD");
            // emotion.setScore(9);
            // Score level = o.getScore(ChatColor.GREEN + "Lv. " + ChatColor.GOLD + "3");
            // level.setScore(8);
            scoreboards.put(player, sb);
            return sb;
        }
    }

    //endregion 


    //region PARTY MANAGEMENT
    public static boolean isPlayerInParty(String player) {
        for (Party party : parties) {
            if (party.isPlayerInParty(player)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPlayerInParty(Player player) {
        for (Party party : parties) {
            if (party.isPlayerInParty(player.getName())) {
                return true;
            }
        }
        return false;
    }
    public static Party getParty(String player) {
        for (Party party : parties) {
            if (party.isPlayerInParty(player)) {
                return party;
            }
        }
        return null;
    }
    //endregion

    @Override
    public void onEnable() {
        // Bukkit.getPluginManager().registerEvents(, null);
        glowingAPI = new GlowingAPI(this);
        protocolManager = ProtocolLibrary.getProtocolManager();
        // protocolManager.addPacketListener(
        //     new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.ENTITY_METADATA) {
        //         @Override
        //         public void onPacketSending(com.comphenix.protocol.events.PacketEvent event) {
        //             if (event.getPacketType() == PacketType.Play.Server.ENTITY_METADATA) {
        //                 // Get the packet first (it's a wrapper)
        //                 WrappedWatchableObject watchable = event.getPacket().getWatchableCollectionModifier().read(0).get(0);

        //                 // Get the entity
        //                 int entityID = event.getPacket().getIntegers().read(0);
        //                 App p = (App) plugin;
        //                 Entity e = p.getEntity(entityID);
                        
        //                 // Get the person receiving the packet
        //                 Player player = event.getPlayer();

        //                 if (e == null) {
        //                     return;
        //                 }
        //                 double dist = player.getLocation().distance(e.getLocation());

        //                 // Change glow to 1 
        //                 if (watchable.getIndex() == 0 && dist < 10) {
        //                     byte b = (byte) watchable.getValue();
        //                     b |= (byte) 0x44;
        //                     watchable.setValue(b);
        //                 }

                        
        //             }
        //         }
        //     }

        // );
        sbmanager = Bukkit.getScoreboardManager();



        emotionScoreboard = sbmanager.getNewScoreboard();
        happyTeam = emotionScoreboard.registerNewTeam("happy");
        happyTeam.setColor(ChatColor.YELLOW);
        happyTeam.setPrefix("[HAPPY]");
        sadTeam = emotionScoreboard.registerNewTeam("sad");
        sadTeam.setColor(ChatColor.BLUE);
        sadTeam.setPrefix("[SAD]");
        angryTeam = emotionScoreboard.registerNewTeam("angry");
        angryTeam.setColor(ChatColor.RED);
        angryTeam.setPrefix("[ANGRY]");
        neutralTeam = emotionScoreboard.registerNewTeam("neutral");
        neutralTeam.setColor(ChatColor.WHITE);
        neutralTeam.setPrefix("[NEUTRAL]");


        plugin = this;
        getLogger().info("Hello, SpigotMC!");
        registerEvents();
        this.getCommand("createparty").setExecutor(new CommandPartyCreate());
        this.getCommand("leave").setExecutor(new CommandPartyLeave());
        this.getCommand("test").setExecutor(new CommandTest(this));

    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new OnBreakBlockEvent(), this);
        pm.registerEvents(new OnDamageEvent(this), this);
        pm.registerEvents(new OnEntitySpawnEvent(), this);
        pm.registerEvents(new OnEntityExplodeEvent(), this);
        pm.registerEvents(new OnPlaceBlockEvent(), this);
        pm.registerEvents(new OnWeatherChangeEvent(), this);
        pm.registerEvents(new OnFoodLevelChangeEvent(), this);
        pm.registerEvents(new OnItemPickUpEvent(), this);
        pm.registerEvents(new OnPlayerItemBreak(), this);
        pm.registerEvents(new OnEntityDeathEvent(), this);
        pm.registerEvents(new OnAdvancementDoneEvent(), this);
        pm.registerEvents(new OnPlayerItemConsume(), this);
        


    }

    public Scoreboard getEmotionScoreboard() {
        return emotionScoreboard;
    }
}

