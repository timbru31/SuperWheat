package de.dustplanet.superwheat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * SuperWheat for CraftBukkit/Bukkit Handles some general stuff!
 * Refer to the forum thread:
 * http://bit.ly/superwheatthread
 * Refer to the dev.bukkit.org page: http://bit.ly/superwheatpage
 * 
 * @author  xGhOsTkiLLeRx
 * @thanks  to thescreem for the original SuperWheat plugin!
 */

public class SuperWheat extends JavaPlugin {

	public Logger log = Logger.getLogger("Minecraft");
	private final SuperWheatBlockListener blockListener = new SuperWheatBlockListener(this);
	public boolean preventWater = true, preventWaterGrown, dropsCreative = false, blockCreativeDestroying, waterDropWheat = true, waterDropSeeds;
	public int delayHit = 3, delayWater = 5;
	public String message = "§6[SuperWheat] That crop isn't fully grown yet!";
	public FileConfiguration config;
	private File configFile;

	// Shutdown
	public void onDisable() {
		// Message
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is disabled!");
	}

	// Start
	public void onEnable() {
		// Events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(blockListener, this);
		
		// Config
		configFile = new File(getDataFolder(), "config.yml");
		if(!configFile.exists()){
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
		config = this.getConfig();
		loadConfig();

		// Message
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is enabled!");
		
	}

	private void loadConfig() {
		config.options().header("For help please either refer to the\nforum thread: http://bit.ly/superwheatthread\nor the bukkit dev page: http://bit.ly/superwheatpage");
		config.addDefault("message", "§6[SuperWheat] That crop isn't fully grown yet!");
		config.addDefault("water.drops.wheat", true);
		config.addDefault("water.drops.seed", false);
		config.addDefault("water.prevent.premature", true);
		config.addDefault("water.prevent.mature", false);
		config.addDefault("creative.dropsCreative", false);
		config.addDefault("creative.blockCreativeDestroying", false);
		config.addDefault("delayHit", 3);
		config.addDefault("water.delayWater", 5);
		config.options().copyDefaults(true);
		saveConfig();
		preventWater = config.getBoolean("water.prevent.premature");
		preventWaterGrown = config.getBoolean("water.prevent.mature");
		dropsCreative = config.getBoolean("creative.dropsCreative");
		blockCreativeDestroying = config.getBoolean("creative.blockCreativeDestroying");
		message = config.getString("message");
		delayHit = config.getInt("delayHit");
		delayWater = config.getInt("water.delayWater");
		waterDropWheat = config.getBoolean("water.drops.wheat");
		waterDropSeeds = config.getBoolean("water.drops.seed");
	}
	
	// If no config is found, copy the default one!
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len=in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}