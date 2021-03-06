package net.stuntguy3000.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import net.stuntguy3000.MFPlugin;
import net.stuntguy3000.enums.StatType;

public class mgr_Stats {
	public MFPlugin plugin;
	
	public mgr_Stats (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	public int getStats(StatType st, String username)
	{
		File uf = new File(plugin.getDataFolder() + File.separator + "users", username + ".yml");
		YamlConfiguration uc = YamlConfiguration.loadConfiguration(uf);
		
		if (st == null || username == null || username == "" || !uf.exists())
		{
			return 0;
		}
		
		return uc.getInt(st.name());
	}
	
	public void setStat(StatType st, String username, int value)
	{
		File uf = new File(plugin.getDataFolder() + File.separator + "users", username + ".yml");
		YamlConfiguration uc = YamlConfiguration.loadConfiguration(uf);
		
		if (st == null || username == null || username == "" || !uf.exists())
		{
			return;
		}
		
		try {
			uc.set(st.name(), value);
			uc.save(uf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearStats(String username)
	{
		File uf = new File(plugin.getDataFolder() + File.separator + "users", username + ".yml");
		YamlConfiguration uc = YamlConfiguration.loadConfiguration(uf);
		
		try {
			uc.set("Kills", 0);
			uc.set("Deaths", 0);
			uc.set("Games", 0);
			uc.set("Captures", 0);
			uc.save(uf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean exists(String username)
	{
		File uf = new File(plugin.getDataFolder() + File.separator + "users", username + ".yml");
		
		return uf.exists();
	}
}

