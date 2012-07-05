package de.dustplanet.superwheat;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SuperWheatPlayerListener implements Listener {

	public SuperWheat plugin;
	public SuperWheatPlayerListener(SuperWheat instance){
		plugin = instance;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			final Block block = event.getClickedBlock();
			// If that block is a crop (Block ID #59)...
			if(block.getType() == Material.CROPS){
				Player player = event.getPlayer();
				// If the data for the crop isn't 7 (Isn't fully grown)
				// And the player doesn't have the bypass permission...
				// Also check if creative guys should be able to destroy
				if((byte) block.getData() != 7) {
					if (!player.hasPermission("SuperWheat.destroying") || plugin.blockCreativeDestroying) {
						event.setCancelled(true);
						player.sendMessage(plugin.config.getString("message"));
					}
				}
				// Else, if the data for the block IS 7 (The crop is fully grown) and the player
				// has the permission node so the crop automatically re-grows after being harvested...
				else if((byte) block.getData() == 7 && player.hasPermission("SuperWheat.regrowing")) {
					event.setCancelled(true);
					// Set the block to a data value of 0, which is what the crop looks
					// like right when you just plant it. With a light delay...
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							block.setData((byte) 0);
						}
					}, 1L);
					// Drop wheat from the crop. The amount of wheat is determined from the random number.
					// Check for creative guys!
					if (!plugin.noDropsCreative) dropWheat(block);
					else if (player.getGameMode() != GameMode.CREATIVE)	dropWheat(block);
					// Drop seeds, too (0-3)
					// Check for creative guys!
					if (player.hasPermission("SuperWheat.seed")) {
						if (!plugin.noDropsCreative) dropSeeds(block);
						else if (player.getGameMode() != GameMode.CREATIVE) dropSeeds(block);
					}
				}
			}
		}
	}

	// Drops wheat
	private void dropWheat(Block block) {
		block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT, (int)(Math.random()*3) + 1));
	}

	// Drops seeds
	private void dropSeeds(Block block) {
		block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.SEEDS, (int)(Math.random()*4)));
	}
}