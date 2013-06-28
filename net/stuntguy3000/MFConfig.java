package net.stuntguy3000;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class MFConfig {
	public MFPlugin plugin;
	
	// MySQL
	public Boolean UseMySQL = true;
	public String Host = null;
	public String Name = null;
	public String User = null;
	public String Pass = null;
	
	// Vairous
	public Boolean Debug = false;
	public Boolean ShowPermissionNeeded = true;
	
	public MFConfig (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	public void loadOptions()
	{
		File uf = new File(plugin.getDataFolder() + "config.yml");
		YamlConfiguration uc = YamlConfiguration.loadConfiguration(uf);
		
		// MySQL
		UseMySQL = plugin.getConfig().getBoolean("MySQL.UseMySQL");
		Host = plugin.getConfig().getString("MySQL.Host");
		Name = plugin.getConfig().getString("MySQL.Name");
		User = plugin.getConfig().getString("MySQL.User");
		Pass = plugin.getConfig().getString("MySQL.Pass");
		
		// Vairous
		Debug = plugin.getConfig().getBoolean("Vairous.Debug");
		ShowPermissionNeeded = plugin.getConfig().getBoolean("Vairous.ShowPermissionNeeded");
	}
}
