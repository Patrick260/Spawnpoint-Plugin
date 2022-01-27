/*
    Copyright (C) 2021  Patrick260
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package de.patrick260.spawpointplugin.main;

import de.patrick260.spawpointplugin.commands.ReloadConfigCommand;
import de.patrick260.spawpointplugin.commands.SetSpawnpointCommand;
import de.patrick260.spawpointplugin.commands.SpawnCommand;
import de.patrick260.spawpointplugin.commands.SpawnpointInfoCommand;
import de.patrick260.spawpointplugin.listeners.PlayerRespawnListener;
import de.patrick260.spawpointplugin.listeners.PlayerJoinListener;
import de.patrick260.spawpointplugin.util.BStats;
import de.patrick260.spawpointplugin.util.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public final class SpawnpointPlugin extends JavaPlugin {

    private static SpawnpointPlugin plugin;

    private LanguageManager languageManager;


    public static void main(final String[] args) {

        System.out.println("This is a Minecraft plugin! Not a Java program!");

    }


    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();
        reloadConfig();
        getConsole().sendMessage(getPrefix() + "§aconfig.yml was loaded successfully!");

        languageManager = new LanguageManager(getConfig().getString("settings.language"));
        getConsole().sendMessage(getPrefix() + "§aLanguage file LANG_" + getConfig().getString("settings.language") + ".yml was loaded successfully!");

        registerCommands();
        registerListeners();

        new BStats(this, 13639);

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

    private void registerListeners() {

        final PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        getConsole().sendMessage(getPrefix() + "§aPlayerJoinListener.java was successfully loaded and registered!");

        pluginManager.registerEvents(new PlayerRespawnListener(), this);
        getConsole().sendMessage(getPrefix() + "§aPlayerRespawnListener.java was successfully loaded and registered!");

    }


    @Override
    public void saveConfig() {

        try {

            final BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(getDataFolder(), "config.yml")));

            final TreeMap<Integer, String> comments = new TreeMap<>();

            String line;
            int index = 0;

            while ((line = bufferedReader.readLine()) != null) {

                if (line.contains("#") || line.trim().isEmpty()) {

                    comments.put(index, line);

                }

                index++;

            }

            bufferedReader.close();

            final ArrayList<String> toSave = new ArrayList<>();

            final String data = getConfig().saveToString();

            for (final String s : data.split("\n")) {

                if (!s.contains("#")) {

                    toSave.add(s);

                }

            }

            for (final int i : comments.keySet()) {

                toSave.add(i, comments.get(i));

            }

            final StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < toSave.size(); i++) {

                stringBuilder.append(toSave.get(i));

                if (toSave.size() - 1 - i != 0) {

                    stringBuilder.append("\n");

                }

            }

            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(getDataFolder(), "config.yml")));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    public static SpawnpointPlugin getPlugin() {

        return plugin;

    }

    public LanguageManager getLanguageManager() {

        return languageManager;

    }

    public String getPrefix() {

        return getConfig().getString("settings.prefix").replaceAll("&", "§");

    }

    public ConsoleCommandSender getConsole() {

        return Bukkit.getConsoleSender();

    }

}
