package com.juancho.spigot.omorimod.utils.emotions;

public abstract class Emotion {
    public abstract String formatedNameString();
    public abstract String formatedDescriptionString();
    public abstract String rawName();
    public abstract double multiplierWhenAttackedBy(Angry e);
    public abstract double multiplierWhenAttackedBy(Happy e);
    public abstract double multiplierWhenAttackedBy(Neutral e);
    public abstract double multiplierWhenAttackedBy(Sad e);
    public abstract double getMultiplierAttack(Emotion e);
    
    public boolean shouldMiss() {
        return false;
    }

    public boolean shouldCrit() {
        return false;
    }

    public double knockbackAddition() {
        return 0;}
    
}
