package me.rxndmz.voidskills.Enchantments;

import me.rxndmz.voidskills.VoidSkills;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnchantsGUI implements Listener {

    //TODO add enchant onClick functionality and then this is done. :)

    private static VoidSkills plugin = VoidSkills.getPlugin();
    private static String prefix = VoidSkills.getPrefix();

    /**
     * onClick handles adding the enchants ect
     */

    @EventHandler
    public void onClick (InventoryClickEvent e) {
        if (e.getCurrentItem() != null)
        if (e.getView().getTitle().equalsIgnoreCase("Enchanter")) {
            e.setCancelled(true);
            if (e.getView().getTopInventory().equals(e.getClickedInventory())) {
                Player player = (Player) e.getWhoClicked();
                switch (e.getCurrentItem().getType()) {
                    case SUGAR:{
                        // Efficiency Enchant
                        int playerXP = player.getTotalExperience();
                        int cost = 750;
                        int level = 0;
                        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.DIG_SPEED)) {
                            level = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DIG_SPEED);
                            if (level == 5) {
                                player.sendMessage(prefix + format("&fEnchant already at max level!"));
                                return;
                            }
                            cost = level*cost + cost;
                        }
                        if (playerXP >= cost) {
                            ItemStack _item = e.getCurrentItem();
                            ItemMeta _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getEfficiencyLore(level + 1));
                            _item.setItemMeta(_itemMeta);
                            player.setTotalExperience(0);
                            player.setLevel(0);
                            player.setExp(0);
                            player.giveExp(playerXP - cost);
                            _item = e.getView().getTopInventory().getItem(4);
                            _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getTotalXPLore(player));
                            _item.setItemMeta(_itemMeta);
                            EnchantManager.addEfficiency(level + 1, player.getInventory().getItemInMainHand());
                            if (level == 0) {
                                player.sendMessage(prefix + format("&fSuccessfully added Efficiency enchantment to your tool!"));
                            } else {
                                player.sendMessage(prefix + format("&fSuccessfully upgraded Efficiency from " + level + " to " + (level+1) + "!"));
                            }
                        } else {
                            player.sendMessage(prefix + format("&fYou don't have enough xp to get this enchantment!"));
                        }
                        break;
                    }
                    case GOLD_INGOT:{
                        // Fortune Enchant
                        int playerXP = player.getTotalExperience();
                        int cost = 1500;
                        int level = 0;
                        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                            level = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                            if (level == 3) {
                                player.sendMessage(format("&d&lVOIDMC&8» &fEnchant already at max level!"));
                                return;
                            }
                            cost = level*cost + cost;
                        }
                        if (playerXP >= cost) {
                            ItemStack _item = e.getCurrentItem();
                            ItemMeta _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getFortuneLore(level + 1));
                            _item.setItemMeta(_itemMeta);
                            player.setTotalExperience(0);
                            player.setLevel(0);
                            player.setExp(0);
                            player.giveExp(playerXP - cost);
                            _item = e.getView().getTopInventory().getItem(4);
                            _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getTotalXPLore(player));
                            _item.setItemMeta(_itemMeta);
                            EnchantManager.addFortune(level + 1, player.getInventory().getItemInMainHand());
                            if (level == 0) {
                                player.sendMessage(prefix + format("&fSuccessfully added Fortune enchantment to your tool!"));
                            } else {
                                player.sendMessage(prefix + format("&fSuccessfully upgraded Fortune from " + level + " to " + (level+1) + "!"));
                            }
                        } else {
                            player.sendMessage(prefix + format("&fYou don't have enough xp to get this enchantment!"));
                        }
                        break;
                    }
                    case ANVIL:{
                        // Unbreaking Enchant
                        int playerXP = player.getTotalExperience();
                        int cost = 1000;
                        int level = 0;
                        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.DURABILITY)) {
                            level = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY);
                            if (level == 3) {
                                player.sendMessage(format("&d&lVOIDMC&8» &fEnchant already at max level!"));
                                return;
                            }
                            cost = level*cost + cost;
                        }
                        if (playerXP >= cost) {
                            ItemStack _item = e.getCurrentItem();
                            ItemMeta _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getUnbreakingLore(level + 1));
                            _item.setItemMeta(_itemMeta);
                            player.setTotalExperience(0);
                            player.setLevel(0);
                            player.setExp(0);
                            player.giveExp(playerXP - cost);
                            _item = e.getView().getTopInventory().getItem(4);
                            _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getTotalXPLore(player));
                            _item.setItemMeta(_itemMeta);
                            EnchantManager.addUnbreaking(level + 1, player.getInventory().getItemInMainHand());
                            if (level == 0) {
                                player.sendMessage(prefix + format("&fSuccessfully added Unbreaking enchantment to your tool!"));
                            } else {
                                player.sendMessage(prefix + format("&fSuccessfully upgraded Unbreaking from " + level + " to " + (level+1) + "!"));
                            }
                        } else {
                            player.sendMessage(prefix + format("&fYou don't have enough xp to get this enchantment!"));
                        }
                        break;
                    }
                    case EXPERIENCE_BOTTLE:{
                        // Experience Enchant
                        int playerXP = player.getTotalExperience();
                        int cost = 2000;
                        int level = 0;
                        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.EXPERIENCE)) {
                            level = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.EXPERIENCE);
                            if (level == 3) {
                                player.sendMessage(format("&d&lVOIDMC&8» &fEnchant already at max level!"));
                                return;
                            }
                            cost = level*cost + cost;
                        }
                        if (playerXP >= cost) {
                            ItemStack _item = e.getCurrentItem();
                            ItemMeta _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getExperienceLore(level + 1));
                            _item.setItemMeta(_itemMeta);
                            player.setTotalExperience(0);
                            player.setLevel(0);
                            player.setExp(0);
                            player.giveExp(playerXP - cost);
                            _item = e.getView().getTopInventory().getItem(4);
                            _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getTotalXPLore(player));
                            _item.setItemMeta(_itemMeta);
                            EnchantManager.addExperience(level + 1, player.getInventory().getItemInMainHand());
                            if (level == 0) {
                                player.sendMessage(prefix + format("&fSuccessfully added Experience enchantment to your tool!"));
                            } else {
                                player.sendMessage(prefix + format("&fSuccessfully upgraded Experience from " + level + " to " + (level+1) + "!"));
                            }
                        } else {
                            player.sendMessage(prefix + format("&fYou don't have enough xp to get this enchantment!"));
                        }
                        break;
                    }
                    case FURNACE:{
                        // Smelting Enchant
                        int playerXP = player.getTotalExperience();
                        int cost = 1250;
                        int level = 0;
                        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.SMELTING)) {
                            level = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.SMELTING);
                            if (level == 5) {
                                player.sendMessage(format("&d&lVOIDMC&8» &fEnchant already at max level!"));
                                return;
                            }
                            cost = level*cost + cost;
                        }
                        if (playerXP >= cost) {
                            ItemStack _item = e.getCurrentItem();
                            ItemMeta _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getSmeltingLore(level + 1));
                            _item.setItemMeta(_itemMeta);
                            player.setTotalExperience(0);
                            player.setLevel(0);
                            player.setExp(0);
                            player.giveExp(playerXP - cost);
                            _item = e.getView().getTopInventory().getItem(4);
                            _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getTotalXPLore(player));
                            _item.setItemMeta(_itemMeta);
                            EnchantManager.addSmelting(level + 1, player.getInventory().getItemInMainHand());
                            if (level == 0) {
                                player.sendMessage(prefix + format("&fSuccessfully added Smelting enchantment to your tool!"));
                            } else {
                                player.sendMessage(prefix + format("&fSuccessfully upgraded Smelting from " + level + " to " + (level+1) + "!"));
                            }
                        } else {
                            player.sendMessage(prefix + format("&fYou don't have enough xp to get this enchantment!"));
                        }
                        break;
                    }
                    case COBBLESTONE:{
                        // Replenish Enchant
                        int playerXP = player.getTotalExperience();
                        int cost = 1500;
                        int level = 0;
                        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.REPLENISH)) {
                            level = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.REPLENISH);
                            if (level == 3) {
                                player.sendMessage(format("&d&lVOIDMC&8» &fEnchant already at max level!"));
                                return;
                            }
                            cost = level*cost + cost;
                        }
                        if (playerXP >= cost) {
                            ItemStack _item = e.getCurrentItem();
                            ItemMeta _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getReplenishLore(level + 1));
                            _item.setItemMeta(_itemMeta);
                            player.setTotalExperience(0);
                            player.setLevel(0);
                            player.setExp(0);
                            player.giveExp(playerXP - cost);
                            _item = e.getView().getTopInventory().getItem(4);
                            _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getTotalXPLore(player));
                            _item.setItemMeta(_itemMeta);
                            EnchantManager.addReplenish(level + 1, player.getInventory().getItemInMainHand());
                            if (level == 0) {
                                player.sendMessage(prefix + format("&fSuccessfully added Replenish enchantment to your tool!"));
                            } else {
                                player.sendMessage(prefix + format("&fSuccessfully upgraded Replenish from " + level + " to " + (level+1) + "!"));
                            }
                        } else {
                            player.sendMessage(prefix + format("&fYou don't have enough xp to get this enchantment!"));
                        }
                        break;
                    }
                    case GOLDEN_PICKAXE:{
                        // Profession Enchant
                        int playerXP = player.getTotalExperience();
                        int cost = 2000;
                        int level = 0;
                        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.PROFESSION)) {
                            level = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.PROFESSION);
                            if (level == 5) {
                                player.sendMessage(format("&d&lVOIDMC&8» &fEnchant already at max level!"));
                                return;
                            }
                            cost = level*cost + cost;
                        }
                        if (playerXP >= cost) {
                            ItemStack _item = e.getCurrentItem();
                            ItemMeta _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getSmeltingLore(level + 1));
                            _item.setItemMeta(_itemMeta);
                            player.setTotalExperience(0);
                            player.setLevel(0);
                            player.setExp(0);
                            player.giveExp(playerXP - cost);
                            _item = e.getView().getTopInventory().getItem(4);
                            _itemMeta = _item.getItemMeta();
                            _itemMeta.setLore(getTotalXPLore(player));
                            _item.setItemMeta(_itemMeta);
                            EnchantManager.addProfession(level + 1, player.getInventory().getItemInMainHand());
                            if (level == 0) {
                                player.sendMessage(prefix + format("&fSuccessfully added Profession enchantment to your tool!"));
                            } else {
                                player.sendMessage(prefix + format("&fSuccessfully upgraded Profession from " + level + " to " + (level+1) + "!"));
                            }
                        } else {
                            player.sendMessage(prefix + format("&fYou don't have enough xp to get this enchantment!"));
                        }
                        break;
                    }

                }
            }
        }
    }

    // may be removed
    @EventHandler
    public void onClose (InventoryCloseEvent e) {

    }

    public static Inventory getEnchantsGUI(Player player, ItemStack heldItem) {
        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Inventory inv = Bukkit.createInventory(null, 36, "Enchanter");
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        // Glass Panes
        inv.setItem(0, item);
        inv.setItem(1, item);
        inv.setItem(2, item);
        inv.setItem(3, item);
        inv.setItem(5, item);
        inv.setItem(6, item);
        inv.setItem(7, item);
        inv.setItem(8, item);
        inv.setItem(9, item);
        inv.setItem(17, item);
        inv.setItem(18, item);
        inv.setItem(26, item);
        inv.setItem(27, item);
        inv.setItem(28, item);
        inv.setItem(29, item);
        inv.setItem(30, item);
        inv.setItem(31, item);
        inv.setItem(32, item);
        inv.setItem(33, item);
        inv.setItem(34, item);
        inv.setItem(35, item);

        if (!isNewVersion)
            item.setDurability((short) 3);

        item.setType(Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM"));
        meta.setLore(getTotalXPLore(player));
        meta.setDisplayName(format("&b&l" + player.getName()));
        item.setItemMeta(meta);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwner(player.getName());
        item.setItemMeta(skullMeta);
        inv.setItem(4, item);

        /**
         * Enchant Placement Based Off Current Levels.
         */

        switch (heldItem.getType()) {
            case WOODEN_PICKAXE:
            case STONE_PICKAXE:
            case IRON_PICKAXE:
            case GOLDEN_PICKAXE:
            case DIAMOND_PICKAXE:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DIG_SPEED)) {
                    item.setType(Material.SUGAR);
                    meta.setDisplayName(format("&b&lEfficiency"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DIG_SPEED)) {
                        case 1:{
                            meta.setLore(getEfficiencyLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getEfficiencyLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getEfficiencyLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getEfficiencyLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getEfficiencyLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.SUGAR);
                    meta.setDisplayName(format("&b&lEfficiency"));
                    meta.setLore(getEfficiencyLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (enchants.containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                    item.setType(Material.GOLD_INGOT);
                    meta.setDisplayName(format("&b&lFortune"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)) {
                        case 1:{
                            meta.setLore(getFortuneLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getFortuneLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getFortuneLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.GOLD_INGOT);
                    meta.setDisplayName(format("&b&lFortune"));
                    meta.setLore(getFortuneLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.EXPERIENCE)) {
                    item.setType(Material.EXPERIENCE_BOTTLE);
                    meta.setDisplayName(format("&b&lExperience"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.EXPERIENCE)) {
                        case 1:{
                            meta.setLore(getExperienceLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getExperienceLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getExperienceLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.EXPERIENCE_BOTTLE);
                    meta.setDisplayName(format("&b&lExperience"));
                    meta.setLore(getExperienceLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(13, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.SMELTING)) {
                    item.setType(Material.FURNACE);
                    meta.setDisplayName(format("&b&lSmelting"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.SMELTING)) {
                        case 1:{
                            meta.setLore(getSmeltingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getSmeltingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getSmeltingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getSmeltingLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getSmeltingLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.FURNACE);
                    meta.setDisplayName(format("&b&lSmelting"));
                    meta.setLore(getSmeltingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(14, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.REPLENISH)) {
                    item.setType(Material.COBBLESTONE);
                    meta.setDisplayName(format("&b&lReplenish"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.REPLENISH)) {
                        case 1:{
                            meta.setLore(getReplenishLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(15, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getReplenishLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(15, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getReplenishLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(15, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.COBBLESTONE);
                    meta.setDisplayName(format("&b&lReplenish"));
                    meta.setLore(getReplenishLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(15, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.PROFESSION)) {
                    item.setType(Material.GOLDEN_PICKAXE);
                    meta.setDisplayName(format("&b&lProfession"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.PROFESSION)) {
                        case 1:{
                            meta.setLore(getProfessionLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(16, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getProfessionLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(16, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getProfessionLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(16, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getProfessionLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(16, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getProfessionLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(16, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.GOLDEN_PICKAXE);
                    meta.setDisplayName(format("&b&lProfession"));
                    meta.setLore(getProfessionLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(16, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.TELEKINESIS)) {
                    item.setType(Material.ENDER_PEARL);
                    meta.setDisplayName(format("&b&lTelekinesis"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.TELEKINESIS)) {
                        case 1:{
                            meta.setLore(getTelekinesisLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getTelekinesisLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getTelekinesisLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ENDER_PEARL);
                    meta.setDisplayName(format("&b&lTelekinesis"));
                    meta.setLore(getTelekinesisLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                break;
            }
            case WOODEN_SWORD:
            case STONE_SWORD:
            case IRON_SWORD:
            case GOLDEN_SWORD:
            case DIAMOND_SWORD:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DAMAGE_ALL)) {
                    item.setType(Material.IRON_SWORD);
                    meta.setDisplayName(format("&b&lSharpness"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DAMAGE_ALL)) {
                        case 1:{
                            meta.setLore(getSharpnessLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getSharpnessLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getSharpnessLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getSharpnessLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getSharpnessLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.IRON_SWORD);
                    meta.setDisplayName(format("&b&lSharpness"));
                    meta.setLore(getSharpnessLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (enchants.containsKey(Enchantment.LOOT_BONUS_MOBS)) {
                    item.setType(Material.GOLD_INGOT);
                    meta.setDisplayName(format("&b&lLooting"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS)) {
                        case 1:{
                            meta.setLore(getLootingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getLootingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getLootingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.GOLD_NUGGET);
                    meta.setDisplayName(format("&b&lLooting"));
                    meta.setLore(getLootingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                if (enchants.containsKey(Enchantment.FIRE_ASPECT)) {
                    item.setType(Material.BLAZE_POWDER);
                    meta.setDisplayName(format("&b&lFire Aspect"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.FIRE_ASPECT)) {
                        case 1:{
                            meta.setLore(getFireAspectLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getFireAspectLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.BLAZE_POWDER);
                    meta.setDisplayName(format("&b&lFire Aspect"));
                    meta.setLore(getFireAspectLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(13, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.PROFESSION)) {
                    item.setType(Material.GOLDEN_PICKAXE);
                    meta.setDisplayName(format("&b&lProfession"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.PROFESSION)) {
                        case 1:{
                            meta.setLore(getProfessionLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getProfessionLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getProfessionLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getProfessionLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getProfessionLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.GOLDEN_PICKAXE);
                    meta.setDisplayName(format("&b&lProfession"));
                    meta.setLore(getProfessionLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(14, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.EXPERIENCE)) {
                    item.setType(Material.EXPERIENCE_BOTTLE);
                    meta.setDisplayName(format("&b&lExperience"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.EXPERIENCE)) {
                        case 1:{
                            meta.setLore(getExperienceLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(15, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getExperienceLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(15, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getExperienceLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(15, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.EXPERIENCE_BOTTLE);
                    meta.setDisplayName(format("&b&lExperience"));
                    meta.setLore(getExperienceLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(15, item);
                }
                break;
            }
            case WOODEN_SHOVEL:
            case STONE_SHOVEL:
            case DIAMOND_SHOVEL:
            case GOLDEN_SHOVEL:
            case IRON_SHOVEL:
            case WOODEN_AXE:
            case STONE_AXE:
            case IRON_AXE:
            case GOLDEN_AXE:
            case DIAMOND_AXE:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DIG_SPEED)) {
                    item.setType(Material.SUGAR);
                    meta.setDisplayName(format("&b&lEfficiency"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DIG_SPEED)) {
                        case 1:{
                            meta.setLore(getEfficiencyLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getEfficiencyLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getEfficiencyLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getEfficiencyLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getEfficiencyLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.SUGAR);
                    meta.setDisplayName(format("&b&lEfficiency"));
                    meta.setLore(getEfficiencyLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.TELEKINESIS)) {
                    item.setType(Material.ENDER_PEARL);
                    meta.setDisplayName(format("&b&lTelekinesis"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.TELEKINESIS)) {
                        case 1:{
                            meta.setLore(getTelekinesisLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ENDER_PEARL);
                    meta.setDisplayName(format("&b&lTelekinesis"));
                    meta.setLore(getTelekinesisLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                if (heldItem.getType().toString().endsWith("_AXE")) {
                    if (heldItem.getItemMeta().hasEnchant(CustomEnchants.HARVESTER)) {
                        item.setType(Material.WHEAT);
                        meta.setDisplayName(format("&b&lHarvester"));
                        item.setItemMeta(meta);
                        switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.HARVESTER)) {
                            case 1: {
                                meta.setLore(getHarvesterLore(1));
                                item.setItemMeta(meta);
                                inv.setItem(13, item);
                                break;
                            }
                            case 2: {
                                meta.setLore(getHarvesterLore(2));
                                item.setItemMeta(meta);
                                inv.setItem(13, item);
                                break;
                            }
                            case 3: {
                                meta.setLore(getHarvesterLore(3));
                                item.setItemMeta(meta);
                                inv.setItem(13, item);
                                break;
                            }
                        }
                    } else {
                        item.setType(Material.WHEAT);
                        meta.setDisplayName(format("&b&lHarvester"));
                        meta.setLore(getTelekinesisLore(0));
                        item.setItemMeta(meta);
                        inv.setItem(13, item);
                    }
                }
                break;
            }
            case WOODEN_HOE:
            case STONE_HOE:
            case IRON_HOE:
            case GOLDEN_HOE:
            case DIAMOND_HOE:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.TELEKINESIS)) {
                    item.setType(Material.ENDER_PEARL);
                    meta.setDisplayName(format("&b&lTelekinesis"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.TELEKINESIS)) {
                        case 1:{
                            meta.setLore(getTelekinesisLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ENDER_PEARL);
                    meta.setDisplayName(format("&b&lTelekinesis"));
                    meta.setLore(getTelekinesisLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                if (heldItem.getItemMeta().hasEnchant(CustomEnchants.HARVESTER)) {
                    item.setType(Material.WHEAT);
                    meta.setDisplayName(format("&b&lHarvester"));
                    item.setItemMeta(meta);
                    switch (heldItem.getItemMeta().getEnchantLevel(CustomEnchants.HARVESTER)) {
                        case 1:{
                            meta.setLore(getHarvesterLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getHarvesterLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getHarvesterLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.WHEAT);
                    meta.setDisplayName(format("&b&lHarvester"));
                    meta.setLore(getTelekinesisLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                break;
            }
            case LEATHER_HELMET:
            case GOLDEN_HELMET:
            case IRON_HELMET:
            case DIAMOND_HELMET:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (enchants.containsKey(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                    item.setType(Material.DIAMOND_CHESTPLATE);
                    meta.setDisplayName(format("&b&lProtection"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                        case 1:{
                            meta.setLore(getProtectionLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getProtectionLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getProtectionLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getProtectionLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getProtectionLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.DIAMOND_CHESTPLATE);
                    meta.setDisplayName(format("&b&lProtection"));
                    meta.setLore(getProtectionLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                if (enchants.containsKey(Enchantment.OXYGEN)) {
                    item.setType(Material.WATER_BUCKET);
                    meta.setDisplayName(format("&b&lAqua affinity"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.OXYGEN)) {
                        case 1:{
                            meta.setLore(getAquaLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.WATER_BUCKET);
                    meta.setDisplayName(format("&b&lAqua affinity"));
                    meta.setLore(getAquaLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                break;
            }
            case LEATHER_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case IRON_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case IRON_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case DIAMOND_LEGGINGS:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (enchants.containsKey(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                    item.setType(Material.DIAMOND_CHESTPLATE);
                    meta.setDisplayName(format("&b&lProtection"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                        case 1:{
                            meta.setLore(getProtectionLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getProtectionLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getProtectionLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getProtectionLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getProtectionLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.DIAMOND_CHESTPLATE);
                    meta.setDisplayName(format("&b&lProtection"));
                    meta.setLore(getProtectionLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                break;
            }
            case LEATHER_BOOTS:
            case IRON_BOOTS:
            case GOLDEN_BOOTS:
            case DIAMOND_BOOTS:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (enchants.containsKey(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                    item.setType(Material.DIAMOND_CHESTPLATE);
                    meta.setDisplayName(format("&b&lProtection"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                        case 1:{
                            meta.setLore(getProtectionLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getProtectionLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getProtectionLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getProtectionLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getProtectionLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.DIAMOND_CHESTPLATE);
                    meta.setDisplayName(format("&b&lProtection"));
                    meta.setLore(getProtectionLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                if (enchants.containsKey(Enchantment.PROTECTION_FALL)) {
                    item.setType(Material.FEATHER);
                    meta.setDisplayName(format("&b&lFeather falling"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.PROTECTION_FALL)) {
                        case 1:{
                            meta.setLore(getFeatherFallingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getFeatherFallingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getFeatherFallingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getFeatherFallingLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getFeatherFallingLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.FEATHER);
                    meta.setDisplayName(format("&b&lFeather falling"));
                    meta.setLore(getFeatherFallingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                break;
            }
            case BOW:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (enchants.containsKey(Enchantment.ARROW_DAMAGE)) {
                    item.setType(Material.ARROW);
                    meta.setDisplayName(format("&b&lPower"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.ARROW_DAMAGE)) {
                        case 1:{
                            meta.setLore(getPowerLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getPowerLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getPowerLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 4:{
                            meta.setLore(getPowerLore(4));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 5:{
                            meta.setLore(getPowerLore(5));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ARROW);
                    meta.setDisplayName(format("&b&lPower"));
                    meta.setLore(getPowerLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                if (enchants.containsKey(Enchantment.ARROW_KNOCKBACK)) {
                    item.setType(Material.STICK);
                    meta.setDisplayName(format("&b&lPunch"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK)) {
                        case 1:{
                            meta.setLore(getPunchLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getPunchLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.STICK);
                    meta.setDisplayName(format("&b&lPunch"));
                    meta.setLore(getPunchLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                if (enchants.containsKey(Enchantment.ARROW_INFINITE)) {
                    item.setType(Material.ENDER_CHEST);
                    meta.setDisplayName(format("&b&lInfinity"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.ARROW_INFINITE)) {
                        case 1:{
                            meta.setLore(getInfinityLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ENDER_CHEST);
                    meta.setDisplayName(format("&b&lInfinity"));
                    meta.setLore(getInfinityLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(13, item);
                }
                if (enchants.containsKey(Enchantment.ARROW_FIRE)) {
                    item.setType(Material.LAVA_BUCKET);
                    meta.setDisplayName(format("&b&lFlame"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.ARROW_FIRE)) {
                        case 1:{
                            meta.setLore(getArrowFlame(1));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getArrowFlame(2));
                            item.setItemMeta(meta);
                            inv.setItem(14, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.LAVA_BUCKET);
                    meta.setDisplayName(format("&b&lFlame"));
                    meta.setLore(getArrowFlame(0));
                    item.setItemMeta(meta);
                    inv.setItem(14, item);
                }
                break;
            }
            case FISHING_ROD:{
                Map<Enchantment, Integer> enchants = heldItem.getEnchantments();
                if (enchants.containsKey(Enchantment.DURABILITY)) {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 1:{
                            meta.setLore(getUnbreakingLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getUnbreakingLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getUnbreakingLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(10, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.ANVIL);
                    meta.setDisplayName(format("&b&lUnbreaking"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(10, item);
                }
                if (enchants.containsKey(CustomEnchants.LUCKY)) {
                    item.setType(Material.CHEST);
                    meta.setDisplayName(format("&b&lLucky"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(CustomEnchants.LUCKY)) {
                        case 1:{
                            meta.setLore(getLuckyLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getLuckyLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getLuckyLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(11, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.CHEST);
                    meta.setDisplayName(format("&b&lLucky"));
                    meta.setLore(getUnbreakingLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(11, item);
                }
                if (enchants.containsKey(CustomEnchants.MYTHICAL)) {
                    item.setType(Material.DRAGON_EGG);
                    meta.setDisplayName(format("&b&lMythical"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(CustomEnchants.MYTHICAL)) {
                        case 1:{
                            meta.setLore(getMythicalLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getMythicalLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getMythicalLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(12, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.DRAGON_EGG);
                    meta.setDisplayName(format("&b&lMythical"));
                    meta.setLore(getMythicalLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(12, item);
                }
                if (enchants.containsKey(Enchantment.LURE)) {
                    item.setType(Material.STRING);
                    meta.setDisplayName(format("&b&lLure"));
                    item.setItemMeta(meta);
                    switch (heldItem.getEnchantmentLevel(Enchantment.LURE)) {
                        case 1:{
                            meta.setLore(getLureLore(1));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                        case 2:{
                            meta.setLore(getLureLore(2));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                        case 3:{
                            meta.setLore(getLureLore(3));
                            item.setItemMeta(meta);
                            inv.setItem(13, item);
                            break;
                        }
                    }
                } else {
                    item.setType(Material.STRING);
                    meta.setDisplayName(format("&b&lLure"));
                    meta.setLore(getLureLore(0));
                    item.setItemMeta(meta);
                    inv.setItem(13, item);
                }
                break;
            }
        }

        return inv;
    }

    /**
     * Some methods to get enchant lore based of level
     *
     * Note: can probably turn this into a single method by taking a desc, cost, lvl ect.
     *
     */

    private static ArrayList<String> getTotalXPLore(Player player) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&dCurrent XP: &f" + player.getTotalExperience()));
        return lore;
    }

    private static ArrayList<String> getEfficiencyLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes your tool"));
        lore.add(format("&7mine faster than normal!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 5) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/5"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*750 + 750) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getFortuneLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant gives your tool"));
        lore.add(format("&7a chance to get more items!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1500 + 1500) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getUnbreakingLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant gives your item"));
        lore.add(format("&7more durability!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1000 + 1000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getExperienceLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant gets you"));
        lore.add(format("&7more experience!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*2000 + 2000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getProfessionLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant gets you more"));
        lore.add(format("&7skill xp for skills!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 5) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/5"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*3000 + 3000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getSmeltingLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant smelts ore"));
        lore.add(format("&7automatically while mining!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 5) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/5"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*2750 + 2750) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getReplenishLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant has a chance"));
        lore.add(format("&7to regenerate ores while mining!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1500 + 1500) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getSharpnessLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes your"));
        lore.add(format("&7weapon deal more damage!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 5) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/5"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1500 + 1500) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getFireAspectLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes your"));
        lore.add(format("&7weapon deal fire damage!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 2) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/2"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1000 + 1000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getLootingLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes mobs"));
        lore.add(format("&7drop more items!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*2000 + 2000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getPowerLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes your"));
        lore.add(format("&7bow deal more damage!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 5) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/5"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1500 + 1500) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getPunchLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes your"));
        lore.add(format("&7bow knockback enemies!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 2) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/2"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1000 + 1000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getArrowFlame(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes your bow"));
        lore.add(format("&7 deal fire damage to enemies!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 2) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/2"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1000 + 1000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getInfinityLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes your"));
        lore.add(format("&7bow have infinite arrows!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 1) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/1"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*3000 + 3000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getLuckyLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7Makes your fishing rod"));
        lore.add(format("&7have a better chance to find"));
        lore.add(format("&7rarer fish!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1500 + 1500) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getLureLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7Increases the rate of"));
        lore.add(format("&7finding a fish!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1750 + 1750) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getTelekinesisLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7Items go straight to"));
        lore.add(format("&7your inventory!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 1) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/1"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1000 + 1000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getHarvesterLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7Increases the chance to"));
        lore.add(format("&7get double the drops!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*2000 + 2000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getMythicalLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7While fishing have the chance"));
        lore.add(format("&7to instantly catch a Mythic fish!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 1) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/1"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*3000 + 3000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getProtectionLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7This enchant makes you"));
        lore.add(format("&7take less damage!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 5) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/5"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1000 + 1000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getThornsLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7When damaged deal damage"));
        lore.add(format("&7to your attacker!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 3) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/3"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1000 + 1000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getFeatherFallingLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7Take less fall damage!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 4) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/4"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1000 + 1000) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static ArrayList<String> getAquaLore(int level) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(format("&7With this enchant you can"));
        lore.add(format("&7stay underwater for longer!"));
        lore.add("");
        lore.add(format("&b&lINFO"));
        if (level == 1) {
            lore.add(format("&8» &dCurrent level: &f&lMAX"));
            lore.add(format("&8» &dUpgrade cost: &f&lMAX"));
        } else {
            lore.add(format("&8» &dCurrent level: &f" + level + "/1"));
            lore.add(format("&8» &dUpgrade cost: &f" + (level*1250 + 1250) + "xp"));
        }
        lore.add(format("&8» &7(click to upgrade)"));
        return lore;
    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
