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
