package net.stuntguy3000.command;

import net.stuntguy3000.MFPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class MFCommand implements CommandExecutor {
	public MFPlugin plugin;
	
	public MFCommand (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			if (args.length < 1)
			{
				
			}
			
		} else {
			
		}
		
		return false;
	}

}
