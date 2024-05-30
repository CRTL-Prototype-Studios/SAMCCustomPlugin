package cn.crtlprototypestudios.samccp;

import cn.crtlprototypestudios.samccp.core.scheduling.SchedulerWrapper;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class SAMCCustomPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        BukkitScheduler scheduler = this.getServer().getScheduler();
        SchedulerWrapper.setInstance(scheduler);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
