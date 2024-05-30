package cn.crtlprototypestudios.samccp.core.scheduling;

import cn.crtlprototypestudios.samccp.SAMCCustomPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class SchedulerWrapper {
    private static BukkitScheduler instance;

    public static BukkitScheduler getInstance() {
        if (instance == null) {
            instance = Bukkit.getServer().getScheduler();
        }
        return instance;
    }
    public static BukkitScheduler setInstance(BukkitScheduler scheduler) {
        instance = scheduler;
        return instance;
    }
}
