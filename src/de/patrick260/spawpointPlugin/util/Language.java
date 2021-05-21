package de.patrick260.spawpointPlugin.util;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Language {

    private static FileConfiguration config = Main.getPlugin().getConfig();
    private static FileConfiguration languageFile = YamlConfiguration.loadConfiguration(new File(getClass().getClassLoader().getResourceAsStream("LANG/LANG_" + config.getString("settings.language") + ".yml")));

    public static String getText(String path) {

        return languageFile.getString(path);

    }

}
