package de.dustplanet.superwheat;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.inventory.ItemStack;

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
		// If the block is a wheat
		if (event.getToBlock().getType() == Material.CROPS) {
			final Block block = event.getToBlock();
			// If water flows "over" it
			if (event.getBlock().getType() == Material.WATER || event.getBlock().getType() == Material.STATIONARY_WATER) {
				// Fully grown
				if ((byte) block.getData() == 7) {
					// Should we cancel this?
					if (plugin.preventWaterGrown) event.setCancelled(true);
					else {
						// Set to air and drop. Then wait the delay and make it a premature block again
						block.setTypeId(0);
						if (plugin.waterDropSeeds) dropSeeds(block);
						if (plugin.waterDropWheat) dropWheat(block);
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								block.setTypeIdAndData(59, (byte) 0, true);
							}
						}, (20 * plugin.delayWater));
					}
				}
				// MUST be a premature block, cancel it or not?
				else if (plugin.preventWater) event.setCancelled(true);
			}
		}
	}
	
	// NOT WORKING!
	@EventHandler
	public void onBlockPistonExtend (BlockPistonExtendEvent event) {
		PistonMoveReaction reaction = event.getBlock().getPistonMoveReaction();
		if (reaction == PistonMoveReaction.BREAK) plugin.log.info("yes");
		if (reaction == PistonMoveReaction.BLOCK) plugin.log.info("block");
		if (reaction == PistonMoveReaction.MOVE) plugin.log.info("move");
		for (final Block block : event.getBlocks()) {
			plugin.log.info(block.toString());
			if (block.getType() == Material.CROPS) {
				plugin.log.info("Yes");
				// Set to air and drop. Then wait the delay and make it a premature block again
				block.setTypeId(0);
				if (plugin.waterDropSeeds) dropSeeds(block);
				if (plugin.waterDropWheat) dropWheat(block);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						plugin.log.info("Delay");
						block.setTypeIdAndData(59, (byte) 0, true);
					}
				}, (20 * plugin.delayWater));
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		final Block block = event.getBlock();
		// If that block is a crop (Block ID #59)...
		if(block.getType() == Material.CROPS) {
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
				block.setTypeId(0);
				// Set the block to a data value of 0, which is what the crop looks
				// like right when you just plant it. With a light delay...
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						block.setTypeIdAndData(59, (byte) 0, true);
					}
				}, (20 * plugin.delayHit));
				// Drop wheat from the crop. The amount of wheat is determined from the random number.
				// Check for creative guys!
				if (plugin.dropsCreative) dropWheat(block);
				else if (player.getGameMode() != GameMode.CREATIVE)	dropWheat(block);
				// Drop seeds, too (0-3)
				// Check for creative guys!
				if (player.hasPermission("SuperWheat.seed")) {
					if (plugin.dropsCreative) dropSeeds(block);
					else if (player.getGameMode() != GameMode.CREATIVE) dropSeeds(block);
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