package de.patrick260.spawpointPlugin.util;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageManager {

    private String language;

    private FileConfiguration languageFile;

    public LanguageManager(String language) {

        this.language = language;

        languageFile = YamlConfiguration.loadConfiguration(this.getClass().getClassLoader().getResourceAsStream("LANG/LANG_" + language + ".yml"));

        if (language == null) {

            Main.getPlugin().getConsole().sendMessage(Main.getPlugin().getPrefix() + "§cAn error occurred while loading the language file! Perhaps an invalid language was selected.");
            Main.getPlugin().getPluginLoader().disablePlugin(Main.getPlugin());

        }

    }

    public String getText(String path) {

        return languageFile.getString(path).replaceAll((char)194+"","");

    }

}
