package net.stuntguy3000;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MFPlugin extends JavaPlugin {
	
	Logger log = Bukkit.getLogger();
	
	public void onEnable() {
		debug("Starting config copy...");
		getConfig().options().copyDefaults(true);
		saveConfig();
		debug("...Complete.");
	}
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
	}
	
	public void debug(String msg)
	{
		log.info("[MineFlag Debug] " + msg);
	}
	
}
