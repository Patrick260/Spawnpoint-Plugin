package de.patrick260.spawpointPlugin.main;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    //Main-singelton
    private static Main plugin;

    private ConsoleCommandSender console = Bukkit.getConsoleSender();
    private FileConfiguration config = getConfig();

    private String plugin_prefix;

    //A text that display when you try to start the plugin as a Java programm.
    public static void main(String[] args) {

        System.out.println("This is a Minecraft 1.8.8 plugin! Not a Java programm!");

    }


    //Things that happens when the server load the plugin.
    public void onEnable() {

        setupDefaultConfig();
        console.sendMessage("[SpawnpointPlugin] config.yml wurde erfolgreich geladen!");

        plugin_prefix = getPrefix();
        console.sendMessage(plugin_prefix + "§aDer Plugin Prefix wurde erfolgreich aus der Config geladen!");

        registerCommands();
        registerListener();

        console.sendMessage(plugin_prefix + "§aDas Plugin wurde erfolgreich aktiviert!");

    }

    //Things that happens when the server disable the plugin.
    public void onDisable() {

        //empty

    }


    //Register the commands of the plugin.
    private void registerCommands() {

    }

    //Register the listeners of the plugin.
    private void registerListener() {

    }

    //Setup the default config file
    private void setupDefaultConfig() {

        config.addDefault("plugin.prefix", "§7[§2SpawnpointPlugin§7] §f");

        config.addDefault("permissions.commands.admin.setspawnpoint", "spawnpointplugin.commands.admin.setspawnpoint");

        config.addDefault("data.spawnpoint.world", Bukkit.getWorlds().get(0));
        config.addDefault("data.spawnpoint.x", 0);
        config.addDefault("data.spawnpoint.y", 63);
        config.addDefault("data.spawnpoint.z", 0);
        config.addDefault("data.spawnpoint.yaw", 0);
        config.addDefault("data.spawnpoint.pitch", 0);

        config.options().copyDefaults(true);

        saveConfig();

    }


    //Getter and Setter

    //A Methode to get the main-singelton.
    public static Main getPlugin() {

        return plugin;

    }

    //Returns the plugin prefix
    public String getPrefix() {

        return config.getString("plugin.prefix");

    }

    //Returns the ConsoleCommandSender
    public ConsoleCommandSender getConsole() {

        return console;

    }

}
