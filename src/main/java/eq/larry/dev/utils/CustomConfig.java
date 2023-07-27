package eq.larry.dev.utils;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig {
    private FileConfiguration config;
    private File file;

    public CustomConfig(File var1) {
        this.file = var1;
        this.config = YamlConfiguration.loadConfiguration(var1);
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}