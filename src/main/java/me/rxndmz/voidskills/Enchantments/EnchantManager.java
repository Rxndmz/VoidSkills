package me.rxndmz.voidskills.Enchantments;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EnchantManager {

    private static void addLore(ItemStack item, Enchantment ench, ChatColor color, int intLvl, boolean showNumbers) {
        List<String> lore = new CopyOnWriteArrayList<>();
        String level = levelConverter(intLvl);
        ItemMeta meta = item.getItemMeta();
        boolean check = false;
        if (meta.hasLore()) {
            lore = meta.getLore();
            check = true;
        } else {
            if (!showNumbers) {
                lore.add(color + ench.getName());
            } else {
                lore.add(color + ench.getName() + " " + level);
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        if (check) {
            boolean worked = false;
            for (String s : lore) {
                for (int i = 1; i <= 10; i++) {
                    String lvl = levelConverter(i);
                    if (s.equalsIgnoreCase(color.toString() + ench.getName() + " " + lvl) || s.equalsIgnoreCase(color.toString() + ench.getName())) {
                        int pos = lore.indexOf(s);
                        lore.set(pos, color + ench.getName() + " " + level);
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        worked = true;
                    }
                }
            }
            if (!worked) {
                if (!showNumbers) {
                    lore.add(color + ench.getName());
                } else {
                    lore.add(color + ench.getName() + " " + level);
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
        }
    }

    private static String levelConverter(int i) {
        String level = "";
        switch (i) {
            case 1:{
                level = "I";
                break;
            }
            case 2:{
                level = "II";
                break;
            }
            case 3:{
                level = "III";
                break;
            }
            case 4:{
                level = "IV";
                break;
            }
            case 5:{
                level = "V";
                break;
            }
            case 6:{
                level = "VI";
                break;
            }
            case 7:{
                level = "VII";
                break;
            }
            case 8:{
                level = "VIII";
                break;
            }
            case 9:{
                level = "IX";
                break;
            }
            case 10:{
                level = "X";
                break;
            }
        }
        return level;
    }

    //TODO create methods for each enchant (hasEnchant addEnchant)

    public static void addEfficiency(int level, ItemStack tool) {
        tool.addUnsafeEnchantment(Enchantment.DIG_SPEED, level);
    }

    public static void addFortune(int level, ItemStack tool) {
        tool.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, level);
    }

    public static void addUnbreaking(int level, ItemStack tool) {
        tool.addUnsafeEnchantment(Enchantment.DURABILITY, level);
    }

    public static void addExperience(int level, ItemStack tool) {
        tool.addUnsafeEnchantment(CustomEnchants.EXPERIENCE, level);
        addLore(tool, CustomEnchants.EXPERIENCE, ChatColor.GREEN, level, true);
    }

    public static void addSmelting(int level, ItemStack tool) {
        tool.addUnsafeEnchantment(CustomEnchants.SMELTING, level);
        addLore(tool, CustomEnchants.SMELTING, ChatColor.AQUA, level, true);
    }

    public static void addProfession(int level, ItemStack tool) {
        tool.addUnsafeEnchantment(CustomEnchants.PROFESSION, level);
        addLore(tool, CustomEnchants.PROFESSION, ChatColor.GREEN, level, true);
    }

    public static void addReplenish(int level, ItemStack tool) {
        tool.addUnsafeEnchantment(CustomEnchants.REPLENISH, level);
        addLore(tool, CustomEnchants.REPLENISH, ChatColor.GREEN, level, true);
    }

    public static void addEnchants(Enchantment ench, ItemStack item, int lvl, ChatColor color, boolean lvlMoreThanOne) {
        addLore(item, ench, color, lvl, lvlMoreThanOne);
        item.addUnsafeEnchantment(ench, lvl);
    }

}
