package de.patrick260.spawpointplugin.commands;

import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import de.patrick260.spawpointplugin.util.LanguageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class ReloadConfigCommand implements CommandExecutor {

    LanguageManager languageManager = SpawnpointPlugin.getPlugin().getLanguageManager();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        FileConfiguration config = SpawnpointPlugin.getPlugin().getConfig();

        if (commandSender.hasPermission(config.getString("permissions.commands.admin.reloadConfig"))) {

            SpawnpointPlugin.getPlugin().reloadConfig();
            languageManager.reloadLanguage();

            commandSender.sendMessage(languageManager.getText("messages.commands.reloadConfigCommand.reloadSuccess"));

        } else {

            commandSender.sendMessage(languageManager.getText("messages.commands.general.noPermissions"));

        }

        return true;

    }

}
