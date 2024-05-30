package cn.crtlprototypestudios.samccp.core.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnEntityTask extends BukkitRunnable {
    private World world;
    private Location location;

    public SpawnEntityTask(World world, Location location) {
        super();
        this.world = world;
        this.location = location;
    }

    @Override
    public void run() {
        boolean canRun = false;
        world.getNearbyEntities(location, 10, 10, 10).forEach(entity -> {});

        String command = "say Hello, world!";
        if (canRun)
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}
