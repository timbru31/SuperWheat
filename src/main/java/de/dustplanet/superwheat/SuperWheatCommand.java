package de.dustplanet.superwheat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SuperWheatCommand implements CommandExecutor {
    private SuperWheat plugin;

    public SuperWheatCommand(SuperWheat instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /superwheat reload (or /sw reload)");
        } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
            if (sender.hasPermission("SuperWheat.reload")) {
                plugin.loadConfigAgain();
                sender.sendMessage(ChatColor.GREEN + "SuperWheat successfully reloaded!");
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have the permission to reload SuperWheat!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This argument is unkown!");
        }
        return true;
    }
}
