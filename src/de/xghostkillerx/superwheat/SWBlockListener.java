package de.xghostkillerx.superwheat;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.inventory.ItemStack;

public class SWBlockListener extends BlockListener {
	
	public SuperWheat plugin;
	public SWBlockListener(SuperWheat instance){
		plugin = instance;
	}
	
	public void onBlockPhysics(BlockPhysicsEvent event){
		//Material block2 = event.getChangedType();
		Block block = event.getBlock();
		
		if(block.getType() == Material.CROPS){
			if(block.getData() != (byte)7){
				event.setCancelled(true);
				//block.setData(block.getData());
			} else{
				event.setCancelled(true);
				Random rand = new Random();
				int r = rand.nextInt(3) + 1;
				block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(59, r));
			}
		}
	}
}
