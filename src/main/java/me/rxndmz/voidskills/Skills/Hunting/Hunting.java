package me.rxndmz.voidskills.Skills.Hunting;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Hunting implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity) {
            Player player = (Player) e.getDamager();
            LivingEntity mob = (LivingEntity) e.getEntity();
        }
    }

}
