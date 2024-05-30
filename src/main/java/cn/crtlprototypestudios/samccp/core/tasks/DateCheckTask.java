package cn.crtlprototypestudios.samccp.core.tasks;

import cn.crtlprototypestudios.samccp.SAMCCustomPlugin;
import cn.crtlprototypestudios.samccp.core.scheduling.SchedulerWrapper;
import cn.crtlprototypestudios.samccp.core.utility.ConfigReference;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateCheckTask extends BukkitRunnable {
    private boolean isExecuted = false;
    @Override
    public void run() {
//        SchedulerWrapper.getInstance()
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        if (localDateTime.getDayOfWeek() == DayOfWeek.SUNDAY){
            LocalTime localTime = localDateTime.toLocalTime();
            if(localTime.isAfter(LocalTime.of(11, 0)) &&
                    localTime.isBefore(LocalTime.of(12, 0)) &&
                    !isExecuted &&
                    SAMCCustomPlugin.getCustomConfig().getLocation(ConfigReference.SPAWN_LOCATION) != null &&
                    SAMCCustomPlugin.getCustomConfig().getString(ConfigReference.MOB_NAME) != null){

                SchedulerWrapper.getInstance().runTaskTimer(SAMCCustomPlugin.getInstance(), new SpawnEntityTask(SAMCCustomPlugin.getCustomConfig().getLocation(ConfigReference.SPAWN_LOCATION), SAMCCustomPlugin.getCustomConfig().getString(ConfigReference.MOB_NAME)), 10, 10);
                isExecuted = true;

            } else {
                isExecuted = false;
            }
        } else {
            isExecuted = false;
        }
    }
}
