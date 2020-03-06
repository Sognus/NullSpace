package cz.sognus.nullspace;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NullSpaceCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "NullSpace " + NullSpace.plugin.getDescription().getVersion() + ChatColor.DARK_GRAY + " -----------");
            sender.sendMessage("Nullspace is Spigot plugin created by Sognus to generate empty world with a few asteroids.");
        }

        return true;
    }
}