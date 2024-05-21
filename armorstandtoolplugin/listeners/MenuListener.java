package me.jamesbrown.armorstandtoolplugin.listeners;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuListener implements Listener {

    ArmorStandToolPlugin plugin;

    public MenuListener(ArmorStandToolPlugin plugin) {
        this.plugin = plugin;
    }

    public void fillInMenu(Inventory menu){

        final int SIZE = 54;

        for (int i = 0; i<SIZE; i++){
            menu.setItem(i, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }
    }

    public ItemStack makeBigItem(String name,String lore, Material item){
        ItemStack menu = new ItemStack(item);
        ItemMeta menuMeta = menu.getItemMeta();
        menuMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        List<String> menuLore = new ArrayList<String>();
        menuLore.add(ChatColor.translateAlternateColorCodes('&',lore));
        menuMeta.setLore(menuLore);
        menu.setItemMeta(menuMeta);

        return menu;
    }

    public ItemStack makeBackItem(){
        ItemStack menu = new ItemStack(Material.BARRIER);
        ItemMeta menuMeta = menu.getItemMeta();
        menuMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("return-to-main-menu","&cMain Menu",plugin)));
        menu.setItemMeta(menuMeta);

        return menu;
    }

    public void openApparelMenu(Player player){

        Inventory apparelMenu = Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("name-of-the-apparel-menu","&eApparel Menu",plugin)));
        fillInMenu(apparelMenu);

        ItemStack headMenu = makeBigItem(plugin.configStringGetter("apparel-menu.head.name","&6Head",plugin), plugin.configStringGetter("apparel-menu.head.description","&9Put an item onto this one to change the head.",plugin), Material.LEATHER_HELMET);

        ItemStack chestMenu = makeBigItem(plugin.configStringGetter("apparel-menu.chest.name","&6Chest",plugin), plugin.configStringGetter("apparel-menu.chest.description","&9Put an item onto this one to change the chest plate.",plugin), Material.LEATHER_CHESTPLATE);

        ItemStack leggingsMenu = makeBigItem(plugin.configStringGetter("apparel-menu.leggings.name","&6Leggings",plugin), plugin.configStringGetter("apparel-menu.leggings.description","&9Put an item onto this one to change the leggings.",plugin), Material.LEATHER_LEGGINGS);

        ItemStack bootsMenu = makeBigItem(plugin.configStringGetter("apparel-menu.boots.name","&6Boots",plugin), plugin.configStringGetter("apparel-menu.boots.description","&9Put an item onto this one to change the boots.",plugin), Material.LEATHER_BOOTS);

        ItemStack leftHandMenu = makeBigItem(plugin.configStringGetter("apparel-menu.left-hand.name","&6Left Hand",plugin), plugin.configStringGetter("apparel-menu.left-hand.description","&9Put an item onto this one to change the item in the left hand.",plugin), Material.SHIELD);

        ItemStack rightHandMenu = makeBigItem(plugin.configStringGetter("apparel-menu.right-hand.name","&6Right Hand",plugin), plugin.configStringGetter("apparel-menu.right-hand.description","&9Put an item onto this one to change the item in the right hand.",plugin), Material.WOODEN_SWORD);

        ItemStack enableHands = plugin.makeSmallItem(plugin.configStringGetter("apparel-menu.enable-hands","&9Show Hands",plugin), Material.STICK);

        ItemStack visibility = plugin.makeSmallItem(plugin.configStringGetter("apparel-menu.visibility","&9Show Armor Stand",plugin), Material.ENDER_EYE);

        ItemStack resetApparel = plugin.makeSmallItem(plugin.configStringGetter("apparel-menu.reset-apparel","&cReset Apparel",plugin), Material.CRAFTING_TABLE);

        ItemStack back = makeBackItem();

        apparelMenu.setItem(11, headMenu);
        apparelMenu.setItem(20, chestMenu);
        apparelMenu.setItem(29, leggingsMenu);
        apparelMenu.setItem(19, leftHandMenu);
        apparelMenu.setItem(21, rightHandMenu);
        apparelMenu.setItem(38, bootsMenu);
        apparelMenu.setItem(16, enableHands);
        apparelMenu.setItem(25, visibility);
        apparelMenu.setItem(34, resetApparel);
        apparelMenu.setItem(8, back);

        player.openInventory(apparelMenu);
    }

    public void openPoseMenu(Player player){

        Inventory poseMenu = Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("name-of-the-pose-menu","&ePose Menu",plugin)));
        fillInMenu(poseMenu);

        ItemStack headMenu = makeBigItem(plugin.configStringGetter("pose-menu.head.name","&6Head",plugin), plugin.configStringGetter("pose-menu.head.description","&9Change the pose of the head.",plugin), Material.LEATHER_HELMET);

        ItemStack bodyMenu = makeBigItem(plugin.configStringGetter("pose-menu.body.name","&6Body",plugin), plugin.configStringGetter("pose-menu.body.description","&9Change the pose of the body.",plugin), Material.LEATHER_CHESTPLATE);

        ItemStack leftHandMenu = makeBigItem(plugin.configStringGetter("pose-menu.left-hand.name","&6Left Hand",plugin), plugin.configStringGetter("pose-menu.left-hand.description","&9Change the pose of the left hand.",plugin), Material.SHIELD);

        ItemStack rightHandMenu = makeBigItem(plugin.configStringGetter("pose-menu.right-hand.name","&6Right Hand",plugin), plugin.configStringGetter("pose-menu.right-hand.description","&9Change the pose of the right hand.",plugin), Material.WOODEN_SWORD);

        ItemStack leftLegMenu = makeBigItem(plugin.configStringGetter("pose-menu.left-leg.name","&6Left Leg",plugin), plugin.configStringGetter("pose-menu.left-leg.description","&9Change the pose of the left leg.",plugin), Material.STICK);

        ItemStack rightLegMenu = makeBigItem(plugin.configStringGetter("pose-menu.right-leg.name","&6Right Leg",plugin), plugin.configStringGetter("pose-menu.right-leg.description","&9Change the pose of the right leg.",plugin), Material.BLAZE_ROD);

        ItemStack resetPose = plugin.makeSmallItem(plugin.configStringGetter("pose-menu.reset-pose","&6Reset Pose",plugin), Material.CRAFTING_TABLE);

        ItemStack back = makeBackItem();

        poseMenu.setItem(11, headMenu);
        poseMenu.setItem(20, bodyMenu);
        poseMenu.setItem(19, leftHandMenu);
        poseMenu.setItem(21, rightHandMenu);
        poseMenu.setItem(28, leftLegMenu);
        poseMenu.setItem(30, rightLegMenu);
        poseMenu.setItem(16, resetPose);
        poseMenu.setItem(8, back);

        player.openInventory(poseMenu);
    }

    public void changeName(Player player){
        player.closeInventory();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("main-menu.change-name.message","&6Write the new name with color codes (&) into the chat.",plugin)));
        plugin.setChatPlayer(player);
    }

    public void eraseName(Player player){
        plugin.getEntity(player).setCustomNameVisible(false);
        plugin.getEntity(player).setCustomName(null);
    }


    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){

        final String MAIN_MENU = ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("name-of-the-creation-menu","&eArmor Stand Creator",plugin));
        final String TOOLKIT_MENU = ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("name-of-the-main-menu","&eArmor Stand Menu",plugin));

        if ((event.getView().getTitle().equals(MAIN_MENU)) && !event.getClickedInventory().equals(event.getWhoClicked().getInventory()) && (event.getCurrentItem().getType().equals(Material.ARMOR_STAND))){

            Player player = (Player) event.getWhoClicked();

            if (!event.getClickedInventory().equals(player.getInventory())){
                event.setCancelled(true);
            }

            if (event.isRightClick()){
                return;
            }

            Location location = plugin.getLocation();

            Location loc = new Location(location.getWorld(), location.getX(), location.getY()+1, location.getZ());
            ArmorStand armorstand = (ArmorStand) Bukkit.getWorld(location.getWorld().getUID()).spawnEntity(loc, EntityType.ARMOR_STAND);

            if (plugin.getConfig().getBoolean("show-default-name-on-creation")){
                String name = plugin.getConfig().getString("default-name");
                if (name == null){
                    armorstand.setCustomName("Armor Stand");
                    armorstand.setCustomNameVisible(true);
                }else{
                    armorstand.setCustomName(name);
                    armorstand.setCustomNameVisible(true);
                }
            }

        }else if (event.getView().getTitle().equals(TOOLKIT_MENU)){

            Player player = (Player) event.getWhoClicked();

            if (!event.getClickedInventory().equals(player.getInventory())){
                event.setCancelled(true);
            }

            if (event.isRightClick()){
                return;
            }

            if (event.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE)){
                openApparelMenu(player);
            }

            if (event.getCurrentItem().getType().equals(Material.ARMOR_STAND)){
                openPoseMenu(player);
            }

            if (event.getCurrentItem().getType().equals(Material.NAME_TAG)){
                changeName(player);
            }

            if (event.getCurrentItem().getType().equals(Material.PAPER)){
                eraseName(player);
            }

            if (event.getCurrentItem().getType().equals(Material.CRAFTING_TABLE)){
                Location location = plugin.getEntity(player).getLocation();
                plugin.getEntity(player).remove();
                Bukkit.getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.ARMOR_STAND);
                player.closeInventory();
            }

            if (event.getCurrentItem().getType().equals(Material.BARRIER)){
                plugin.deleteEntity(player);
                player.closeInventory();
            }
        }

    }

}
