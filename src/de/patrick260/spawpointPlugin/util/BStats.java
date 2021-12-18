package de.patrick260.spawpointPlugin.util;

import org.bukkit.plugin.java.JavaPlugin;

public class BStats {

    private final Metrics metrics;

    public BStats(JavaPlugin plugin, int serviceID) {

        metrics = new Metrics(plugin, serviceID);

    }

}
