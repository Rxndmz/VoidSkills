package me.rxndmz.voidskills.Commands;

import me.rxndmz.voidskills.Skills.GUI.SkillGUIHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Skills implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.openInventory(SkillGUIHandler.getSkillsGUI(player));
        }
        return false;
    }
}
