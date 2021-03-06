/*
    Copyright (C) 2021, 2022  Patrick260

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

package de.patrick260.spawpointplugin.commands;

import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import de.patrick260.spawpointplugin.util.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class SpawnCommand implements CommandExecutor {

    private final LanguageManager languageManager = SpawnpointPlugin.getPlugin().getLanguageManager();

    private static final HashMap<UUID, Integer> taskIDs = new HashMap<>();

    private static final HashMap<UUID, Location> playerLocations = new HashMap<>();

    private static final ArrayList<Player> playersInTeleportQueue = new ArrayList<>();


    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String label, final String[] args) {

        final FileConfiguration config = SpawnpointPlugin.getPlugin().getConfig();

        if (commandSender instanceof Player) {

            final Player player = (Player) commandSender;

            if (args.length == 0) {

                if (player.hasPermission(SpawnpointPlugin.getPlugin().getConfig().getString("permissions.commands.spawn.teleport"))) {

                    if (config.getInt("settings.commands.spawn.timer") == 0) {

                        final Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), (float) config.getDouble("data.spawnpoint.yaw"), (float) config.getDouble("data.spawnpoint.pitch"));

                        player.teleport(location);
                        languageManager.sendMessage(player, "messages.commands.spawnCommand.successfullyTeleported");

                    } else {

                        if (!isPlayerInQueue(player)) {

                            playersInTeleportQueue.add(player);

                            playerLocations.put(player.getUniqueId(), player.getLocation());

                            taskIDs.put(player.getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(SpawnpointPlugin.getPlugin(), new Runnable() {

                                int countdown = config.getInt("settings.commands.spawn.timer");

                                @Override
                                public void run() {

                                    if (countdown <= 0) {

                                        final Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), (float) config.getDouble("data.spawnpoint.yaw"), (float) config.getDouble("data.spawnpoint.pitch"));

                                        player.teleport(location);
                                        languageManager.sendMessage(player, "messages.commands.spawnCommand.successfullyTeleported");

                                        playersInTeleportQueue.remove(player);
                                        playerLocations.remove(player.getUniqueId());

                                        Bukkit.getScheduler().cancelTask(taskIDs.get(player.getUniqueId()));
                                        taskIDs.remove(player.getUniqueId());

                                    } else {

                                        languageManager.sendMessage(player, "messages.commands.spawnCommand.teleportCountdown", "%countdown%:" + countdown);
                                        countdown--;

                                    }

                                }

                            }, 0, 20));

                        }

                    }

                } else {

                    languageManager.sendMessage(player, "messages.commands.general.noPermissions");

                }

            } else {

                languageManager.sendMessage(player, "messages.commands.spawnCommand.wrongSyntax");

            }

        } else {

            languageManager.sendMessage(commandSender, "messages.commands.general.onlyPlayerCanExecute");

        }

        return true;

    }

    public static boolean isPlayerInQueue(final Player player) {

        return playersInTeleportQueue.contains(player);

    }

    public static void removePlayerFromQueue(final Player player) {

        Bukkit.getScheduler().cancelTask(taskIDs.get(player.getUniqueId()));

        playersInTeleportQueue.remove(player);
        playerLocations.remove(player.getUniqueId());
        taskIDs.remove(player.getUniqueId());

    }

    public static Location getOldPlayerLocation(final Player player) {

        return playerLocations.get(player.getUniqueId());

    }

}
