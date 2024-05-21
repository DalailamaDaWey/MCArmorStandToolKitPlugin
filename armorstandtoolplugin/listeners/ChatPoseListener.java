package me.jamesbrown.armorstandtoolplugin.listeners;

import me.jamesbrown.armorstandtoolplugin.ArmorStandToolPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.util.EulerAngle;
import java.util.ArrayList;

public class ChatPoseListener implements Listener {

    ArmorStandToolPlugin plugin;

    public ChatPoseListener(ArmorStandToolPlugin plugin) {
        this.plugin = plugin;
    }

    private static class coords{
        int x;
        int y;
        int z;
    }

    public int calculate(ArrayList<Integer> intArr){
        int num = intArr.get(intArr.size()-1);
        intArr.remove(intArr.size()-1);
        if (intArr.size()==2){
            num+= (int) (intArr.get(1)*Math.pow(10,1));
            intArr.remove(intArr.size()-1);
            num+= (int) (intArr.get(0)*Math.pow(10,2));
        }else if (intArr.size()==1) {
            num+= (int) (intArr.get(0)*Math.pow(10,1));
        }
        return num;
    }

    private boolean check(char[] string, Player player){
        int numberOfCommas=0;
        int number;
        ArrayList<Integer> numArr = new ArrayList<>();

        if (string.length < 5){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.message-in-wrong-format", "&cYou have written the message in the wrong format!", plugin)));
            return false;
        }

        for (int i = 0; i < string.length; i++) {

            if (i == string.length-1 && string[i] != ' '){
                numArr.add(Character.getNumericValue(string[i]));
            }

            if (string[i] == ',' || i == string.length-1) {
                number = calculate(numArr);
                numArr = new ArrayList<>();
                if (number > 360) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.too-big-of-a-number", "&cYou have written too big of a number! Only 0-360 values are accepted!", plugin)));
                    return false;
                }

                if (number < 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.too-small-of-a-number", "&cYou have written too small of a number! Only 0-360 values are accepted!", plugin)));
                    return false;
                }

                if (i != string.length-1){
                    numberOfCommas++;
                }

                if (numberOfCommas > 2) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.message-in-wrong-format", "&cYou have written the message in the wrong format!", plugin)));
                    return false;
                }
            } else {

                if (((string[i] - '0') < 0 || (string[i] - '0') > 9) && (string[i] != ' ')){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.message-with-unacceptable-characters", "&cYou have written unacceptable characters into the message! Only numbers, commas and spaces are tolerated!", plugin)));
                    return false;
                }

                if (string[i] == ' '){
                    continue;
                }
                numArr.add(Character.getNumericValue(string[i]));
            }
        }

        if (numberOfCommas!=2){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.configStringGetter("messages.message-in-wrong-format", "&cYou have written the message in the wrong format!", plugin)));
            return false;
        }

        return true;
    }

    private void read(char[] string, coords c){
        int i = 0;
        ArrayList<Integer> intArray = new ArrayList<>();

        for (int a = 0; (a+i) < string.length; a++){
            if (string[a+i]==','){
                i=a;
                c.x = calculate(intArray);
                break;
            }
            if (string[a] == ' '){
                continue;
            }
            intArray.add(Character.getNumericValue(string[a]));
        }

        intArray = new ArrayList<>();

        for (int a = 1; (a+i) < string.length; a++){
            if (string[a+i]==','){
                i+=a;
                c.y = calculate(intArray);
                break;
            }
            if (string[a+i] == ' '){
                continue;
            }
            intArray.add(Character.getNumericValue(string[a+i]));
        }

        intArray = new ArrayList<>();

        for (int a = 1; (a+i) < string.length; a++){
            if (a+i == string.length-1){
                intArray.add(Character.getNumericValue(string[a+i]));
                c.z = calculate(intArray);
                break;
            }
            if (string[a+i] == ' '){
                continue;
            }
            intArray.add(Character.getNumericValue(string[a+i]));
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (plugin.getIfPoseChat(player)){
            event.setCancelled(true);
            String name = event.getMessage();
            char[] nameArr = name.toCharArray();
            coords pose = new coords();

            if (!check(nameArr, player)) {
                plugin.deletePoseChat(player);
                return;
            }
            read(nameArr, pose);

            switch(plugin.getPoseChat(player)){
                case HEAD:
                    plugin.getEntity(player).setHeadPose(new EulerAngle(Math.toRadians(pose.x),Math.toRadians(pose.y),Math.toRadians(pose.z)));
                    plugin.deletePoseChat(player);
                    break;
                case BODY:
                    plugin.getEntity(player).setBodyPose(new EulerAngle(Math.toRadians(pose.x),Math.toRadians(pose.y),Math.toRadians(pose.z)));
                    plugin.deletePoseChat(player);
                    break;
                case LEFT_HAND:
                    plugin.getEntity(player).setLeftArmPose(new EulerAngle(Math.toRadians(pose.x),Math.toRadians(pose.y),Math.toRadians(pose.z)));
                    plugin.deletePoseChat(player);
                    break;
                case RIGHT_HAND:
                    plugin.getEntity(player).setRightArmPose(new EulerAngle(Math.toRadians(pose.x),Math.toRadians(pose.y),Math.toRadians(pose.z)));
                    plugin.deletePoseChat(player);
                    break;
                case LEFT_LEG:
                    plugin.getEntity(player).setLeftLegPose(new EulerAngle(Math.toRadians(pose.x),Math.toRadians(pose.y),Math.toRadians(pose.z)));
                    plugin.deletePoseChat(player);
                    break;
                case RIGHT_LEG:
                    plugin.getEntity(player).setRightLegPose(new EulerAngle(Math.toRadians(pose.x),Math.toRadians(pose.y),Math.toRadians(pose.z)));
                    plugin.deletePoseChat(player);
                    break;
            }
        }
    }
}
