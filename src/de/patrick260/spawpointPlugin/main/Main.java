package de.patrick260.spawpointPlugin.main;

import de.patrick260.spawpointPlugin.commands.ReloadConfigCommand;
import de.patrick260.spawpointPlugin.commands.SetSpawnpointCommand;
import de.patrick260.spawpointPlugin.commands.SpawnCommand;
import de.patrick260.spawpointPlugin.commands.SpawnpointInfoCommand;
import de.patrick260.spawpointPlugin.listeners.PlayerRespawnListener;
import de.patrick260.spawpointPlugin.listeners.PlayerJoinListener;
import de.patrick260.spawpointPlugin.util.BStats;
import de.patrick260.spawpointPlugin.util.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    private LanguageManager languageManager;

    private BStats bStats;


    public static void main(String[] args) {

        System.out.println("This is a Minecraft 1.8.8 plugin! Not a Java programm!");

    }


    public void onEnable() {

        plugin = this;

        saveDefaultConfig();
        reloadConfig();
        getConsole().sendMessage(getPrefix() + "§aconfig.yml was loaded successfully!");

        languageManager = new LanguageManager(getConfig().getString("settings.language"));
        getConsole().sendMessage(getPrefix() + "§aLanguage file LANG_" + getConfig().getString("settings.language") + ".yml was loaded successfully!");

        registerCommands();
        registerListener();

        bStats = new BStats(this, 13639);

        getConsole().sendMessage(getPrefix() + "§aThe plugin was activated successfully!");

    }


    private void registerCommands() {

        getCommand("setspawnpoint").setExecutor(new SetSpawnpointCommand());
        getConsole().sendMessage(getPrefix() + "§aSetSpawnpointCommand.java was successfully loaded and registered!");

        getCommand("spawn").setExecutor(new SpawnCommand());
        getConsole().sendMessage(getPrefix() + "§aSpawnCommand.java was successfully loaded and registered!");

        getCommand("spawnpointplugin-info").setExecutor(new SpawnpointInfoCommand());
        getConsole().sendMessage(getPrefix() + "§aSpawnpointInfoCommand.java was successfully loaded and registered!");

        getCommand("spawnpointplugin-reloadconfig").setExecutor(new ReloadConfigCommand());
        getConsole().sendMessage(getPrefix() + "§aReloadConfigCommand.java was successfully loaded and registered!");

    }

    private void registerListener() {

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        getConsole().sendMessage(getPrefix() + "§aPlayerJoinListener.java was successfully loaded and registered!");

        pluginManager.registerEvents(new PlayerRespawnListener(), this);
        getConsole().sendMessage(getPrefix() + "§aPlayerRespawnListener.java was successfully loaded and registered!");

    }


    public static Main getPlugin() {

        return plugin;

    }

    public LanguageManager getLanguageManager() {

        return languageManager;

    }

    public String getPrefix() {

        return getConfig().getString("plugin.prefix").replaceAll("&", "§");

    }

    public ConsoleCommandSender getConsole() {

        return Bukkit.getConsoleSender();

    }

}
