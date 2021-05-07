package de.patrick260.spawpointPlugin.commands;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetspawnpointCommand implements CommandExecutor {

    private FileConfiguration config = Main.getPlugin().getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (commandSender.hasPermission(config.getString("permissions.commands.admin.setspawnpoint"))) {

            if (args.length == 0) {

                if (commandSender instanceof Player) {

                    Player player = (Player) commandSender;

                    config.set("data.spawnpoint.world", Bukkit.getWorld(player.getWorld().getName()).getName());
                    config.set("data.spawnpoint.x", player.getLocation().getX());
                    config.set("data.spawnpoint.y", player.getLocation().getY());
                    config.set("data.spawnpoint.z", player.getLocation().getZ());
                    config.set("data.spawnpoint.yaw", player.getLocation().getYaw());
                    config.set("data.spawnpoint.pitch", player.getLocation().getPitch());

                    Main.getPlugin().saveConfig();

                    player.sendMessage(Main.getPlugin().getPrefix() + "§aDer Spawnpunkt wurde erfolgreich auf deine Position gesetzt!");

                } else {

                    commandSender.sendMessage("§6Bitte benutze §c/setspawnpoint (world, x, y, z) (yaw, pitch) §6!");

                }

            } else if (args.length == 3) {

                if (commandSender instanceof Player) {

                    config.set("data.spawnpoint.world", Bukkit.getWorld(((Player) commandSender).getWorld().getName()).getName());
                    config.set("data.spawnpoint.x", Integer.parseInt(args[0]));
                    config.set("data.spawnpoint.y", Integer.parseInt(args[1]));
                    config.set("data.spawnpoint.z", Integer.parseInt(args[2]));

                    Main.getPlugin().saveConfig();

                    commandSender.sendMessage(Main.getPlugin().getPrefix() + "§aDer Spawnpunkt wurde erfolgreich auf die angegebenen Kordinaten gesetzt!");

                } else {

                    commandSender.sendMessage("§6Bitte benutze §c/setspawnpoint (world, x, y, z) (yaw, pitch) §6!");

                }

            } else if (args.length == 4) {

                if (Bukkit.getWorld(args[0]) != null) {

                    config.set("data.spawnpoint.world", Bukkit.getWorld(args[0]).getName());
                    config.set("data.spawnpoint.x", Integer.parseInt(args[1]));
                    config.set("data.spawnpoint.y", Integer.parseInt(args[2]));
                    config.set("data.spawnpoint.z", Integer.parseInt(args[3]));

                    Main.getPlugin().saveConfig();

                    commandSender.sendMessage(Main.getPlugin().getPrefix() + "§aDer Spawnpunkt wurde erfolgreich auf die angegebenen Kordinaten gesetzt!");

                } else {

                    commandSender.sendMessage(Main.getPlugin().getPrefix() + "§cDer Spawnpoint wurde nicht gesetzt da die angegebene Welt nicht existiert!");

                }

            } else if (args.length == 5) {

                if (commandSender instanceof Player) {

                    config.set("data.spawnpoint.world", Bukkit.getWorld(((Player) commandSender).getWorld().getName()).getName());
                    config.set("data.spawnpoint.x", Integer.parseInt(args[0]));
                    config.set("data.spawnpoint.y", Integer.parseInt(args[1]));
                    config.set("data.spawnpoint.z", Integer.parseInt(args[2]));
                    config.set("data.spawnpoint.yaw", Integer.parseInt(args[3]));
                    config.set("data.spawnpoint.pitch", Integer.parseInt(args[4]));

                    Main.getPlugin().saveConfig();

                    commandSender.sendMessage(Main.getPlugin().getPrefix() + "§aDer Spawnpunkt wurde erfolgreich auf die angegebenen Kordinaten gesetzt!");

                } else {

                    commandSender.sendMessage("§6Bitte benutze §c/setspawnpoint (world, x, y, z) (yaw, pitch) §6!");

                }

            } else if (args.length == 6) {

                if (Bukkit.getWorld(args[0]) != null) {

                    config.set("data.spawnpoint.world", Bukkit.getWorld(args[0]).getName());
                    config.set("data.spawnpoint.x", Integer.parseInt(args[1]));
                    config.set("data.spawnpoint.y", Integer.parseInt(args[2]));
                    config.set("data.spawnpoint.z", Integer.parseInt(args[3]));
                    config.set("data.spawnpoint.yaw", Integer.parseInt(args[4]));
                    config.set("data.spawnpoint.pitch", Integer.parseInt(args[5]));

                    Main.getPlugin().saveConfig();

                    commandSender.sendMessage(Main.getPlugin().getPrefix() + "§aDer Spawnpunkt wurde erfolgreich auf die angegebenen Kordinaten gesetzt!");

                } else {

                    commandSender.sendMessage(Main.getPlugin().getPrefix() + "§cDer Spawnpoint wurde nicht gesetzt da die angegebene Welt nicht existiert!");

                }

            } else {

                commandSender.sendMessage("§6Bitte benutze §c/setspawnpoint <world, x, y, z> <yaw, pitch> §6!");

            }

        } else {

            commandSender.sendMessage("§cDazu hast du keine Rechte!");

        }

        return true;

    }

}
