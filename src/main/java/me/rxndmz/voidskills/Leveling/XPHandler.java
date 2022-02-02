package me.rxndmz.voidskills.Leveling;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import me.rxndmz.voidskills.Events.XPGained;
import me.rxndmz.voidskills.VoidSkills;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class XPHandler implements Listener {

    private VoidSkills plugin = VoidSkills.getPlugin();

    @EventHandler
    public void GainedXP(XPGained e) {
        Player player = e.getPlayer();
        switch (e.getType()) {
            case "MINING":
                addMiningXP(player, e.getAmount());
                break;
            case "FARMING":
                addFarmingXP(player, e.getAmount());
                break;
            case "HUNTING":
                addHuntingXP(player, e.getAmount());
                break;
            case "FISHING":
                addFishingXP(player, e.getAmount());
                break;
            default:
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.00f, 1.00f);
                player.sendMessage(format("&c&lERROR GAINING XP"));
        }
    }

    private void addHuntingXP(Player player, int xpAmount) {
        int current_xp = plugin.sqlHandler.getHuntingXp(player);
        plugin.sqlHandler.setHuntingXp(player, current_xp + xpAmount);
        ActionBarAPI.sendActionBar(player, format("&bHunting &a+" + xpAmount + "xp &2(" + plugin.sqlHandler.getHuntingXp(player) + "/" + (Math.round(1000 * Math.pow(2.1, plugin.sqlHandler.getHuntingLvl(player) - 1) * 100) / 100) + ")"));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.00f, 1.00f);

    }

    private void addFishingXP(Player player, int xpAmount) {
        int current_xp = plugin.sqlHandler.getFishingXp(player);
        plugin.sqlHandler.setFishingXp(player, current_xp + xpAmount);
        ActionBarAPI.sendActionBar(player, format("&bFishing &a+" + xpAmount + "xp &2(" + plugin.sqlHandler.getFishingXp(player) + "/" + (Math.round(1000 * Math.pow(2.1, plugin.sqlHandler.getFishingLvl(player) - 1) * 100) / 100) + ")"));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.00f, 1.00f);
    }

    private void addFarmingXP(Player player, int xpAmount) {
        int current_xp = plugin.sqlHandler.getFarmingXp(player);
        plugin.sqlHandler.setFarmingXp(player, current_xp + xpAmount);
        ActionBarAPI.sendActionBar(player, format("&dFarming &f+" + xpAmount + "xp &d(" + plugin.sqlHandler.getFarmingXp(player) + "/" + (Math.round(1000 * Math.pow(2.1, plugin.sqlHandler.getFarmingLvl(player) - 1) * 100) / 100) + ")"));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.00f, 1.00f);
    }

    private void addMiningXP(Player player, int xpAmount) {
        int current_xp = plugin.sqlHandler.getMiningXp(player);
        plugin.sqlHandler.setMiningXp(player, current_xp + xpAmount);
        ActionBarAPI.sendActionBar(player, format("&dMining &f+" + xpAmount + "xp &d(" + plugin.sqlHandler.getMiningXp(player) + "/" + (Math.round(1000 * Math.pow(2.1, plugin.sqlHandler.getMiningLvl(player) - 1) * 100) / 100) + ")"));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.00f, 1.00f);
    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
