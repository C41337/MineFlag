package net.stuntguy3000;

import net.stuntguy3000.enums.LogType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MFUtil {
	
	private MFPlugin plugin;
	
	public String MessagePrefix = "&8[&7MineFlag&8] ";
	
	public MFUtil(MFPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public void noPermission(Player p, String permission)
	{
		if (plugin.config.ShowPermissionNeeded)
		{
			p.sendMessage(c(MessagePrefix + "&cYou do not have permission!"));
			p.sendMessage(c(MessagePrefix + "&cYou need &7%node&c!".replaceAll("%node", permission)));
		} else {
			p.sendMessage(c(MessagePrefix + "&cYou do not have permission!"));
		}
	}
	
	public void log(LogType lt, String message)
	{
		if (lt == null || message == null)
		{
			return;
		}
		
		if (lt == LogType.Debug)
		{
			if (plugin.config.Debug)
				Bukkit.getConsoleSender().sendMessage(c("&e[MineFlag Debug] " + message));
		} else if (lt == LogType.Normal)
		{
			Bukkit.getConsoleSender().sendMessage(c("[MineFlag] " + message));
		} else if (lt == LogType.Severe)
		{
			Bukkit.getConsoleSender().sendMessage(c("&4[MineFlag] [SEVERE] " + message));
		} else if (lt == LogType.Warning)
		{
			Bukkit.getConsoleSender().sendMessage(c("&c[MineFlag] [Warning] " + message));
		}
	}
	
	public String c(String text)
	{
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
}
