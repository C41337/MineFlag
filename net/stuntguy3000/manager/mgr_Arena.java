package net.stuntguy3000.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.stuntguy3000.MFPlugin;
import net.stuntguy3000.enums.LogType;
import net.stuntguy3000.enums.Team;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class mgr_Arena {
	public MFPlugin plugin;
	
	public mgr_Arena (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	public Location getSpawn(Team team, String arena)
	{
		File af = new File(plugin.getDataFolder() + File.separator + "arenas.yml");
		YamlConfiguration ac = YamlConfiguration.loadConfiguration(af);
		
		if (team == null || arena == null || arena == "")
		{
			plugin.util.log(LogType.Warning, "[NULL-LOC] Team: " + team + " | Arena: " + arena);
			return null;
		}
		
		if (ac.getString(arena + ".Spawns." + team.name() + ".world") == null)
		{
			return null;
		}
		
		Location spawn = new Location(null, 1, 1, 1);
		
		spawn.setX(ac.getInt(arena + ".Spawns." + team.name() + ".x"));
		spawn.setY(ac.getInt(arena + ".Spawns." + team.name() + ".y"));
		spawn.setZ(ac.getInt(arena + ".Spawns." + team.name() + ".z"));
		spawn.setYaw(ac.getInt(arena + ".Spawns." + team.name() + ".yaw"));
		spawn.setPitch(ac.getInt(arena + ".Spawns." + team.name() + ".pitch"));
		spawn.setWorld(Bukkit.getWorld(ac.getString(arena + ".Spawns." + team.name() + ".world")));
		
		return spawn;
	}
	
	public void setSpawn(Team team, String arena, Location spawn)
	{
		File af = new File(plugin.getDataFolder() + File.separator + "arenas.yml");
		YamlConfiguration ac = YamlConfiguration.loadConfiguration(af);
		
		if (team == null || arena == null || arena == "" || spawn == null)
		{
			plugin.util.log(LogType.Warning, "[SET-LOC] Team: " + team + " | Arena: " + arena);
			plugin.util.log(LogType.Warning, "[SET-LOC] " + spawn.toString());
			
			return;
		}
		
		try {
			ac.set(arena + ".Spawns." + team.name() + ".x", (int) spawn.getX());
			ac.set(arena + ".Spawns." + team.name() + ".y", (int) spawn.getY());
			ac.set(arena + ".Spawns." + team.name() + ".z", (int) spawn.getZ());
			ac.set(arena + ".Spawns." + team.name() + ".yaw", (int) spawn.getYaw());
			ac.set(arena + ".Spawns." + team.name() + ".pitch", (int) spawn.getPitch());
			ac.set(arena + ".Spawns." + team.name() + ".world", spawn.getWorld().getName());
			ac.save(af);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exists(String arena)
	{
		File af = new File(plugin.getDataFolder() + File.separator + "arenas.yml");
		YamlConfiguration ac = YamlConfiguration.loadConfiguration(af);
		
		return ac.contains(arena);
	}
	
	public List<String> getArenaList()
	{
		File af = new File(plugin.getDataFolder() + File.separator + "arenas.yml");
		YamlConfiguration ac = YamlConfiguration.loadConfiguration(af);
		
		List<String> r = new ArrayList<String>();
		
		for (String name : ac.getKeys(false))
		{
			r.add(name);
		}
		
		return r;
	}
	
	public void createArena(String arena)
	{
		File af = new File(plugin.getDataFolder() + File.separator + "arenas.yml");
		YamlConfiguration ac = YamlConfiguration.loadConfiguration(af);
		
		try {
			ac.set(arena + ".Valid", true);
			ac.save(af);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeArena(String arena)
	{
		File af = new File(plugin.getDataFolder() + File.separator + "arenas.yml");
		YamlConfiguration ac = YamlConfiguration.loadConfiguration(af);
		
		try {
			ac.set(arena, null);
			ac.save(af);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
