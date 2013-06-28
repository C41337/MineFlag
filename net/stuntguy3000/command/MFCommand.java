package net.stuntguy3000.command;
import java.io.File;

import net.stuntguy3000.MFPlugin;
import net.stuntguy3000.enums.LogType;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
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
				p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cFor commands: Type /MineFlag help"));
				
				return true;
			}
			
			if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("help"))
				{
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bThe help menu is seperated into sections."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/MineFlag help arena -&e All arena related commands"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/MineFlag help stats -&e All stats related commands"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/MineFlag help other -&e All other commands"));
					return true;
				}
				
				if (args[0].equalsIgnoreCase("stats"))
				{
					if (p.hasPermission("MineFlag.Command.Stats"))
					{
						File uf = new File(plugin.getDataFolder() + File.separator + "users", p.getName() + ".yml");
						YamlConfiguration uc = YamlConfiguration.loadConfiguration(uf);
						
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6====== &eYour Stats &6======"));
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Kills: &e" + uc.getInt("Kills")));
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Deaths: &e" + uc.getInt("Deaths")));
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Games: &e" + uc.getInt("Games")));
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Captures: &e" + uc.getInt("Captures")));
					} else {
						plugin.util.noPermission(p, "MineFlag.Command.Stats");
					}
					return true;
				}
				
				if (args[0].equalsIgnoreCase("leave"))
				{
					
					
					return true;
				}
			} else if (args.length == 2)
			{
				//Here we go! - Help
				if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("arena"))
				{
					p.sendMessage(plugin.util.c(""));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bArena Commands:"));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena create <ArenaName>"));
					p.sendMessage(plugin.util.c(" &a- &e&oCreate an arena."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena remove <ArenaName>"));
					p.sendMessage(plugin.util.c(" &a- &e&oRemove an arena."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena listList all the arenas."));
					p.sendMessage(plugin.util.c(" &a- &e&oRemove an arena."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena setspawn <ArenaName> <Red/Blue>"));
					p.sendMessage(plugin.util.c(" &a- &e&oSet an arena's team spawn point."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena setblock <ArenaName> <Red/Blue>"));
					p.sendMessage(plugin.util.c(" &a- &e&oSet an arena's target block."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena setname <ArenaName> <Name>"));
					p.sendMessage(plugin.util.c(" &a- &e&oChange an Arena's Name."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena check <ArenaName>"));
					p.sendMessage(plugin.util.c(" &a- &e&oView an arena's information."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena set <ArenaName> <Setting> <Value>"));
					p.sendMessage(plugin.util.c(" &a- &e&oChange an Arena's settings."));
					p.sendMessage(plugin.util.c(""));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cTo full the view list, press your chat key."));
					return true;
				}
				
				if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("stats"))
				{
					p.sendMessage(plugin.util.c(""));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bStats Commands:"));
					p.sendMessage(plugin.util.c(" &6/MineFlag stats"));
					p.sendMessage(plugin.util.c(" &a- &e&oDisplay your stats."));
					p.sendMessage(plugin.util.c(" &6/MineFlag stats [Name]"));
					p.sendMessage(plugin.util.c(" &a- &e&oDisplay someone else's stats."));
					p.sendMessage(plugin.util.c(" &6/MineFlag stats clear [Name]"));
					p.sendMessage(plugin.util.c(" &a- &e&oClear someone's stats."));
					p.sendMessage(plugin.util.c(" &6/MineFlag stats set [Name] [Option] [Value]"));
					p.sendMessage(plugin.util.c(" &a- &e&oChange someone's stats."));
					
					return true;
				}
				
				if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("other"))
				{
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bOther Commands:"));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/MineFlag join <Arena> &a-&e Try to join an arena."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/MineFlag leave &a-&e Leave the game."));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6/MineFlag reload &a-&e Reload configuration files"));
					
					p.sendMessage(plugin.util.c(""));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bOther Commands:"));
					p.sendMessage(plugin.util.c(" &6/MineFlag join [Arena]"));
					p.sendMessage(plugin.util.c(" &a- &e&oTry to join an arena."));
					p.sendMessage(plugin.util.c(" &6/MineFlag leave"));
					p.sendMessage(plugin.util.c(" &a- &e&oLeave a game."));
					return true;
				}
			}
			
			p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cInvalid command (or wrong syntax)! Type /MineFlag help for command help."));
			
		} else {
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
						Authors = Authors + name + ", ";
					}
				}
				
				plugin.util.log(LogType.Normal, "" + plugin.getDescription().getDescription());
				plugin.util.log(LogType.Normal, "" + plugin.getDescription().getWebsite());
				plugin.util.log(LogType.Normal, "Version: " + plugin.getDescription().getVersion());
				plugin.util.log(LogType.Normal, "Created by: " + Authors);
				plugin.util.log(LogType.Normal, "For commands: Type /MineFlag help");
				
				return true;
			}
			
			if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("help"))
				{
					plugin.util.log(LogType.Normal, "The help menu is seperated into sections.");
					plugin.util.log(LogType.Normal, "/MineFlag help arena - All arena related commands");
					plugin.util.log(LogType.Normal, "/MineFlag help stats - All stats related commands");
					return true;
				}
			} else if (args.length == 2)
			{
				//Here we go! - Help
				if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("arena"))
				{
					plugin.util.log(LogType.Normal, "");
					plugin.util.log(LogType.Normal, "Arena Commands:");
					plugin.util.log(LogType.Normal, " /MineFlag arena create <ArenaName>");
					plugin.util.log(LogType.Normal, " - Create an arena.");
					plugin.util.log(LogType.Normal, " /MineFlag arena remove <ArenaName>");
					plugin.util.log(LogType.Normal, " - Remove an arena.");
					plugin.util.log(LogType.Normal, " /MineFlag arena listList all the arenas.");
					plugin.util.log(LogType.Normal, " - Remove an arena.");
					plugin.util.log(LogType.Normal, " /MineFlag arena setspawn <ArenaName> <Red/Blue>");
					plugin.util.log(LogType.Normal, " - Set an arena's team spawn point.");
					plugin.util.log(LogType.Normal, " /MineFlag arena setblock <ArenaName> <Red/Blue>");
					plugin.util.log(LogType.Normal, " - Set an arena's target block.");
					plugin.util.log(LogType.Normal, " /MineFlag arena setname <ArenaName> <Name>");
					plugin.util.log(LogType.Normal, " - Change an Arena's Name.");
					plugin.util.log(LogType.Normal, " /MineFlag arena check <ArenaName>");
					plugin.util.log(LogType.Normal, " - View an arena's information.");
					plugin.util.log(LogType.Normal, " /MineFlag arena set <ArenaName> <Setting> <Value>");
					plugin.util.log(LogType.Normal, " - Change an Arena's settings.");
					return true;
				}
				
				if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("stats"))
				{
					plugin.util.log(LogType.Normal, "");
					plugin.util.log(LogType.Normal, "Stats Commands:");
					plugin.util.log(LogType.Normal, " /MineFlag stats [Name]");
					plugin.util.log(LogType.Normal, " - Display someone else's stats.");
					plugin.util.log(LogType.Normal, " /MineFlag stats clear [Name]");
					plugin.util.log(LogType.Normal, " - Clear someone's stats.");
					plugin.util.log(LogType.Normal, " /MineFlag stats set [Name] [Option] [Value]");
					plugin.util.log(LogType.Normal, " - Change someone's stats.");
					
					return true;
				}
			}
			
			plugin.util.log(LogType.Normal, "Invalid command (or wrong syntax)! Type /MineFlag help for command help.");
			
		}
		
		return false;
	}

}
