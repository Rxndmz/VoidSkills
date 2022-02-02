package me.rxndmz.voidskills.Commands;

import me.rxndmz.voidskills.VoidSkills;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ResetSkills implements CommandExecutor {

    private VoidSkills plugin = VoidSkills.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player playerToReset = Bukkit.getPlayer(args[0]);
                plugin.sqlHandler.resetSkills(playerToReset);
                player.sendMessage("Player's skills have been reset!");
            } else {
                player.sendMessage("Please input a player name to reset.");
            }
        }
        return false;
    }
}
