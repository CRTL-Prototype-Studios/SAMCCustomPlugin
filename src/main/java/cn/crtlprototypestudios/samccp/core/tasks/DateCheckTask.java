package cn.crtlprototypestudios.samccp.core.tasks;

import cn.crtlprototypestudios.samccp.SAMCCustomPlugin;
import cn.crtlprototypestudios.samccp.core.scheduling.SchedulerWrapper;
import cn.crtlprototypestudios.samccp.core.utility.ConfigReference;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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

        boolean outputLog = SAMCCustomPlugin.getInstance().getConfig().getBoolean(ConfigReference.OUTPUT_LOG);

        if(outputLog)
            SAMCCustomPlugin.getInstance().getLogger().info("DateCheckTask Checking Date....");

        if (localDateTime.getDayOfWeek() == DayOfWeek.SUNDAY){
            Location confLoc = SAMCCustomPlugin.getInstance().getConfig().getLocation(ConfigReference.SPAWN_LOCATION);
            String confMobName = SAMCCustomPlugin.getInstance().getConfig().getString(ConfigReference.MOB_NAME);

            LocalTime localTime = localDateTime.toLocalTime();
            if(localTime.isAfter(LocalTime.of(11, 0)) &&
                    localTime.isBefore(LocalTime.of(12, 0)) &&
                    !isExecuted &&
                    confLoc != null &&
                    confMobName != null){

                SchedulerWrapper.getInstance().runTaskTimer(SAMCCustomPlugin.getInstance(), new SpawnEntityTask(confLoc, confMobName), 10, 10);
                isExecuted = true;

            } else {
                isExecuted = false;
            }
        } else {
            isExecuted = false;
        }

        if(outputLog)
            SAMCCustomPlugin.getInstance().getLogger().info(!isExecuted ? "DateCheckTask Checked, SpawnEntityTask Not Executed" : "DateCheckTask Checked, SpawnEntityTask Executed");
    }
}

