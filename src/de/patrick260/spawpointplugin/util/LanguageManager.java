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

package de.patrick260.spawpointplugin.util;

import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguageManager {

    private String language;

    private FileConfiguration languageFile;


    public LanguageManager(String language) {

        SpawnpointPlugin.getPlugin().saveResource("LANG/LANG_DE.yml", false);
        SpawnpointPlugin.getPlugin().saveResource("LANG/LANG_EN.yml", false);

        this.language = language;

        loadLangFile();

    }


    public void reloadLanguage() {

        this.language = SpawnpointPlugin.getPlugin().getConfig().getString("settings.language");

        loadLangFile();

    }

    public void loadLangFile() {

        if (!new File(SpawnpointPlugin.getPlugin().getDataFolder()+ "/LANG/LANG_" + language + ".yml").exists()) {

            SpawnpointPlugin.getPlugin().getConsole().sendMessage(SpawnpointPlugin.getPlugin().getPrefix() + "§cAn error occurred while loading the language file! Perhaps an invalid language was selected.");

        } else {

            languageFile = YamlConfiguration.loadConfiguration(new File(SpawnpointPlugin.getPlugin().getDataFolder() + "/LANG/LANG_" + language + ".yml"));

        }

    }


    public String getText(String path) {

        return languageFile.getString(path).replaceAll((char)194+"","").replaceAll("&", "§");

    }

}
