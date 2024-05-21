package me.jamesbrown.armorstandtoolplugin.listeners;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.EulerAngle;

public class PoseListener implements Listener {

    ArmorStandToolPlugin plugin;

    public PoseListener(ArmorStandToolPlugin plugin) {
        this.plugin = plugin;
    }

    public void startReading(Player player, ArmorStandToolPlugin.part bodyPart){
        plugin.setPoseChat(player, bodyPart);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("pose-menu.set-pose-change-message", "&6Set pose of the current body part in the following format: X, Y, Z", plugin)));
        player.closeInventory();
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event) {

        final String POSE_MENU = ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("name-of-the-pose-menu", "&ePose Menu", plugin));

        if (event.getView().getTitle().equals(POSE_MENU) && !event.getClickedInventory().equals(event.getWhoClicked().getInventory())){

            Player player = (Player) event.getWhoClicked();

            if (!event.getClickedInventory().equals(player.getInventory())) {
                event.setCancelled(true);
            }

            if (event.isRightClick()){
                return;
            }

            if (event.getCurrentItem().getType().equals(Material.LEATHER_HELMET)){
                startReading(player, ArmorStandToolPlugin.part.HEAD);
            }

            if (event.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE)){
                startReading(player, ArmorStandToolPlugin.part.BODY);
            }

            if (event.getCurrentItem().getType().equals(Material.SHIELD)){
                startReading(player, ArmorStandToolPlugin.part.LEFT_HAND);
            }

            if (event.getCurrentItem().getType().equals(Material.WOODEN_SWORD)){
                startReading(player, ArmorStandToolPlugin.part.RIGHT_HAND);
            }

            if (event.getCurrentItem().getType().equals(Material.STICK)){
                startReading(player, ArmorStandToolPlugin.part.LEFT_LEG);
            }

            if (event.getCurrentItem().getType().equals(Material.BLAZE_ROD)){
                startReading(player, ArmorStandToolPlugin.part.RIGHT_LEG);
            }

            if (event.getCurrentItem().getType().equals(Material.CRAFTING_TABLE)){
                ArmorStand armorstand = plugin.getEntity(player);

                armorstand.setHeadPose(new EulerAngle(0, 0, 0));
                armorstand.setBodyPose(new EulerAngle(0, 0, 0));
                armorstand.setRightArmPose(new EulerAngle(0, 0, 0));
                armorstand.setLeftArmPose(new EulerAngle(0, 0, 0));
                armorstand.setRightLegPose(new EulerAngle(0, 0, 0));
                armorstand.setLeftLegPose(new EulerAngle(0, 0, 0));
            }

            if (event.getCurrentItem().getType().equals(Material.BARRIER)){
                plugin.openToolkitMenu(player);
            }

        }

    }

}
