package net.stuntguy3000.command;
import net.stuntguy3000.MFPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MFCommand implements CommandExecutor {
	public MFPlugin plugin;
	
	public MFCommand (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			if (args.length < 1)
			{
				String Authors = "";
				int Count = 0;
				
				for (String name : plugin.getDescription().getAuthors())
				{
					Count ++;
					
					if (Count == plugin.getDescription().getAuthors().size())
					{
						Authors = Authors + name;
					} else {
						Authors = Authors + name + "&7, &b";
					}
				}
				
				p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6" + plugin.getDescription().getDescription()));
				p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&e" + plugin.getDescription().getWebsite()));
				p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&9Version: &b" + plugin.getDescription().getVersion()));
				p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&9Created by: &b" + Authors));
				p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cFor commands: Type /mf help"));
				
				return true;
			}
			
			if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("help"))
				{
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bThe help menu is seperated into sections."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf help arena &8-&e All arena related commands"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf help game &8-&e All game related commands"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf help stats &8-&e All stats related commands"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf help other &8-&e All other commands"));
					return true;
				}
			} else if (args.length == 2)
			{
				//Here we go!
				if (args[0].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("arena"))
				{
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bArena Commands:"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf arena create <ArenaName> &8-&e Create an arena."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf arena remove <ArenaName> &8-&e Remove an arena."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf arena list &8-&e List all the arenas."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf arena setspawn <ArenaName> <Red/Blue> &8-&e Set an arena's team spawn point."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf arena setblock <ArenaName> <Red/Blue> &8-&e Set an arena's target block."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf arena setname <ArenaName> <Name> &8-&e Change an Arena's Name."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf arena check <ArenaName> &8-&e View an arena's information."));
					return true;
				}
				
				if (args[0].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("game"))
				{
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bGame Commands:"));
					// TBA
					return true;
				}
				
				if (args[0].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("stats"))
				{
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bStats Commands:"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf stats &8-&e Display your stats"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf stats [name]&8-&e Display someone else's stats"));
					return true;
				}
				
				if (args[0].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("other"))
				{
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bOther Commands:"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf join <Arena> &8-&e Try to join an arena."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf leave &8-&e Leave the game."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/mf reload &8-&e Reload configuration files"));
					return true;
				}
			}
			
			p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cInvalid command (or wrong syntax)! Type /mf help for command help."));
			
		} else {
			// Comming soon.
		}
		
		return false;
	}

}
