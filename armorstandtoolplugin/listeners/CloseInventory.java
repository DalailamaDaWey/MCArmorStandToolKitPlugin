package me.jamesbrown.armorstandtoolplugin.listeners;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CloseInventory implements Listener {

    ArmorStandToolPlugin plugin;

    public CloseInventory(ArmorStandToolPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        if (plugin.getEntity(player) != null){
            plugin.deleteEntity(player);
        }
    }

}
