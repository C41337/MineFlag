package net.stuntguy3000;

import net.stuntguy3000.command.MFCommand;
import net.stuntguy3000.enums.LogType;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MFPlugin extends JavaPlugin {
	
	public MFUtil util;
	public MFConfig config;
	
	public void onEnable() {
		util = new MFUtil(this);
		config = new MFConfig(this);
		
		config.loadOptions();
		
		util.log(LogType.Debug, "Coppying default config.");
		getConfig().options().copyDefaults(true);
		saveConfig();
		util.log(LogType.Debug, "Copy complete.");
		
		getCommand("mineflag").setExecutor(new MFCommand(this));
		
		//Bukkit.getServer().getPluginManager().registerEvents(new EventListener(this), this);
	}
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
	}
	
	
	
}
