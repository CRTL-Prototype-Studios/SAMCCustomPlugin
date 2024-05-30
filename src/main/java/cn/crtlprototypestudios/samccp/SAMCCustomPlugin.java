package cn.crtlprototypestudios.samccp;

import cn.crtlprototypestudios.samccp.core.scheduling.SchedulerWrapper;
import cn.crtlprototypestudios.samccp.core.utility.Reference;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

public final class SAMCCustomPlugin extends JavaPlugin {

    private static SAMCCustomPlugin instance;

    private static FileConfiguration customConfig;
    private static File configFile;

    @Override
    public void onEnable() {
        instance = this;
//        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);

        // Create the config file if it doesn't exist
        configFile = new File(getDataFolder(), Reference.CONFIG_FILE_NAME);
        if (!configFile.exists()) {
            saveResource(Reference.CONFIG_FILE_NAME, false);
        }

        // Load the configuration
        customConfig = YamlConfiguration.loadConfiguration(configFile);

        // Plugin startup logic
        BukkitScheduler scheduler = this.getServer().getScheduler();
        SchedulerWrapper.setInstance(scheduler);
    }

    public static FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public static File getConfigFile() {
        return configFile;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SAMCCustomPlugin getInstance() {
        return instance;
    }
}
