package me.jamesbrown.armorstandtoolplugin.listeners;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ApparelListener implements Listener {

    ArmorStandToolPlugin plugin;

    public ApparelListener(ArmorStandToolPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event) {

        final String APPAREL_MENU = ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("name-of-the-apparel-menu", "&eApparel Menu", plugin));

        if (event.getView().getTitle().equals(APPAREL_MENU) && !event.getClickedInventory().equals(event.getWhoClicked().getInventory())){

            Player player = (Player) event.getWhoClicked();

            event.setCancelled(true);

            if (event.isRightClick()){
                return;
            }

            ItemStack item = event.getCursor();

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.LEATHER_HELMET)){
                ArmorStand armorStand = plugin.getEntity(player);
                armorStand.getEquipment().setHelmet(item);
            }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE)){
                ArmorStand armorStand = plugin.getEntity(player);
                armorStand.getEquipment().setChestplate(item);
            }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS)){
                ArmorStand armorStand = plugin.getEntity(player);
                armorStand.getEquipment().setLeggings(item);
            }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.LEATHER_BOOTS)){
                ArmorStand armorStand = plugin.getEntity(player);
                armorStand.getEquipment().setBoots(item);
            }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.WOODEN_SWORD)){
                ArmorStand armorStand = plugin.getEntity(player);
                armorStand.getEquipment().setItemInMainHand(item);
            }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.SHIELD)){
                ArmorStand armorStand = plugin.getEntity(player);
                armorStand.getEquipment().setItemInOffHand(item);
            }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.ENDER_EYE)){
                ArmorStand armorStand = plugin.getEntity(player);
                boolean see = !armorStand.isVisible();
                armorStand.setVisible(see);
                String defaultName = plugin.getConfig().getString("default-name-on-invisibility");

                String ogName = armorStand.getCustomName();

                if (see && ogName != null && (ogName.equals("Invisible Armor Stand") || ogName.equals(defaultName))){
                    armorStand.setCustomName(null);
                    armorStand.setCustomNameVisible(false);
                    return;
                }

                if (!see && plugin.getConfig().getBoolean("show-default-name-on-invisibility-without-armor") && ogName == null) {

                    if (defaultName == null) {
                        armorStand.setCustomName("Invisible Armor Stand");
                        armorStand.setCustomNameVisible(true);
                    } else {
                        armorStand.setCustomName(defaultName);
                        armorStand.setCustomNameVisible(true);
                    }
                }
            }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.STICK)){
                ArmorStand armorStand = plugin.getEntity(player);
                boolean putHands = !armorStand.hasArms();
                armorStand.setArms(putHands);
                }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.CRAFTING_TABLE)){
                ArmorStand armorStand = plugin.getEntity(player);

                armorStand.getEquipment().clear();
            }

            if (event.getCursor() != null && event.getCurrentItem().getType().equals(Material.BARRIER)){
                plugin.openToolkitMenu(player);
            }
        }
    }
}
