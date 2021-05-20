package de.patrick260.spawpointPlugin.main;

import de.patrick260.spawpointPlugin.commands.SetSpawnpointCommand;
import de.patrick260.spawpointPlugin.commands.SpawnCommand;
import de.patrick260.spawpointPlugin.listeners.PlayerRespawnListener;
import de.patrick260.spawpointPlugin.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
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

        plugin = this;

        setupDefaultConfig();
        console.sendMessage("[SpawnpointPlugin] config.yml wurde erfolgreich geladen!");

        plugin_prefix = getPrefix();
        console.sendMessage(plugin_prefix + "§aDer Plugin Prefix wurde erfolgreich aus der Config geladen!");

        registerCommands();
        registerListener();

        console.sendMessage(plugin_prefix + "§aDas Plugin wurde erfolgreich aktiviert!");

    }


    //Register the commands of the plugin.
    private void registerCommands() {

        getCommand("setspawnpoint").setExecutor(new SetSpawnpointCommand());
        console.sendMessage(plugin_prefix + "§aSetspawnpointCommand.java wurde erfolgreich geladen und registriert!");

        getCommand("spawn").setExecutor(new SpawnCommand());
        console.sendMessage(plugin_prefix + "§aSpawnCommand.java wurde erfolgreich geladen und registriert!");

    }

    //Register the listeners of the plugin.
    private void registerListener() {

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        console.sendMessage(plugin_prefix + "§aPlayerJoinListener.java wurde erfolgreich geladen und registriert!");

        pluginManager.registerEvents(new PlayerRespawnListener(), this);
        console.sendMessage(plugin_prefix + "§aPlayerRespawnListener.java wurde erfolgreich geladen und registriert!");

    }

    //Setup the default config file
    private void setupDefaultConfig() {

        config.addDefault("plugin.prefix", "§7[§2SpawnpointPlugin§7] §f");

        config.addDefault("permissions.commands.admin.setSpawnpoint", "spawnpointPlugin.commands.admin.setSpawnpoint");
        config.addDefault("permissions.commands.spawn.teleport", "spawnpointPlugin.commands.spawn.teleport");

        config.addDefault("settings.language", "DE");
        config.addDefault("settings.showPrefixByAdminMessages", true);
        config.addDefault("settings.showPrefixByPlayerMessages", false);
        config.addDefault("settings.commands.spawn.timer", 3);

        config.addDefault("data.spawnpoint.world", Bukkit.getWorlds().get(0).getName());
        config.addDefault("data.spawnpoint.x", 0);
        config.addDefault("data.spawnpoint.y", 64);
        config.addDefault("data.spawnpoint.z", 0);
        config.addDefault("data.spawnpoint.yaw", 0);
        config.addDefault("data.spawnpoint.pitch", 0);

        config.options().copyDefaults(true);

        saveConfig();

    }

    //Reloads the default config file
    public FileConfiguration reloadDefaultConfig() {

        reloadConfig();

        return Main.getPlugin().getConfig();

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
