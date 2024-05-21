package me.jamesbrown.armorstandtoolplugin.listeners;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatNameListener implements Listener {

    ArmorStandToolPlugin plugin;

    public ChatNameListener(ArmorStandToolPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (plugin.getIfNameChatPlayer(player)){
            event.setCancelled(true);
            String name = event.getMessage();
            ArmorStand armorstand = plugin.getEntity(player);
            armorstand.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
            armorstand.setCustomNameVisible(true);
        }
    }
}
