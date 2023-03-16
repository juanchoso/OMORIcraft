package com.juancho.spigot.omorimod.utils.emotions;

import java.util.Random;

import net.md_5.bungee.api.ChatColor;

public class Happy extends Emotion {

    @Override
    public String rawName() {
        return "HAPPY";
    }

    @Override
    public String formatedDescriptionString() {
        return ChatColor.GRAY + "Estás feliz. Tus ataques tienen alta probabilidad de dar golpes críticos pero también alta probabilidad de fallar.";
    }

    @Override
    public String formatedNameString() {
        return ChatColor.YELLOW + "HAPPY";
    }

    @Override
    public double multiplierWhenAttackedBy(Angry e) {
        return 0.5;
    }

    @Override
    public double multiplierWhenAttackedBy(Happy e) {
        return 1;
    }

    @Override
    public double multiplierWhenAttackedBy(Neutral e) {
        return 1;
    }

    @Override
    public double multiplierWhenAttackedBy(Sad e) {
        return 1.5;
    }

    @Override
    public double getMultiplierAttack(Emotion e) {
        return e.multiplierWhenAttackedBy(this);
    }

    @Override
    public boolean shouldCrit() {
        Random r = new Random();
        double odds = 0.3;
        return r.nextDouble() < odds;
    }

    @Override
    public boolean shouldMiss() {
        Random r = new Random();
        double odds = 0.2;
        return r.nextDouble() < odds;
    }   

    @Override
    public double knockbackAddition() {
        return 1.75;
    }
    

    
}
