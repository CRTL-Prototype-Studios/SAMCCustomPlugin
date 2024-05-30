package cn.crtlprototypestudios.samccp.core.tasks;

import cn.crtlprototypestudios.samccp.core.utility.MythicWrapper;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.api.mobs.entities.MythicEntity;
import io.lumine.mythic.bukkit.BukkitAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpawnEntityTask extends BukkitRunnable {
    private Location location;
    private String mobName;

    public SpawnEntityTask(Location location, String mobName) {
        super();
        this.location = location;
        this.mobName = mobName;
    }

    @Override
    public void run() {
        AtomicBoolean canRun = new AtomicBoolean(true);
        location.getWorld().getNearbyEntities(location, 10, 10, 10).forEach(entity -> {
            if (entity instanceof MythicEntity) {
                canRun.set(false);
                return;
            }
        });

//        String command = "say Hello, world!";
        if (canRun.get()) {
            MythicMob mob = MythicWrapper.getMythicMob(mobName);
            assert mob != null;
            mob.spawn(BukkitAdapter.adapt(location), MythicWrapper.getWorldIndex(location.getWorld()));
        }
    }
}
