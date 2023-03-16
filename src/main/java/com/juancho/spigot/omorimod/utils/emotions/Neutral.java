package com.juancho.spigot.omorimod.utils.emotions;

public class Neutral extends Emotion {

    @Override
    public String rawName() {
        return "NEUTRAL";
    }

    @Override
    public String formatedDescriptionString() {
        return "Estás neutral. No tienes efectos adicionales.";
    }

    @Override
    public String formatedNameString() {
        return "NEUTRAL";
    }

    @Override
    public double multiplierWhenAttackedBy(Angry e) {
        return 1;
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
        return 1;
    }

    @Override
    public double getMultiplierAttack(Emotion e) {
        return e.multiplierWhenAttackedBy(this);
    }
    
}
