package cn.crtlprototypestudios.samccp.core.tasks;

import cn.crtlprototypestudios.samccp.SAMCCustomPlugin;
import cn.crtlprototypestudios.samccp.core.scheduling.SchedulerWrapper;
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
            if(localTime.isAfter(LocalTime.of(11, 0)) && localTime.isBefore(LocalTime.of(12, 0)) && !isExecuted){
                SchedulerWrapper.getInstance().runTaskTimer(SAMCCustomPlugin.getPlugin(SAMCCustomPlugin.class), new SpawnEntityTask(), 10, 10);
                isExecuted = true;
            } else {
                isExecuted = false;
            }
        } else {
            isExecuted = false;
        }
    }
}
