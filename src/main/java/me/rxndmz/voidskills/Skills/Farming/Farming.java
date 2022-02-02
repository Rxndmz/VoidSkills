package me.rxndmz.voidskills.Skills.Farming;

import me.rxndmz.voidskills.Events.XPGained;
import me.rxndmz.voidskills.VoidSkills;
import net.raidstone.wgevents.WorldGuardEvents;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class Farming implements Listener {

    private VoidSkills plugin = VoidSkills.getPlugin();

    @EventHandler
    public void onFarm(BlockBreakEvent e) {
        Player player = e.getPlayer();
        boolean doesRespawn = false;
        try {
            // Remember to remove the player sneak check
            if (WorldGuardEvents.isPlayerInAnyRegion(player.getUniqueId(), "Wilderness") && !player.isSneaking()) {
                doesRespawn = true;
            }
            switch (e.getBlock().getType()) {
                case WHEAT: {
                    if (player.getInventory().getItemInMainHand().getType().toString().endsWith("HOE")) {
                        e.setCancelled(true);
                        Block block = e.getBlock();
                        ItemStack itemInHand = player.getInventory().getItemInMainHand();
                        int farming_lvl = plugin.sqlHandler.getFarmingLvl(player);
                        if (checkAge(e.getBlock()))
                            blockBroken(player, block, block.getDrops(itemInHand), Material.WHEAT, Material.WHEAT, 10L, 10, itemInHand, doesRespawn);
                    } else {
                        e.setCancelled(true);
                        player.sendMessage(format("&cYou need a &fHoe &cto mine this!"));
                    }
                    break;
                }
                case POTATOES: {
                    if (player.getInventory().getItemInMainHand().getType().toString().endsWith("HOE")) {
                        e.setCancelled(true);
                        Block block = e.getBlock();
                        ItemStack itemInHand = player.getInventory().getItemInMainHand();
                        int farming_lvl = plugin.sqlHandler.getFarmingLvl(player);
                        //if (farming_lvl >= 5) {
                        if (checkAge(e.getBlock()))
                            blockBroken(player, block, block.getDrops(itemInHand), Material.POTATOES, Material.POTATOES, 20L, 25, itemInHand, doesRespawn);
                        //}
                    } else {
                        e.setCancelled(true);
                        player.sendMessage(format("&cYou need a &fHoe &cto mine this!"));
                    }
                    break;
                }
                case CARROTS: {
                    if (player.getInventory().getItemInMainHand().getType().toString().endsWith("HOE")) {
                        e.setCancelled(true);
                        Block block = e.getBlock();
                        ItemStack itemInHand = player.getInventory().getItemInMainHand();
                        int farming_lvl = plugin.sqlHandler.getFarmingLvl(player);
                        //if (farming_lvl >= 10) {
                        if (checkAge(e.getBlock()))
                            blockBroken(player, block, block.getDrops(itemInHand), Material.CARROTS, Material.CARROTS, 30L, 50, itemInHand, doesRespawn);
                        //}
                    } else {
                        e.setCancelled(true);
                        player.sendMessage(format("&cYou need a &fHoe &cto mine this!"));
                    }
                    break;
                }
                case MELON: {
                    if (player.getInventory().getItemInMainHand().getType().toString().endsWith("HOE") || player.getInventory().getItemInMainHand().getType().toString().endsWith("_AXE")) {
                        e.setCancelled(true);
                        Block block = e.getBlock();
                        ItemStack itemInHand = player.getInventory().getItemInMainHand();
                        int farming_lvl = plugin.sqlHandler.getFarmingLvl(player);
                        //if (farming_lvl >= 15) {
                        blockBroken(player, block, block.getDrops(itemInHand), Material.MELON, Material.AIR, 5L, 100, itemInHand, doesRespawn);
                        //}
                    } else {
                        e.setCancelled(true);
                        player.sendMessage(format("&cYou need a &fHoe &cto mine this!"));
                    }
                    break;
                }
                case PUMPKIN: {
                    if (player.getInventory().getItemInMainHand().getType().toString().endsWith("HOE") || player.getInventory().getItemInMainHand().getType().toString().endsWith("_AXE")) {
                        e.setCancelled(true);
                        Block block = e.getBlock();
                        ItemStack itemInHand = player.getInventory().getItemInMainHand();
                        int farming_lvl = plugin.sqlHandler.getFarmingLvl(player);
                        //if (farming_lvl >= 20) {
                        blockBroken(player, block, block.getDrops(itemInHand), Material.PUMPKIN, Material.AIR, 5L, 150, itemInHand, doesRespawn);
                        //}
                    } else {
                        e.setCancelled(true);
                        player.sendMessage(format("&cYou need a &fHoe &cto mine this!"));
                    }
                    break;
                }
            }
        } catch (Exception ignored) { }
        if (doesRespawn) {
            e.setCancelled(true);
        }
    }

    private void blockBroken(Player player, Block block, Collection<ItemStack> drops, Material type, Material seed, Long delay, int xp, ItemStack itemInHand, boolean doesRespawn) {
        plugin.getServer().getPluginManager().callEvent(new XPGained(xp, "FARMING", player));
        drops.forEach(drop -> {
            if (drop.getType() == Material.PUMPKIN) {
                player.getInventory().addItem(new ItemStack(Material.LEGACY_PUMPKIN));
            } else {
                player.getInventory().addItem(drop);
            }
        });
        if (doesRespawn) {
            block.setType(seed);
            new ReplaceFarm(block, type).runTaskLater(plugin, 20*delay);
        }
    }

    private boolean checkAge(Block block) {
        boolean maxAge = false;
        if (block.getBlockData() instanceof Ageable) {
            Ageable age = (Ageable) block.getBlockData();
            if (age.getAge() == age.getMaximumAge()) {
                maxAge = true;
            }
        }
        return maxAge;
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
