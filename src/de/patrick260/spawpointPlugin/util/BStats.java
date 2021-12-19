package de.patrick260.spawpointPlugin.util;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BStats {

    private Metrics metrics;

    public BStats(JavaPlugin plugin, int serviceID) {

        if (Main.getPlugin().getConfig().getBoolean("settings.BStatsEnabled")) {

            metrics = new Metrics(plugin, serviceID);

            if (YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder().getParentFile() + "/bStats/config.yml")).getBoolean("enabled")) {

                Main.getPlugin().getConsole().sendMessage(Main.getPlugin().getPrefix() + "§aBStats was activated successfully! If you want do disable BStats you can do it in the plugin config or server wide in the bStats config. There is no performance penalty associated with having bStats enabled, and data sent to bStats is fully anonymous. See https://bstats.org/ for more information.");

            }

        }

        if (!YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder().getParentFile() + "/bStats/config.yml")).getBoolean("enabled") || !Main.getPlugin().getConfig().getBoolean("settings.BStatsEnabled")){

            Main.getPlugin().getConsole().sendMessage(Main.getPlugin().getPrefix() + "§aYou have BStats disabled! BStats is a site who collects some basic information for plugin authors, like how many people use their plugin and their total player count. There is no performance penalty associated with having bStats enabled, and data sent to bStats is fully anonymous. If you want to support them enabled bStats in the plugin config or server wide in the bStats config. See https://bstats.org/ for more information.");

        }

    }

}
