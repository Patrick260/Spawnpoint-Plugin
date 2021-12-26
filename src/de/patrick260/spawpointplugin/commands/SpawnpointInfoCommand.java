package de.patrick260.spawpointplugin.commands;

import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnpointInfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        commandSender.sendMessage(SpawnpointPlugin.getPlugin().getPrefix() + " §aPlugin coded by Patrick260");
        commandSender.sendMessage(SpawnpointPlugin.getPlugin().getPrefix() + " §aPlugin source code: https://github.com/Patrick260/SpawnpointPlugin");
        commandSender.sendMessage(SpawnpointPlugin.getPlugin().getPrefix() + " §aHave fun with the plugin :D");

        return true;

    }

}
