package de.patrick260.spawpointPlugin.commands;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    FileConfiguration config = Main.getPlugin().getConfig();

    private int taskID;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        Main.getPlugin().reloadConfig();

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (args.length == 0) {

                if (player.hasPermission(Main.getPlugin().getConfig().getString("permissions.commands.spawn.teleport"))) {

                    if (config.getInt("settings.commands.spawn.timer") == 0) {

                        Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), config.getFloat("data.spawnpoint.yaw"), config.getFloat("data.spawnpoint.pitch"));

                        player.teleport(location);
                        player.sendMessage("§aDu wurdest zum Spawn teleportiert!");

                    } else {

                        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

                            int countdown = config.getInt("settings.commands.spawn.timer");

                            @Override
                            public void run() {

                                if (countdown <= 0) {

                                    Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), config.getFloat("data.spawnpoint.yaw"), config.getFloat("data.spawnpoint.pitch"));

                                    player.teleport(location);
                                    player.sendMessage("§aDu wurdest zum Spawn teleportiert!");

                                    Bukkit.getScheduler().cancelTask(taskID);

                                } else {

                                    player.sendMessage("§aDu wirst in §c" + countdown + " §aSekunden zum Spawn teleportiert!");
                                    countdown--;

                                }

                            }

                        }, 0, 20);

                    }

                } else {

                    player.sendMessage("§cDazu hast du keine Rechte!");

                }

            } else {

                player.sendMessage("§6Bitte benutze §c/spawn §6!");

            }

        } else {

            commandSender.sendMessage("§cNur Spieler koennen diesen Command ausfuehren!");

        }

        return true;

    }

}
