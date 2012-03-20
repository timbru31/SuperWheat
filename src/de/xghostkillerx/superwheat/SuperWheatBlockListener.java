package de.xghostkillerx.superwheat;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

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