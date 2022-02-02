package me.rxndmz.voidskills.Skills.Mining;

import me.rxndmz.voidskills.Enchantments.CustomEnchants;
import me.rxndmz.voidskills.Events.XPGained;
import me.rxndmz.voidskills.VoidSkills;
import net.raidstone.wgevents.WorldGuardEvents;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class Mining implements Listener {

    private static VoidSkills plugin = VoidSkills.getPlugin();

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        Player player = e.getPlayer();
        boolean doesRespawn = false;
        try {
            if (WorldGuardEvents.isPlayerInAnyRegion(player.getUniqueId(), "Wilderness") && !player.isSneaking()) {
                doesRespawn = true;
            }
            if (player.getInventory().getItemInMainHand().getType().toString().endsWith("PICKAXE")) {
                e.setCancelled(true);
                ItemStack pickaxe = e.getPlayer().getInventory().getItemInMainHand();
                int mining_lvl = plugin.sqlHandler.getMiningLvl(player);
                Block block = e.getBlock();
                ItemStack itemInHand = player.getInventory().getItemInMainHand();
                switch (block.getType()) {
                    case STONE: {
                        blockBroken(player, block, block.getDrops(itemInHand), Material.STONE, 4L, ThreadLocalRandom.current().nextInt(5, 10) + 1, pickaxe, doesRespawn, e.getExpToDrop());
                        break;
                    }
                    case COAL_ORE: {
                        if (mining_lvl >= 3) {
                            blockBroken(player, block, block.getDrops(itemInHand), Material.COAL_ORE, 7L, ThreadLocalRandom.current().nextInt(15, 25) + 1, pickaxe, doesRespawn, e.getExpToDrop());
                        } else {
                            player.sendMessage(format("&cYou need to be Mining level &l&n3&r&c or above to mine this!"));
                        }
                        break;
                    }
                    case IRON_ORE:{
                        if (mining_lvl >= 5) {
                            if (itemInHand.getType() != Material.WOODEN_PICKAXE) {
                                blockBroken(player, block, block.getDrops(itemInHand), Material.IRON_ORE, 10L, ThreadLocalRandom.current().nextInt(35, 45) + 1, pickaxe, doesRespawn, e.getExpToDrop());
                            } else {
                                player.sendMessage(format("&cYou need at least a &fStone Pickaxe &cto mine this!"));
                            }
                        } else {
                            player.sendMessage(format("&cYou need to be &fMining level 5 &cto mine this!"));
                        }
                        break;
                    }
                    case GOLD_ORE:{
                        if (mining_lvl >= 10) {
                            if (itemInHand.getType() != Material.WOODEN_PICKAXE || itemInHand.getType() != Material.STONE_PICKAXE) {
                                blockBroken(player, block, block.getDrops(itemInHand), Material.GOLD_ORE, 20L, ThreadLocalRandom.current().nextInt(55, 75) + 1, pickaxe, doesRespawn, e.getExpToDrop());
                            } else {
                                player.sendMessage(format("&cYou need at least a &fIron Pickaxe &cto mine this!"));
                            }
                        } else {
                            player.sendMessage(format("&cYou need to be &fMining level 10 &cto mine this!"));
                        }
                        break;
                    }
                    case LAPIS_ORE:{
                        if (mining_lvl >= 7) {
                            if (itemInHand.getType() != Material.WOODEN_PICKAXE) {
                                blockBroken(player, block, block.getDrops(itemInHand), Material.LAPIS_ORE, 20L, ThreadLocalRandom.current().nextInt(40, 60) + 1, pickaxe, doesRespawn, e.getExpToDrop());
                            } else {
                                player.sendMessage(format("&cYou need at least a &fStone Pickaxe &cto mine this!"));
                            }
                        } else {
                            player.sendMessage(format("&cYou need to be &fMining level 7 &cto mine this!"));
                        }
                        break;
                    }
                    case REDSTONE_ORE: {
                        if (mining_lvl >= 15) {
                            if (itemInHand.getType() != Material.WOODEN_PICKAXE || itemInHand.getType() != Material.STONE_PICKAXE) {
                                blockBroken(player, block, block.getDrops(itemInHand), Material.REDSTONE_ORE, 30L, ThreadLocalRandom.current().nextInt(80, 100) + 1, pickaxe, doesRespawn, e.getExpToDrop());
                            } else {
                                player.sendMessage(format("&cYou need at least a &fIron Pickaxe &cto mine this!"));
                            }
                        } else {
                            player.sendMessage(format("&cYou need to be &fMining level 15 &cto mine this!"));
                        }
                        break;
                    }
                    case DIAMOND_ORE: {
                        if (mining_lvl >= 20) {
                            if (itemInHand.getType() != Material.WOODEN_PICKAXE || itemInHand.getType() != Material.STONE_PICKAXE) {
                                blockBroken(player, block, block.getDrops(itemInHand), Material.DIAMOND_ORE, 45L, ThreadLocalRandom.current().nextInt(135, 175) + 1, pickaxe, doesRespawn, e.getExpToDrop());
                            } else {
                                player.sendMessage(format("&cYou need at least a &fIron Pickaxe &cto mine this!"));
                            }
                        } else {
                            player.sendMessage(format("&cYou need to be &fMining level 20 &cto mine this!"));
                        }
                        break;
                    }
                    case EMERALD_ORE: {
                        if (mining_lvl >= 25) {
                            if (itemInHand.getType() != Material.WOODEN_PICKAXE || itemInHand.getType() != Material.STONE_PICKAXE) {
                                blockBroken(player, block, block.getDrops(itemInHand), Material.EMERALD_ORE, 60L, ThreadLocalRandom.current().nextInt(200, 300) + 1, pickaxe, doesRespawn, e.getExpToDrop());
                            } else {
                                player.sendMessage(format("&cYou need at least a &fIron Pickaxe &cto mine this!"));
                            }
                        } else {
                            player.sendMessage(format("&cYou need to be &fMining level 25 &cto mine this!"));
                        }
                        break;
                    }
                    default: {
                        player.sendMessage(format("&cYou're not allowed to break that!"));
                    }
                }
            }
        } catch (Exception ignored) { }
    }

    private void blockBroken(Player player, Block block, Collection<ItemStack> drops, Material ore, Long delay, int xp, ItemStack pickaxe, boolean doesRespawn, int xpToDrop) {
        block.setType(Material.COBBLESTONE);
        player.giveExp(xpToDrop);
        if (pickaxe.getEnchantments().containsKey(CustomEnchants.PROFESSION)) {
            switch (pickaxe.getItemMeta().getEnchantLevel(CustomEnchants.PROFESSION)) {
                case 1:{
                    plugin.getServer().getPluginManager().callEvent(new XPGained(Math.round(xp * 1.10f), "MINING", player));
                    break;
                }
                case 2:{
                    plugin.getServer().getPluginManager().callEvent(new XPGained(Math.round(xp * 1.20f), "MINING", player));
                    break;
                }
                case 3:{
                    plugin.getServer().getPluginManager().callEvent(new XPGained(Math.round(xp * 1.30f), "MINING", player));
                    break;
                }
                case 4:{
                    plugin.getServer().getPluginManager().callEvent(new XPGained(Math.round(xp * 1.40f), "MINING", player));
                    break;
                }
                case 5:{
                    plugin.getServer().getPluginManager().callEvent(new XPGained(Math.round(xp * 1.50f), "MINING", player));
                    break;
                }
            }
        } else {
            plugin.getServer().getPluginManager().callEvent(new XPGained(xp, "MINING", player));
        }

        /*
         * Drops are given here
         */

        drops.forEach(drop -> {
            if (pickaxe.getEnchantments().containsKey(CustomEnchants.SMELTING)) {
                int random = ThreadLocalRandom.current().nextInt(1, 3) + 1;
                switch (drop.getType()) {
                    case COBBLESTONE:{
                        player.getInventory().addItem(new ItemStack(Material.STONE, random));
                        break;
                    }
                    case IRON_ORE:{
                        player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, random));
                        player.giveExp(1);
                        break;
                    }
                    case GOLD_ORE:{
                        player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, random));
                        player.giveExp(2);
                        break;
                    }
                    default:{
                        player.getInventory().addItem(drop);
                    }
                }
            } else {
                player.getInventory().addItem(drop);
            }
        });

        boolean wasReplenished = false; // used for checking if the block got replenished

        /*
         * Replenish Enchant
         */

        if (pickaxe.getEnchantments().containsKey(CustomEnchants.REPLENISH)) {
            switch (pickaxe.getItemMeta().getEnchantLevel(CustomEnchants.REPLENISH)) {
                case 1:{
                    if (Math.random() <= 0.05) {
                        block.setType(ore);
                        block.getWorld().playSound(block.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1.00f, 1.00f);
                        block.getWorld().spawnParticle(Particle.CRIT, block.getLocation(), 100);
                        wasReplenished = true;
                    }
                    break;
                }
                case 2:{
                    if (Math.random() <= 0.10) {
                        block.setType(ore);
                        block.getWorld().playSound(block.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1.00f, 1.00f);
                        block.getWorld().spawnParticle(Particle.CRIT, block.getLocation(), 100);
                        wasReplenished = true;
                    }
                    break;
                }
                case 3:{
                    if (Math.random() <= 0.15) {
                        block.setType(ore);
                        block.getWorld().playSound(block.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1.00f, 1.00f);
                        block.getWorld().spawnParticle(Particle.CRIT, block.getLocation(), 100);
                        wasReplenished = true;
                    }
                    break;
                }
            }
        }
        if (doesRespawn && !wasReplenished) {
            new ReplaceOre(block, ore).runTaskLater(plugin, 20*delay);
        }
    }

    /*
     * used for chat colours
     */
    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
