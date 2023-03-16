package com.juancho.spigot.omorimod.utils.emotions;

import net.md_5.bungee.api.ChatColor;

public class Angry extends Emotion {

    @Override
    public String rawName() {
        return "ANGRY";
    }

    @Override
    public String formatedNameString() {
        return ChatColor.RED + "ANGRY";
    }

    @Override
    public String formatedDescriptionString() {
        return "Estás enfadado. Tus ataques son más fuertes pero también tu defensa es más débil.";
    }

    @Override
    public double multiplierWhenAttackedBy(Angry e) {
        return 1;
    }

    @Override
    public double multiplierWhenAttackedBy(Happy e) {
        return 1.5;
    }

    @Override
    public double multiplierWhenAttackedBy(Neutral e) {
        return 1;
    }

    @Override
    public double multiplierWhenAttackedBy(Sad e) {
        return 0.5;
    }

    @Override
    public double getMultiplierAttack(Emotion e) {
        return e.multiplierWhenAttackedBy(this);
    }

    
    

    
}
