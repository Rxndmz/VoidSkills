package me.rxndmz.voidskills.Skills.GUI;

import me.rxndmz.voidskills.VoidSkills;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
import java.util.stream.Collectors;

public class SkillGUIHandler implements Listener {

    private static VoidSkills plugin = VoidSkills.getPlugin();

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        switch (e.getView().getTitle()) {
            case "Skills": {
                e.setCancelled(true);
                if (e.getCurrentItem() != null) {
                    ItemStack clicked_item = e.getCurrentItem();
                    switch (clicked_item.getType().toString()) {
                        case "DIAMOND_PICKAXE": {
                            e.getWhoClicked().sendMessage("Mining");
                            break;
                        }
                        case "BOW": {
                            e.getWhoClicked().sendMessage("Hunting");
                            break;
                        }
                        case "FISHING_ROD": {
                            e.getWhoClicked().sendMessage("Fishing");
                            break;
                        }
                        case "DIAMOND_HOE": {
                            e.getWhoClicked().sendMessage("Farming");
                            break;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {

    }

    public static Inventory getSkillsGUI(Player player) {
        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Inventory inv = Bukkit.createInventory(null, 27, "Skills");
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        // Glass Panes
        inv.setItem(0, item);
        inv.setItem(1, item);
        inv.setItem(2, item);
        inv.setItem(3, item);
        inv.setItem(4, item);
        inv.setItem(5, item);
        inv.setItem(6, item);
        inv.setItem(7, item);
        inv.setItem(8, item);
        inv.setItem(9, item);
        inv.setItem(17, item);
        inv.setItem(18, item);
        inv.setItem(19, item);
        inv.setItem(20, item);
        inv.setItem(21, item);
        inv.setItem(22, item);
        inv.setItem(23, item);
        inv.setItem(24, item);
        inv.setItem(25, item);
        inv.setItem(26, item);
        item.setType(Material.PURPLE_STAINED_GLASS_PANE);
        inv.setItem(10, item);
        inv.setItem(16, item);

        // Skills
        // Fishing
        item.setType(Material.FISHING_ROD);
        meta.setDisplayName(format("&b&lFishing"));
        lore.add(format("&dLevel: &f" + plugin.sqlHandler.getFishingLvl(player)));
        lore.add(format("&dXP: &f" + plugin.sqlHandler.getFishingXp(player)));
        lore.add(format("&dNext Lvl: &f" + (Math.round(plugin.sqlHandler.getFishingXp(player) * 100) / 100)) + " / " + (Math.round(1000 * Math.pow(2.2, plugin.sqlHandler.getFishingLvl(player) - 1) * 100) / 100));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(11, item);

        // Farming
        item.setType(Material.DIAMOND_HOE);
        meta.setDisplayName(format("&b&lFarming"));
        lore.clear();
        lore.add(format("&dLevel: &f" + plugin.sqlHandler.getFarmingLvl(player)));
        lore.add(format("&dXP: &f" + plugin.sqlHandler.getFarmingXp(player)));
        lore.add(format("&dNext Lvl: &f" + (Math.round(plugin.sqlHandler.getFarmingXp(player) * 100) / 100)) + " / " + (Math.round(1000 * Math.pow(2.2, plugin.sqlHandler.getFarmingLvl(player) - 1) * 100) / 100));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(12, item);

        // Mining
        item.setType(Material.DIAMOND_PICKAXE);
        meta.setDisplayName(format("&b&lMining"));
        lore.clear();
        lore.add(format("&dLevel: &f" + plugin.sqlHandler.getMiningLvl(player)));
        lore.add(format("&dXP: &f" + plugin.sqlHandler.getMiningXp(player)));
        lore.add(format("&dNext Lvl: &f" + (Math.round(plugin.sqlHandler.getMiningXp(player) * 100) / 100)) + " / " + (Math.round(1000 * Math.pow(2.2, plugin.sqlHandler.getMiningLvl(player) - 1) * 100) / 100));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(14, item);

        // Hunting
        item.setType(Material.BOW);
        meta.setDisplayName(format("&b&lHunting"));
        lore.clear();
        lore.add(format("&dLevel: &f" + plugin.sqlHandler.getHuntingLvl(player)));
        lore.add(format("&dXP: &f" + plugin.sqlHandler.getHuntingXp(player)));
        lore.add(format("&dNext Lvl: &f" + (Math.round(plugin.sqlHandler.getHuntingXp(player) * 100) / 100)) + " / " + (Math.round(1000 * Math.pow(2.2, plugin.sqlHandler.getHuntingLvl(player) - 1) * 100) / 100));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(15, item);

        if (!isNewVersion)
            item.setDurability((short) 3);

        item.setType(Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM"));
        lore.clear();
        lore.add(format("&d&lTotal Level: &f" + plugin.sqlHandler.getTotalLevel(player)));
        lore.add(format("&d&lFishing Level: &f" + plugin.sqlHandler.getFishingLvl(player)));
        lore.add(format("&d&lFarming Level: &f" + plugin.sqlHandler.getFarmingLvl(player)));
        lore.add(format("&d&lMining Level: &f" + plugin.sqlHandler.getMiningLvl(player)));
        lore.add(format("&d&lHunting Level: &f" + plugin.sqlHandler.getHuntingLvl(player)));
        meta.setLore(lore);
        meta.setDisplayName(format("&b&lStats"));
        item.setItemMeta(meta);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwner(player.getName());
        item.setItemMeta(skullMeta);
        inv.setItem(13, item);
        return inv;
    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
