package me.jamesbrown.armorstandtoolplugin.commands;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ToolCommand implements CommandExecutor {

    ArmorStandToolPlugin plugin;

    public ToolCommand(ArmorStandToolPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player){
            if (commandSender.hasPermission("tool-permission")){
                ItemStack toolkit = new ItemStack(Material.STICK);
                ItemMeta toolkitMeta = toolkit.getItemMeta();



                toolkitMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("name-of-the-tool", "&5Toolkit", plugin)));
                toolkitMeta.getPersistentDataContainer().set(plugin.getItemTag(), PersistentDataType.BOOLEAN, true);
                toolkit.setItemMeta(toolkitMeta);
                player.getInventory().addItem(toolkit);
            }else{
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.no-permission-message", "&cYou do not have the permission to do this!", plugin)));
            }
        }else{
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.only-player-can-use-this-command-message", "&cOnly a player can use this!", plugin)));
        }

        return true;
    }
}
