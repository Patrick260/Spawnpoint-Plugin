package de.patrick260.spawpointPlugin.commands;

import de.patrick260.spawpointPlugin.main.Main;
import de.patrick260.spawpointPlugin.util.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpawnCommand implements CommandExecutor {

    private FileConfiguration config = Main.getPlugin().getConfig();

    private final LanguageManager languageManager = Main.getPlugin().getLanguageManager();

    private int taskID;

    private ArrayList<Player> playersInTeleportQueue = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        config = Main.getPlugin().reloadDefaultConfig();

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (args.length == 0) {

                if (player.hasPermission(Main.getPlugin().getConfig().getString("permissions.commands.spawn.teleport"))) {

                    if (config.getInt("settings.commands.spawn.timer") == 0) {

                        Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), config.getFloat("data.spawnpoint.yaw"), config.getFloat("data.spawnpoint.pitch"));

                        player.teleport(location);
                        player.sendMessage(languageManager.getText("messages.commands.spawnCommand.succesTeleport"));

                    } else {

                        if (!playersInTeleportQueue.contains(player)) {

                            playersInTeleportQueue.add(player);

                            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

                                int countdown = config.getInt("settings.commands.spawn.timer");

                                @Override
                                public void run() {

                                    if (countdown <= 0) {

                                        Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), config.getFloat("data.spawnpoint.yaw"), config.getFloat("data.spawnpoint.pitch"));

                                        player.teleport(location);
                                        player.sendMessage(languageManager.getText("messages.commands.spawnCommand.succesTeleport"));

                                        playersInTeleportQueue.remove(player);

                                        Bukkit.getScheduler().cancelTask(taskID);

                                    } else {

                                        player.sendMessage(languageManager.getText("messages.commands.spawnCommand.teleportCountdown").replace("%countdown%", Integer.toString(countdown)));
                                        countdown--;

                                    }

                                }

                            }, 0, 20);

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
