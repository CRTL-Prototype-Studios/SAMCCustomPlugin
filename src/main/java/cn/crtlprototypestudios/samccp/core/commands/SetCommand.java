package cn.crtlprototypestudios.samccp.core.commands;

import cn.crtlprototypestudios.samccp.SAMCCustomPlugin;
import cn.crtlprototypestudios.samccp.core.utility.ConfigReference;
import cn.crtlprototypestudios.samccp.core.utility.MythicWrapper;
import cn.crtlprototypestudios.samccp.core.utility.TextBuilder;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.mobs.MobRegistry;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetCommand implements CommandExecutor, TabCompleter {
    public static final String KEYWORD = "set";
    protected static final String[] availableProperties = new String[]{
            ConfigReference.SPAWN_LOCATION, ConfigReference.MOB_NAME
    };
    protected static final TextComponent usageMessage = TextBuilder.component(ChatColor.GRAY, "Usage: /set ", TextBuilder.colored("<property_name> <value|null>", ChatColor.LIGHT_PURPLE));

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(TextBuilder.component(ChatColor.RED, "Only players can use this command!"));
            return true;
        }

        Player player = (Player) commandSender;

        if (args.length == 0){
            player.sendMessage(usageMessage);
            return true;
        } else if (args.length == 1){
            if (args[0].equalsIgnoreCase(availableProperties[0])){
                SAMCCustomPlugin.getCustomConfig().set(ConfigReference.SPAWN_LOCATION, player.getLocation());
            } else if (args[0].equalsIgnoreCase(availableProperties[1])){
                player.sendMessage(usageMessage);
            }
        } else if (args.length >= 2){
            if (args[0].equalsIgnoreCase(availableProperties[1])){
                if(!getMythicMobNames().contains(args[1])){
                    player.sendMessage(TextBuilder.component(ChatColor.RED, "Invalid mob name!"));
                    return true;
                }
                SAMCCustomPlugin.getCustomConfig().set("mobName", args[1]);
                player.sendMessage(TextBuilder.component(ChatColor.GREEN, "Property ", TextBuilder.colored(availableProperties[1], ChatColor.GOLD), " is set!"));
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        Player player = (Player) commandSender;
        if (args.length == 1) {
            return List.of(availableProperties);
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase(ConfigReference.MOB_NAME)) {
                return getMythicMobNames();
            }
        }
        return List.of();
    }

    public List<String> getMythicMobNames(){
        List<String> list = new ArrayList<>();

        for(ActiveMob i : MythicWrapper.getMythicInstance().getMobManager().getMobRegistry().values())
            list.add(i.getName());

        return list;
    }
}
