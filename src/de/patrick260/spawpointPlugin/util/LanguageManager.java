package de.patrick260.spawpointPlugin.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageManager {

    private String language;

    private FileConfiguration languageFile;

    public LanguageManager(String language) {

        this.language = language;

        languageFile = YamlConfiguration.loadConfiguration(this.getClass().getClassLoader().getResourceAsStream("LANG/LANG_" + language + ".yml"));

    }

    public String getText(String path) {

        return languageFile.getString(path).replaceAll((char)194+"","");

    }

}
