package de.dustplanet.superwheat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * SuperWheat for CraftBukkit/Bukkit 
 * Handles the command 
 * Refer to the forum thread: 
 * http://bit.ly/superwheatthread
 * 
 * Refer to the dev.bukkit.org page: 
 * http://bit.ly/superwheatpage
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
	} else if (args[0].equalsIgnoreCase("reload")) {
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