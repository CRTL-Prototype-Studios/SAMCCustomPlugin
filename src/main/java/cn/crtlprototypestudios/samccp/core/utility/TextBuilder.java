package cn.crtlprototypestudios.samccp.core.utility;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;

public class TextBuilder {
    public static TextComponent component(String... text){
        StringBuilder builder = new StringBuilder();
        for(String s : text)
            builder.append(s);

        return Component.text(builder.toString());
    }

    public static TextComponent component(ChatColor messageColor, String... text){
        StringBuilder builder = new StringBuilder();
        for(String s : text)
            builder.append(s).append(messageColor);

        return Component.text(builder.toString());
    }

    public static String colored(String text, ChatColor head, ChatColor end){
        return head + text + end;
    }

    public static String colored(String text, ChatColor head){
        return colored(text, head, head);
    }
}
