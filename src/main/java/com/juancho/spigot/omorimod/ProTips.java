package com.juancho.spigot.omorimod;

import java.util.ArrayList;

import org.bukkit.ChatColor;

public class ProTips {
    ArrayList<String> tips = new ArrayList<String>();

    public ProTips() {
        tips.add(ChatColor.AQUA + "Si estás entre muchos enemigos que pueden atacarte a distancia, intenta utilizar el estado TRISTE para aumentar tu defensa.");
        tips.add(ChatColor.AQUA + "Si vas a utilizar un arco, prueba a utilizar el estado ENOJADO para aumentar tu daño sin correr el riesgo del daño extra que puedes recibir al estar a distancia.");
        tips.add(ChatColor.AQUA + "Si tienes la ventaja en la batalla utiliza el estado FELIZ para obtener más fuerza de empuje y daño crítico.");
        tips.add(ChatColor.AQUA + "Si tu estrategia se basa en estar TRISTE, intenta llevar contigo buenas cantidades de un mismo tipo de comida para poder recargar tu barra de hambre y mantenerte triste.");
        tips.add(ChatColor.AQUA + "Debes estar atento a los cambios de estado que puedes tener durante una batalla ya que esto puede afectar a tu estrategia.");
        tips.add(ChatColor.AQUA + "Ten cuidado con ciertos enemigos ya que su emoción les puede otorgar efectos adicionales. Por ejemplo, los Creeper pueden explotar con distinta intensidad o cambiar el estado de ánimo de las entidades cercanas.");
        tips.add(ChatColor.AQUA + "Si estás contra varios enemigos que tienen distintas emociones intenta enfocarte en derrotar primero a los que tengan un estado débil contra el tuyo.");
        tips.add(ChatColor.AQUA + "Recuerda que " + ChatColor.YELLOW + "FELIZ " + ChatColor.AQUA + "le gana a " + ChatColor.RED + "ENOJADO " + ChatColor.AQUA +".");
        tips.add(ChatColor.AQUA + "Recuerda que " + ChatColor.RED + "ENOJADO " + ChatColor.AQUA + "le gana a " + ChatColor.BLUE + "TRISTE " + ChatColor.AQUA +".");
        tips.add(ChatColor.AQUA + "Recuerda que " + ChatColor.BLUE + "TRISTE " + ChatColor.AQUA + "le gana a " + ChatColor.YELLOW + "FELIZ " + ChatColor.AQUA +".");
    }

    public String getRandomTip() {
        int index = (int) (Math.random() * tips.size());
        return tips.get(index);
    }


}
