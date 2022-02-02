package me.rxndmz.voidskills;

import me.rxndmz.voidskills.Commands.Enchanter;
import me.rxndmz.voidskills.Commands.ResetSkills;
import me.rxndmz.voidskills.Commands.Skills;
import me.rxndmz.voidskills.Commands.Test;
import me.rxndmz.voidskills.Enchantments.CustomEnchants;
import me.rxndmz.voidskills.Enchantments.EnchantsGUI;
import me.rxndmz.voidskills.Leveling.LevelHandler;
import me.rxndmz.voidskills.Leveling.XPHandler;
import me.rxndmz.voidskills.Skills.Farming.Farming;
import me.rxndmz.voidskills.Skills.GUI.SkillGUIHandler;
import me.rxndmz.voidskills.Skills.Hunting.Hunting;
import me.rxndmz.voidskills.Skills.Mining.Mining;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

public final class VoidSkills extends JavaPlugin {

    public MySQL SQL;
    public SQLHandler sqlHandler;
    private final Logger log = Logger.getLogger("Minecraft");
    private static VoidSkills plugin;
    private static Economy econ = null;
    private static FileConfiguration config;
    private static String prefix;

    @Override
    public void onEnable() {
        plugin = this;

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        CustomEnchants.register();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        config = getConfig();

        prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("prefix")));

        this.SQL = new MySQL();
        this.sqlHandler = new SQLHandler();
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            log.severe("[VoidSkills] Database not connected! Please connect a database via the config!");
            this.setEnabled(false);
        }
        if (SQL.isConnected()) {
            log.info("[VoidSkills] Database is connected!");
            sqlHandler.createTables();
        }

        this.getServer().getPluginManager().registerEvents(new Mining(), this);
        this.getServer().getPluginManager().registerEvents(new Farming(), this);
        this.getServer().getPluginManager().registerEvents(new Hunting(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinHandler(), this);
        this.getServer().getPluginManager().registerEvents(new XPHandler(), this);
        this.getServer().getPluginManager().registerEvents(new LevelHandler(), this);
        this.getServer().getPluginManager().registerEvents(new SkillGUIHandler(), this);
        this.getServer().getPluginManager().registerEvents(new EnchantsGUI(), this);

        this.getCommand("Test").setExecutor(new Test());
        this.getCommand("ResetSkills").setExecutor(new ResetSkills());
        this.getCommand("Enchanter").setExecutor(new Enchanter());
        this.getCommand("Skills").setExecutor(new Skills());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static String getPrefix() { return prefix; }

    public static VoidSkills getPlugin() {
        return plugin;
    }

    public static FileConfiguration getDefaultConfig() { return config; }

    @Override
    public void onDisable() {
        SQL.disconnect();
    }
}
