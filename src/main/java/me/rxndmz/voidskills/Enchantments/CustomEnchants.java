package me.rxndmz.voidskills.Enchantments;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants {

    // All

    public static final Enchantment EXPERIENCE = new EnchantmentWrapper("experience", "Experience", 3);
    public static final Enchantment PROFESSION = new EnchantmentWrapper("profession", "Profession", 5);
    public static final Enchantment TELEKINESIS = new EnchantmentWrapper("telekinesis", "Telekinesis", 1);

    // Pickaxe

    public static final Enchantment SMELTING = new EnchantmentWrapper("smelting", "Smelting", 5);
    public static final Enchantment REPLENISH = new EnchantmentWrapper("replenish", "Replenish", 3);

    // Hoe

    public static final Enchantment HARVESTER = new EnchantmentWrapper("harvester", "Harvester", 3);

    // Fishing Rod

    public static final Enchantment MYTHICAL = new EnchantmentWrapper("mythical", "Mythical", 3);
    public static final Enchantment LUCKY = new EnchantmentWrapper("lucky", "Lucky", 3);

    // Registering Enchants

    public static void register() {
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(SMELTING)) {
            registerEnchantment(SMELTING);
        }
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(LUCKY)) {
            registerEnchantment(LUCKY);
        }
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(PROFESSION)) {
            registerEnchantment(PROFESSION);
        }
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(REPLENISH)) {
            registerEnchantment(REPLENISH);
        }
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(EXPERIENCE)) {
            registerEnchantment(EXPERIENCE);
        }
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEKINESIS)) {
            registerEnchantment(TELEKINESIS);
        }
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(HARVESTER)) {
            registerEnchantment(HARVESTER);
        }
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(MYTHICAL)) {
            registerEnchantment(MYTHICAL);
        }
    }

    private static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
    }

}
