package cn.crtlprototypestudios.samccp.core.utility;

import io.lumine.mythic.api.mobs.MobManager;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;

import javax.annotation.Nullable;

public class MythicWrapper {
    private static MythicBukkit mythicInstance;
    private static MobManager mobManagerInstance;

    public static MythicBukkit getMythicInstance() {
        if (mythicInstance == null) {
            mythicInstance = MythicBukkit.inst();
        }
        return mythicInstance;
    }

    public static MobManager getMobManagerInstance() {
        if (mobManagerInstance == null) {
            mobManagerInstance = getMythicInstance().getMobManager();
        }
        return mobManagerInstance;
    }

    @Nullable
    public static MythicMob getMythicMob(String mobName) {
        return getMobManagerInstance().getMythicMob(mobName).orElse(null);
    }
}
