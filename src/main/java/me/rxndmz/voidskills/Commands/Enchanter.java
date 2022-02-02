package me.rxndmz.voidskills.Commands;

import me.rxndmz.voidskills.Enchantments.EnchantsGUI;
import me.rxndmz.voidskills.VoidSkills;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Enchanter implements CommandExecutor {

    private static VoidSkills plugin = VoidSkills.getPlugin();
    private static String serverPrefix = VoidSkills.getPrefix();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            ItemStack heldItem = player.getInventory().getItemInMainHand();
            switch (heldItem.getType()) {
                case WOODEN_PICKAXE:
                case STONE_PICKAXE:
                case GOLDEN_PICKAXE:
                case DIAMOND_PICKAXE:
                case IRON_PICKAXE:
                case STONE_SWORD:
                case DIAMOND_SWORD:
                case GOLDEN_SWORD:
                case IRON_SWORD:
                case WOODEN_SWORD:
                case STONE_SHOVEL:
                case DIAMOND_SHOVEL:
                case GOLDEN_SHOVEL:
                case IRON_SHOVEL:
                case WOODEN_SHOVEL:
                case DIAMOND_AXE:
                case GOLDEN_AXE:
                case IRON_AXE:
                case STONE_AXE:
                case WOODEN_AXE:
                case DIAMOND_HOE:
                case GOLDEN_HOE:
                case IRON_HOE:
                case STONE_HOE:
                case WOODEN_HOE:
                case DIAMOND_HELMET:
                case GOLDEN_HELMET:
                case IRON_HELMET:
                case LEATHER_HELMET:
                case DIAMOND_CHESTPLATE:
                case GOLDEN_CHESTPLATE:
                case IRON_CHESTPLATE:
                case LEATHER_CHESTPLATE:
                case LEATHER_LEGGINGS:
                case DIAMOND_LEGGINGS:
                case GOLDEN_LEGGINGS:
                case IRON_LEGGINGS:
                case DIAMOND_BOOTS:
                case GOLDEN_BOOTS:
                case IRON_BOOTS:
                case LEATHER_BOOTS:
                case BOW:
                case FISHING_ROD:{
                    player.openInventory(EnchantsGUI.getEnchantsGUI(player, heldItem));
                    break;
                }
                default:{
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.00f, 1.00f);
                    player.sendMessage(serverPrefix + format("&fError please hold an Enchantable item!"));
                }
            }
        }
        return false;
    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
