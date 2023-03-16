package com.juancho.spigot.omorimod.utils.emotions;

import java.util.ArrayList;

public class EmotionFactory {
    
    public static Emotion getEmotion(String emotionName) {
        String lowerCaseEmotionName = emotionName.toLowerCase();
        switch (lowerCaseEmotionName) {
            case "angry":
                return new Angry();
            case "happy":
                return new Happy();
            case "sad":
                return new Sad();
            case "neutral":
                return new Neutral();
            default:
                return null;
        }
    }

    public static ArrayList<Emotion> getEmotions() {
        ArrayList<Emotion> emotions = new ArrayList<Emotion>();
        emotions.add(new Angry());
        emotions.add(new Happy());
        emotions.add(new Sad());
        emotions.add(new Neutral());
        return emotions;
    }

}
