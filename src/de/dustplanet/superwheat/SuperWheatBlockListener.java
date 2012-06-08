package de.dustplanet.superwheat;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

/**
 * SuperWheat for CraftBukkit/Bukkit
 * Handles block activities!
 * Refer to the forum thread:
 * http://bit.ly/superwheatthread
 * Refer to the dev.bukkit.org page: http://bit.ly/superwheatpage
 * 
 * @author  xGhOsTkiLLeRx
 * @thanks  to thescreem for the original SuperWheat plugin!
 */

public class SuperWheatBlockListener implements Listener {

	public SuperWheat plugin;
	public SuperWheatBlockListener(SuperWheat instance){
		plugin = instance;
	}

	@EventHandler
	public void onBlockFromTo(BlockFromToEvent event) {
		if (plugin.config.getBoolean("preventWater")) {
			if (event.getBlock().getType() == Material.WATER || event.getBlock().getType() == Material.STATIONARY_WATER) {
				if (event.getToBlock().getType() == Material.CROPS) {
					if (plugin.config.getBoolean("preventWaterGrown")) {
						event.setCancelled(true);
					}
					else if ((byte) event.getToBlock().getData() != 7) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}