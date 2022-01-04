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
