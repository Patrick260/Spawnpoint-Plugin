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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public final class ReloadConfigCommand implements CommandExecutor {

    private final LanguageManager languageManager = SpawnpointPlugin.getPlugin().getLanguageManager();


    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String label, final String[] args) {

        final FileConfiguration config = SpawnpointPlugin.getPlugin().getConfig();

        if (commandSender.hasPermission(config.getString("permissions.commands.admin.reloadConfig"))) {

            SpawnpointPlugin.getPlugin().reloadConfig();
            languageManager.reloadLanguage();

            languageManager.sendMessage(commandSender, "messages.commands.reloadConfigCommand.reloadSuccess");

        } else {

            languageManager.sendMessage(commandSender, "messages.commands.general.noPermissions");

        }

        return true;

    }

}
