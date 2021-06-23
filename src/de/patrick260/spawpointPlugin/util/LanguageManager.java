package de.patrick260.spawpointPlugin.util;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageManager {

    private final String language;

    private FileConfiguration languageFile;

    public LanguageManager(String language) {

        this.language = language;

        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();

        try {

            languageFile = YamlConfiguration.loadConfiguration(this.getClass().getClassLoader().getResourceAsStream("LANG/LANG_" + language + ".yml"));

        } catch (IllegalArgumentException exception) {

            illegalArgumentException = exception;

        }

        if (languageFile == null) {

            Main.getPlugin().getConsole().sendMessage(Main.getPlugin().getPrefix() + "§cAn error occurred while loading the language file! Perhaps an invalid language was selected.");
            throw illegalArgumentException;

        }

    }

    public String getText(String path) {

        return languageFile.getString(path).replaceAll((char)194+"","");

    }

}
