package de.patrick260.spawpointPlugin.main;

import de.patrick260.spawpointPlugin.commands.SetSpawnpointCommand;
import de.patrick260.spawpointPlugin.commands.SpawnCommand;
import de.patrick260.spawpointPlugin.listeners.PlayerRespawnListener;
import de.patrick260.spawpointPlugin.listeners.PlayerJoinListener;
import de.patrick260.spawpointPlugin.util.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    private LanguageManager languageManager;

    private ConsoleCommandSender console = Bukkit.getConsoleSender();
    private FileConfiguration config = getConfig();

    private String plugin_prefix;


    public static void main(String[] args) {

        System.out.println("This is a Minecraft 1.8.8 plugin! Not a Java programm!");

    }


    public void onEnable() {

        plugin = this;

        setupDefaultConfig();
        console.sendMessage("[SpawnpointPlugin] config.yml wurde erfolgreich geladen!");

        languageManager = new LanguageManager(config.getString("settings.language"));
        console.sendMessage("");

        plugin_prefix = getPrefix();
        console.sendMessage(plugin_prefix + "§aDer Plugin Prefix wurde erfolgreich aus der Config geladen!");

        registerCommands();
        registerListener();

        console.sendMessage(plugin_prefix + "§aDas Plugin wurde erfolgreich aktiviert!");

    }


    private void registerCommands() {

        getCommand("setspawnpoint").setExecutor(new SetSpawnpointCommand());
        console.sendMessage(plugin_prefix + "§aSetspawnpointCommand.java wurde erfolgreich geladen und registriert!");

        getCommand("spawn").setExecutor(new SpawnCommand());
        console.sendMessage(plugin_prefix + "§aSpawnCommand.java wurde erfolgreich geladen und registriert!");

    }

    private void registerListener() {

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        console.sendMessage(plugin_prefix + "§aPlayerJoinListener.java wurde erfolgreich geladen und registriert!");

        pluginManager.registerEvents(new PlayerRespawnListener(), this);
        console.sendMessage(plugin_prefix + "§aPlayerRespawnListener.java wurde erfolgreich geladen und registriert!");

    }


    private void setupDefaultConfig() {

        config.addDefault("plugin.prefix", "§7[§2SpawnpointPlugin§7] §f");

        config.addDefault("permissions.commands.admin.setSpawnpoint", "spawnpointPlugin.commands.admin.setSpawnpoint");
        config.addDefault("permissions.commands.spawn.teleport", "spawnpointPlugin.commands.spawn.teleport");

        config.addDefault("settings.language", "DE");
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

    public FileConfiguration reloadDefaultConfig() {

        reloadConfig();

        return Main.getPlugin().getConfig();

    }

    public static Main getPlugin() {

        return plugin;

    }

    public LanguageManager getLanguageManager() {

        return languageManager;

    }

    public String getPrefix() {

        return config.getString("plugin.prefix");

    }

    public ConsoleCommandSender getConsole() {

        return console;

    }

}
