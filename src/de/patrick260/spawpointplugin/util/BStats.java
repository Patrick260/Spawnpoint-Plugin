package de.patrick260.spawpointplugin.util;

import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BStats {

    public BStats(JavaPlugin plugin, int serviceID) {

        if (SpawnpointPlugin.getPlugin().getConfig().getBoolean("settings.BStatsEnabled")) {

            new Metrics(plugin, serviceID);

            if (YamlConfiguration.loadConfiguration(new File(SpawnpointPlugin.getPlugin().getDataFolder().getParentFile() + "/bStats/config.yml")).getBoolean("enabled")) {

                SpawnpointPlugin.getPlugin().getConsole().sendMessage(SpawnpointPlugin.getPlugin().getPrefix() + "§aBStats was activated successfully! If you want do disable BStats you can do it in the plugin config or server wide in the bStats config. There is no performance penalty associated with having bStats enabled, and data sent to bStats is fully anonymous. See https://bstats.org/ for more information.");

            }

        }

        if (!YamlConfiguration.loadConfiguration(new File(SpawnpointPlugin.getPlugin().getDataFolder().getParentFile() + "/bStats/config.yml")).getBoolean("enabled") || !SpawnpointPlugin.getPlugin().getConfig().getBoolean("settings.BStatsEnabled")){

            SpawnpointPlugin.getPlugin().getConsole().sendMessage(SpawnpointPlugin.getPlugin().getPrefix() + "§aYou have BStats disabled! BStats is a site who collects some basic information for plugin authors, like how many people use their plugin and their total player count. There is no performance penalty associated with having bStats enabled, and data sent to bStats is fully anonymous. If you want to support them enabled bStats in the plugin config or server wide in the bStats config. See https://bstats.org/ for more information.");

        }

    }

}
