package de.patrick260.spawpointPlugin.util;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguageManager {

    private String language;

    private FileConfiguration languageFile;

    public LanguageManager(String language) {

        Main.getPlugin().saveResource("LANG/LANG_DE.yml", false);
        Main.getPlugin().saveResource("LANG/LANG_EN.yml", false);

        this.language = language;

        loadLangFile();

    }

    public void reloadLanguage() {

        this.language = Main.getPlugin().getConfig().getString("settings.language");

        loadLangFile();

    }

    public void loadLangFile() {

        if (!new File(Main.getPlugin().getDataFolder()+ "/LANG/LANG_" + language + ".yml").exists()) {

            Main.getPlugin().getConsole().sendMessage(Main.getPlugin().getPrefix() + "§cAn error occurred while loading the language file! Perhaps an invalid language was selected.");

        } else {

            languageFile = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder() + "/LANG/LANG_" + language + ".yml"));

        }

    }

    public String getText(String path) {

        return languageFile.getString(path).replaceAll((char)194+"","").replaceAll("&", "§");

    }

}
