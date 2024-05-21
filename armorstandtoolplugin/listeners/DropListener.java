package me.jamesbrown.armorstandtoolplugin.listeners;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class DropListener implements Listener {

    ArmorStandToolPlugin plugin;

    public DropListener(ArmorStandToolPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {

        if (event.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().has(plugin.getItemTag(), PersistentDataType.BOOLEAN) && !plugin.getConfig().getBoolean("can-drop-toolkit")){
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.throw-away-message", "&cYou can not throw away this item!", plugin)));
            event.setCancelled(true);
        }
    }

}
