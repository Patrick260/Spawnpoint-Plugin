package de.patrick260.spawpointPlugin.main;

import de.patrick260.spawpointPlugin.commands.SetSpawnpointCommand;
import de.patrick260.spawpointPlugin.commands.SpawnCommand;
import de.patrick260.spawpointPlugin.commands.SpawnpointInfoCommand;
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

    private final ConsoleCommandSender console = Bukkit.getConsoleSender();
    private final FileConfiguration config = getConfig();

    private String plugin_prefix;


    public static void main(String[] args) {

        System.out.println("This is a Minecraft 1.8.8 plugin! Not a Java programm!");

    }


    public void onEnable() {

        plugin = this;

        setupDefaultConfig();
        console.sendMessage("[SpawnpointPlugin] config.yml was loaded successfully!");

        plugin_prefix = getPrefix();
        console.sendMessage(plugin_prefix + "§aThe plugin prefix was successfully loaded from the config!");

        languageManager = new LanguageManager(config.getString("settings.language"));
        console.sendMessage(plugin_prefix + "Language file LANG_" + config.getString("settings.language") + ".yml was loaded successfully!");

        registerCommands();
        registerListener();

        console.sendMessage(plugin_prefix + "§aThe plugin was activated successfully!");

    }


    private void registerCommands() {

        getCommand("setspawnpoint").setExecutor(new SetSpawnpointCommand());
        console.sendMessage(plugin_prefix + "§aSetspawnpointCommand.java was successfully loaded and registered!");

        getCommand("spawn").setExecutor(new SpawnCommand());
        console.sendMessage(plugin_prefix + "§aSpawnCommand.java was successfully loaded and registered!");

        getCommand("spawnpointinfo").setExecutor(new SpawnpointInfoCommand());
        console.sendMessage(plugin_prefix + "§aSpawnpointInfoCommand.java was successfully loaded and registered!");

    }

    private void registerListener() {

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        console.sendMessage(plugin_prefix + "§aPlayerJoinListener.java was successfully loaded and registered!");

        pluginManager.registerEvents(new PlayerRespawnListener(), this);
        console.sendMessage(plugin_prefix + "§aPlayerRespawnListener.java was successfully loaded and registered!");

    }


    private void setupDefaultConfig() {

        config.addDefault("plugin.prefix", "§7[§2SpawnpointPlugin§7] §f");

        config.addDefault("permissions.commands.admin.setSpawnpoint", "spawnpointPlugin.commands.admin.setSpawnpoint");
        config.addDefault("permissions.commands.spawn.teleport", "spawnpointPlugin.commands.spawn.teleport");

        config.addDefault("settings.language", "EN");
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
