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
	private final SuperWheatPlayerListener playerListener = new SuperWheatPlayerListener(this);
	private final SuperWheatBlockListener blockListener = new SuperWheatBlockListener(this);
	public boolean preventWater = true, preventWaterGrown, noDropsCreative = true, blockCreativeDestroying;
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
		pm.registerEvents(playerListener, this);
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
		config.addDefault("preventWater", true);
		config.addDefault("preventWaterGrown", false);
		config.addDefault("noDropsCreative", true);
		config.addDefault("blockCreativeDestroying", false);
		config.options().copyDefaults(true);
		saveConfig();
		preventWater = config.getBoolean("preventWater");
		preventWaterGrown = config.getBoolean("preventWaterGrown");
		noDropsCreative = config.getBoolean("noDropsCreative");
		blockCreativeDestroying = config.getBoolean("blockCreativeDestroying");
		message = config.getString("message");
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