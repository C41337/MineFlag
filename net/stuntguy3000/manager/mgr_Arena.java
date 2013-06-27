package net.stuntguy3000.manager;

import org.bukkit.Location;

import net.stuntguy3000.MFPlugin;
import net.stuntguy3000.enums.LogType;
import net.stuntguy3000.enums.Team;

public class mgr_Arena {
	public MFPlugin plugin;
	
	public mgr_Arena (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	public Location getSpawn(Team team, String arena)
	{
		if (team == null || arena == null || arena == "")
		{
			plugin.util.log(LogType.Warning, "[NULL-LOC] Team: " + team + " | Arena: " + arena);
			return null;
		}
		
		if (team == Team.Red)
		{
			Location spawn = null;
			
			return spawn;
		} else if (team == Team.Blue) {
			Location spawn = null;
			
			return spawn;
		} else {
			return null;
		}
	}
	
	public void setSpawn(Team team, String arena, Location spawn)
	{
		if (team == null || arena == null || arena == "" || spawn == null)
		{
			plugin.util.log(LogType.Warning, "[SET-LOC] Team: " + team + " | Arena: " + arena);
			plugin.util.log(LogType.Warning, "[SET-LOC] " + spawn.toString());
			
			return;
		}
		
		if (team == Team.Red)
		{
			
			
			return;
		} else if (team == Team.Blue) {
			
			
			return;
		}
	}
}
