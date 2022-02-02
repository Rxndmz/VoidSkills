package me.rxndmz.voidskills.Leveling;

import me.rxndmz.voidskills.Events.XPGained;
import me.rxndmz.voidskills.VoidSkills;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LevelHandler implements Listener {

    //TODO SP IS ON HOLD

    private VoidSkills plugin = VoidSkills.getPlugin();

    @EventHandler
    public void onXPGain(XPGained e) {
        Player player = e.getPlayer();
        int base_xp_needed = 1000;
        switch (e.getType()) {
            case "MINING":{
                int current_xp = plugin.sqlHandler.getMiningXp(player);
                int current_lvl = plugin.sqlHandler.getMiningLvl(player);
                int total_lvl = plugin.sqlHandler.getTotalLevel(player);
                if (current_lvl != 50)
                    if (current_xp >= base_xp_needed * Math.pow(2.1, current_lvl - 1)) {
                        current_lvl++;
                        total_lvl++;
                        plugin.sqlHandler.setTotalLvl(player, total_lvl);
                        plugin.sqlHandler.setMiningLvl(player, current_lvl);
                        sendPlayerLevelAlert(player, "Mining", current_lvl);
                    }
                break;
            }
            case "FARMING":{
                int current_xp = plugin.sqlHandler.getFarmingXp(player);
                int current_lvl = plugin.sqlHandler.getFarmingLvl(player);
                int total_lvl = plugin.sqlHandler.getTotalLevel(player);
                if (current_lvl != 50)
                    if (current_xp >= base_xp_needed * Math.pow(2.1, current_lvl - 1)) {
                        current_lvl++;
                        total_lvl++;
                        plugin.sqlHandler.setTotalLvl(player, total_lvl);
                        plugin.sqlHandler.setFarmingLvl(player, current_lvl);
                        sendPlayerLevelAlert(player, "Farming", current_lvl);
                    }
                break;
            }
            case "FISHING":{
                int current_xp = plugin.sqlHandler.getFishingXp(player);
                int current_lvl = plugin.sqlHandler.getFishingLvl(player);
                int total_lvl = plugin.sqlHandler.getTotalLevel(player);
                if (current_lvl != 50)
                    if (current_xp >= base_xp_needed * Math.pow(2.1, current_lvl - 1)) {
                        current_lvl++;
                        total_lvl++;
                        plugin.sqlHandler.setTotalLvl(player, total_lvl);
                        plugin.sqlHandler.setFishingLvl(player, current_lvl);
                        sendPlayerLevelAlert(player, "Fishing", current_lvl);
                    }
                break;
            }
            case "HUNTING":{
                int current_xp = plugin.sqlHandler.getHuntingXp(player);
                int current_lvl = plugin.sqlHandler.getHuntingLvl(player);
                int total_lvl = plugin.sqlHandler.getTotalLevel(player);
                if (current_lvl != 50)
                    if (current_xp >= base_xp_needed * Math.pow(2.1, current_lvl - 1)) {
                        current_lvl++;
                        total_lvl++;
                        plugin.sqlHandler.setTotalLvl(player, total_lvl);
                        plugin.sqlHandler.setHuntingLvl(player, current_lvl);
                        sendPlayerLevelAlert(player, "Hunting", current_lvl);
                    }
                break;
            }
            default:{
                player.sendMessage("ERROR");
            }
        }
    }

    private void sendPlayerLevelAlert(Player player, String skill, int lvl) {
        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1.00f, 1.00f);
        player.sendTitle(format("&a&lLevel Up!"), format("&a" + skill + " has increased to &l&n" + lvl + "!"), 20, 80, 20);
    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
