package me.rxndmz.voidskills;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener {

    // This class will init playerdata and add them to the database
    private VoidSkills plugin = VoidSkills.getPlugin();

    @EventHandler
    public void playerjoined(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!plugin.sqlHandler.skillDataExists(player)) {
            plugin.sqlHandler.setupSkillData(player);
        }
    }

}