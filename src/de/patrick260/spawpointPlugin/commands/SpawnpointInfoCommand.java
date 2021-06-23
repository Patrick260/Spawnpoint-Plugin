package de.patrick260.spawpointPlugin.commands;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnpointInfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        commandSender.sendMessage(Main.getPlugin().getPrefix() + " §aPlugin coded by Patrick260");
        commandSender.sendMessage(Main.getPlugin().getPrefix() + " §aPlugin source code: https://github.com/Patrick260/SpawnpointPlugin");
        commandSender.sendMessage(Main.getPlugin().getPrefix() + " §aHave fun with the plugin :D");

        return true;

    }

}
