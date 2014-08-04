package de.dustplanet.superwheat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * SuperWheat for CraftBukkit/Bukkit 
 * Handles the command 

 * Refer to the dev.bukkit.org page: 
 * http://dev.bukkit.org/bukkit-plugins/superwheat/
 * 
 * @author xGhOsTkiLLeRx
 * thanks to thescreem for the original SuperWheat plugin!
 */

public class SuperWheatCommand implements CommandExecutor {
    private SuperWheat plugin;

    public SuperWheatCommand(SuperWheat instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /superwheat reload (or /sw reload)");
        } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
            // Make sure the sender has the permission
            if (sender.hasPermission("SuperWheat.reload")) {
                plugin.loadConfigAgain();
                sender.sendMessage(ChatColor.GREEN + "SuperWheat successfully reloaded!");
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have the permission to reload SuperWheat!");
            }
            // So far we only support reload
        } else {
            sender.sendMessage(ChatColor.RED + "This argument is unkown!");
        }
        return true;
    }
}
