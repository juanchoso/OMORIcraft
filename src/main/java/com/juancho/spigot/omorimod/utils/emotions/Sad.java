package com.juancho.spigot.omorimod.utils.emotions;

import net.md_5.bungee.api.ChatColor;

public class Sad extends Emotion {

    @Override
    public String rawName() {
        return "SAD";
    }

    @Override
    public String formatedNameString() {
        return ChatColor.BLUE + "SAD";
    }

    @Override
    public String formatedDescriptionString() {
        return ChatColor.GRAY + "Estás triste.  Al ser atacado por un enemigo parte del daño que recibas será atenuado a cambio de perder un poco del medidor de hambre.";
    }

    @Override
    public double multiplierWhenAttackedBy(Angry e) {
        return 1.5;
    }

    @Override
    public double multiplierWhenAttackedBy(Happy e) {
        return 0.5;
    }

    @Override
    public double multiplierWhenAttackedBy(Neutral e) {
        return 1;
    }

    @Override
    public double multiplierWhenAttackedBy(Sad e) {
        return 1;
    }
    
    @Override
    public double getMultiplierAttack(Emotion e) {
        return e.multiplierWhenAttackedBy(this);
    }
    
    
}
