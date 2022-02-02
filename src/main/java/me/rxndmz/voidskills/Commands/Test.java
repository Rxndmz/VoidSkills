package me.rxndmz.voidskills.Commands;

import me.rxndmz.voidskills.Enchantments.CustomEnchants;
import me.rxndmz.voidskills.Enchantments.EnchantManager;
import me.rxndmz.voidskills.VoidSkills;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Test implements CommandExecutor {

    VoidSkills plugin = VoidSkills.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //HologramsAPI.getHolograms(plugin).forEach(Hologram::delete);
        if (sender instanceof Player) {
            Player player = (Player) sender;
            EnchantManager.addProfession(5, player.getInventory().getItemInMainHand());
            EnchantManager.addReplenish(3, player.getInventory().getItemInMainHand());
            EnchantManager.addExperience(3, player.getInventory().getItemInMainHand());
            EnchantManager.addSmelting(5, player.getInventory().getItemInMainHand());
            /*ItemStack generator = new ItemStack(Material.COAL_BLOCK);
            ItemMeta itemMeta = generator.getItemMeta();
            itemMeta.setDisplayName(format("&8&lCoal Generator"));
            List<String> lore = new ArrayList<String>();
            lore.add(format("&dPlace the generator on your island"));
            lore.add(format("&dto start generating ores!"));
            itemMeta.setLore(lore);
            generator.setItemMeta(itemMeta);
            player.getInventory().addItem(generator);*/
        }
        return false;
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
