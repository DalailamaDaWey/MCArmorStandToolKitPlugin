package me.jamesbrown.armorstandtoolplugin.listeners;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ClickToolListener implements Listener {

    ArmorStandToolPlugin plugin;

    public ClickToolListener(ArmorStandToolPlugin plugin) {
        this.plugin = plugin;
    }

    public void openMainMenu(Player player){

        Inventory mainMenu = Bukkit.createInventory(player,9, ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("name-of-the-creation-menu", "&eArmor Stand Creator", plugin)));
        ItemStack create = new ItemStack(Material.ARMOR_STAND);
        ItemMeta createMeta = create.getItemMeta();
        createMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("creation-menu.create-name", "&9Create an Armor Stand", plugin)));
        create.setItemMeta(createMeta);

        mainMenu.setItem(4, create);
        player.openInventory(mainMenu);
    }

    @EventHandler
    public void Click(PlayerInteractEvent event){

        if (event.getItem()!=null && (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) && Objects.requireNonNull(event.getItem().getItemMeta()).getPersistentDataContainer().has(plugin.getItemTag(), PersistentDataType.BOOLEAN)  && event.getPlayer().hasPermission("toolkit")) {

            Player player = event.getPlayer();

            if (!player.hasPermission("tool-permission-use")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.no-permission-message", "&cYou do not have the permission to do this!", plugin)));
            }

            if (event.hasBlock()){
                    plugin.setLocation(event.getClickedBlock().getLocation());
                    openMainMenu(player);
                } else{
                plugin.setLocation(player.getLocation());
                openMainMenu(player);
            }
        }
    }

    @EventHandler
    public void ArmorStandClick(PlayerInteractAtEntityEvent event){

        if ( event.getRightClicked() instanceof ArmorStand &&  Objects.requireNonNull(event.getPlayer().getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().has(plugin.getItemTag(), PersistentDataType.BOOLEAN) ){
            Player player = event.getPlayer();

            if (!player.hasPermission("tool-permission-use")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.no-permission-message", "&cYou do not have the permission to do this!", plugin)));
            }

            event.setCancelled(true);
            plugin.setEntity(player, (ArmorStand) event.getRightClicked());
            plugin.openToolkitMenu(player);
        }
    }
}
