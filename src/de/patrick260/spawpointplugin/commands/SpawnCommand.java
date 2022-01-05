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

public class SpawnCommand implements CommandExecutor {

    private final LanguageManager languageManager = SpawnpointPlugin.getPlugin().getLanguageManager();

    private final HashMap<UUID, Integer> taskIDs = new HashMap<>();

    private final ArrayList<Player> playersInTeleportQueue = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        FileConfiguration config = SpawnpointPlugin.getPlugin().getConfig();

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (args.length == 0) {

                if (player.hasPermission(SpawnpointPlugin.getPlugin().getConfig().getString("permissions.commands.spawn.teleport"))) {

                    if (config.getInt("settings.commands.spawn.timer") == 0) {

                        Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), (float) config.getDouble("data.spawnpoint.yaw"), (float) config.getDouble("data.spawnpoint.pitch"));

                        player.teleport(location);
                        player.sendMessage(languageManager.getText("messages.commands.spawnCommand.successfullyTeleported"));

                    } else {

                        if (!playersInTeleportQueue.contains(player)) {

                            playersInTeleportQueue.add(player);

                            taskIDs.put(player.getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(SpawnpointPlugin.getPlugin(), new Runnable() {

                                int countdown = config.getInt("settings.commands.spawn.timer");

                                @Override
                                public void run() {

                                    if (countdown <= 0) {

                                        Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), (float) config.getDouble("data.spawnpoint.yaw"), (float) config.getDouble("data.spawnpoint.pitch"));

                                        player.teleport(location);
                                        player.sendMessage(languageManager.getText("messages.commands.spawnCommand.successfullyTeleported"));

                                        playersInTeleportQueue.remove(player);

                                        Bukkit.getScheduler().cancelTask(taskIDs.get(player.getUniqueId()));

                                    } else {

                                        player.sendMessage(languageManager.getText("messages.commands.spawnCommand.teleportCountdown").replace("%countdown%", Integer.toString(countdown)));
                                        countdown--;

                                    }

                                }

                            }, 0, 20));

                        }

                    }

                } else {

                    player.sendMessage(languageManager.getText("messages.commands.general.noPermissions"));

                }

            } else {

                player.sendMessage(languageManager.getText("messages.commands.spawnCommand.wrongSyntax"));

            }

        } else {

            commandSender.sendMessage(languageManager.getText("messages.commands.general.onlyPlayerCanExecute"));

        }

        return true;

    }

}
