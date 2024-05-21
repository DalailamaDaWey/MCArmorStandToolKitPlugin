package me.jamesbrown.armorstandtoolplugin;

import me.jamesbrown.armorstandtoolplugin.commands.ToolCommand;
import me.jamesbrown.armorstandtoolplugin.listeners.*;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ArmorStandToolPlugin extends JavaPlugin {

    private Location location;
    private final ArrayList<Player> nameChatPlayers = new ArrayList<>();
    private final HashMap<Player, ArmorStand> playerArmorStands = new HashMap<>();
    public enum part{
        HEAD,
        BODY,
        LEFT_HAND,
        RIGHT_HAND,
        LEFT_LEG,
        RIGHT_LEG
    }
    private final HashMap<Player, part> poseChatPlayers = new HashMap<>();
    private NamespacedKey itemTag;

    public NamespacedKey getItemTag() {
        return itemTag;
    }

    public String configStringGetter(String fromWhere, String defaultMessage, ArmorStandToolPlugin plugin){
        String message = plugin.getConfig().getString(fromWhere);

        if (message == null){
            return defaultMessage;
        }
        return message;
    }

    public ItemStack makeSmallItem(String name, Material item){
        ItemStack menu = new ItemStack(item);
        ItemMeta menuMeta = menu.getItemMeta();

        menuMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        menu.setItemMeta(menuMeta);

        return menu;
    }

    public void setEntity(Player player, ArmorStand armorstand){
        playerArmorStands.put(player, armorstand);
    }

    public void deleteEntity(Player player){
        playerArmorStands.remove(player);
    }

    public ArmorStand getEntity(Player player){
        return playerArmorStands.get(player);
    }

    public void setPoseChat(Player player, part bodyPart){
        poseChatPlayers.put(player, bodyPart);
    }

    public boolean getIfPoseChat(Player player){
        return poseChatPlayers.containsKey(player);
    }

    public part getPoseChat(Player player){
        part bodyPart = poseChatPlayers.get(player);
        poseChatPlayers.remove(player);
        return bodyPart;
    }

    public void deletePoseChat(Player player){
        poseChatPlayers.remove(player);
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }

    public void setChatPlayer(Player player){
        nameChatPlayers.add(player);
    }

    public boolean getIfNameChatPlayer(Player player){
        boolean isIn = nameChatPlayers.contains(player);
        nameChatPlayers.remove(player);
        return isIn;
    }

    public void openToolkitMenu(Player player){
        Inventory toolkitMenu = Bukkit.createInventory(player,9, ChatColor.translateAlternateColorCodes('&', configStringGetter("name-of-the-main-menu","&eArmor Stand Menu",this)));

        ItemStack modifyApparel = makeSmallItem(configStringGetter("main-menu.change-apparel","&9Change Apparel",this), Material.LEATHER_CHESTPLATE);

        ItemStack modifyPose = makeSmallItem(configStringGetter("main-menu.change-pose","&9Change Pose",this), Material.ARMOR_STAND);

        ItemStack setName = makeSmallItem(configStringGetter("main-menu.change-name.name","&9Change Name",this), Material.NAME_TAG);

        ItemStack eraseName = makeSmallItem(configStringGetter("main-menu.erase-name","&dErase Name",this), Material.PAPER);

        ItemStack reset = makeSmallItem(configStringGetter("main-menu.reset","&dReset",this), Material.CRAFTING_TABLE);

        ItemStack close = makeSmallItem(configStringGetter("main-menu.close","&cClose",this), Material.BARRIER);


        toolkitMenu.setItem(0, modifyApparel);
        toolkitMenu.setItem(1, modifyPose);
        toolkitMenu.setItem(3, setName);
        toolkitMenu.setItem(4, eraseName);
        toolkitMenu.setItem(6, reset);
        toolkitMenu.setItem(8, close);
        player.openInventory(toolkitMenu);
    }

    public void onEnable() {
        itemTag = new NamespacedKey(this, "armorstandtool");
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("toolkit")).setExecutor(new ToolCommand(this));
        getServer().getPluginManager().registerEvents(new ClickToolListener(this), this);
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        getServer().getPluginManager().registerEvents(new ApparelListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatNameListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatPoseListener(this),this);
        getServer().getPluginManager().registerEvents(new PoseListener(this),this);
        getServer().getPluginManager().registerEvents(new DropListener(this), this);
    }

}
