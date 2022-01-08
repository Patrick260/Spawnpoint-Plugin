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

package de.patrick260.spawpointplugin.util;

import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BStats {

    public BStats(final JavaPlugin plugin, final int serviceID) {

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
