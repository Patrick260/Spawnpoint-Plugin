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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class SetSpawnpointCommand implements CommandExecutor {

    private final LanguageManager languageManager = SpawnpointPlugin.getPlugin().getLanguageManager();


    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String label, final String[] args) {

        final FileConfiguration config = SpawnpointPlugin.getPlugin().getConfig();

        if (commandSender.hasPermission(config.getString("permissions.commands.admin.setSpawnpoint"))) {

            // PLAYER: /setspawnpoint
            if (args.length == 0) {

                if (commandSender instanceof Player) {

                    final Player player = (Player) commandSender;

                    config.set("data.spawnpoint.world", Bukkit.getWorld(player.getWorld().getName()).getName());
                    config.set("data.spawnpoint.x", player.getLocation().getX());
                    config.set("data.spawnpoint.y", player.getLocation().getY());
                    config.set("data.spawnpoint.z", player.getLocation().getZ());
                    config.set("data.spawnpoint.yaw", player.getLocation().getYaw());
                    config.set("data.spawnpoint.pitch", player.getLocation().getPitch());

                    SpawnpointPlugin.getPlugin().saveConfig();

                    languageManager.sendMessage(player, "messages.commands.setSpawnpointCommand.successfullySetSpawnpointToPlayerPosition");

                } else {

                    languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.consoleSetSpawnpointWithWrongArgs");

                }

            // PLAYER: /setspawnpoint <x, y, z>
            } else if (args.length == 3) {

                if (commandSender instanceof Player) {

                    try {

                        config.set("data.spawnpoint.world", Bukkit.getWorld(((Player) commandSender).getWorld().getName()).getName());
                        config.set("data.spawnpoint.x", Double.parseDouble(args[0]));
                        config.set("data.spawnpoint.y", Double.parseDouble(args[1]));
                        config.set("data.spawnpoint.z", Double.parseDouble(args[2]));

                        SpawnpointPlugin.getPlugin().saveConfig();

                        languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.successfullySetSpawnpointToCoordinates");


                    } catch (final NumberFormatException exception) {

                        languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.failToSetSpawnpointInvalidInput");

                    }

                } else {

                    languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.consoleSetSpawnpointWithWrongArgs");

                }

            // PLAYER AND CONSOLE: /setspawnpoint <world, x, y, z>
            } else if (args.length == 4) {

                if (Bukkit.getWorld(args[0]) != null) {

                    try {

                        config.set("data.spawnpoint.world", Bukkit.getWorld(args[0]).getName());
                        config.set("data.spawnpoint.x", Double.parseDouble(args[1]));
                        config.set("data.spawnpoint.y", Double.parseDouble(args[2]));
                        config.set("data.spawnpoint.z", Double.parseDouble(args[3]));

                        SpawnpointPlugin.getPlugin().saveConfig();

                        languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.successfullySetSpawnpointToCoordinates");

                    } catch (final NumberFormatException exception) {

                        languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.consoleSetSpawnpointWithWrongArgs");

                    }

                } else {

                    languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.failToSetSpawnpointNoSuchWorld");

                }

            // PLAYER: /setspawnpoint <x, y, z, yaw, pitch>
            } else if (args.length == 5) {

                if (commandSender instanceof Player) {

                    try {

                        config.set("data.spawnpoint.world", Bukkit.getWorld(((Player) commandSender).getWorld().getName()).getName());
                        config.set("data.spawnpoint.x", Double.parseDouble(args[0]));
                        config.set("data.spawnpoint.y", Double.parseDouble(args[1]));
                        config.set("data.spawnpoint.z", Double.parseDouble(args[2]));
                        config.set("data.spawnpoint.yaw", Double.parseDouble(args[3]));
                        config.set("data.spawnpoint.pitch", Double.parseDouble(args[4]));

                        SpawnpointPlugin.getPlugin().saveConfig();

                        languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.successfullySetSpawnpointToCoordinates");

                    } catch (final NumberFormatException exception) {

                        languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.failToSetSpawnpointInvalidInput");

                    }

                } else {

                    languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.consoleSetSpawnpointWithWrongArgs");

                }

            // PLAYER AND CONSOLE: /setspawnpoint <world, x, y, z, yaw, pitch>
            } else if (args.length == 6) {

                if (Bukkit.getWorld(args[0]) != null) {

                    try {

                        config.set("data.spawnpoint.world", Bukkit.getWorld(args[0]).getName());
                        config.set("data.spawnpoint.x", Double.parseDouble(args[1]));
                        config.set("data.spawnpoint.y", Double.parseDouble(args[2]));
                        config.set("data.spawnpoint.z", Double.parseDouble(args[3]));
                        config.set("data.spawnpoint.yaw", Double.parseDouble(args[4]));
                        config.set("data.spawnpoint.pitch", Double.parseDouble(args[5]));

                        SpawnpointPlugin.getPlugin().saveConfig();

                        languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.successfullySetSpawnpointToCoordinates");

                    } catch (final NumberFormatException exception) {

                        languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.failToSetSpawnpointInvalidInput");

                    }

                } else {

                    languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.failToSetSpawnpointNoSuchWorld");

                }

            } else {

                languageManager.sendMessage(commandSender, "messages.commands.setSpawnpointCommand.consoleSetSpawnpointWithWrongArgs");

            }

        } else {

            languageManager.sendMessage(commandSender, "messages.commands.general.noPermissions");

        }

        return true;

    }

}
