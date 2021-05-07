package de.patrick260.spawpointPlugin.main;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    //Main-singelton
    private static Main plugin;

    private ConsoleCommandSender console = Bukkit.getConsoleSender();

    private String plugin_prefix = "§7[§2SpawnpointPlugin§7] §f";

    //A text that display when you try to start the plugin as a Java programm.
    public static void main(String[] args) {

        System.out.println("This is a Minecraft 1.8.8 plugin! Not a Java programm!");

    }


    //Things that happens when the server load the plugin.
    public void onEnable() {



    }

    //Things that happens when the server disable the plugin.
    public void onDisable() {

    }


    //A Methode to get the main-singelton.
    public static Main getPlugin() {

        return plugin;

    }

}
