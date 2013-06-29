package net.stuntguy3000.command;
import net.stuntguy3000.MFPlugin;
import net.stuntguy3000.enums.LogType;
import net.stuntguy3000.enums.StatType;
import net.stuntguy3000.enums.Team;

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
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6====== &eYour Stats &6======"));
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Kills: &e" + plugin.stats.getStats(StatType.Kills, p.getName())));
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Deaths: &e" + plugin.stats.getStats(StatType.Deaths, p.getName())));
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Games: &e" + plugin.stats.getStats(StatType.Games, p.getName())));
						p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Captures: &e" + plugin.stats.getStats(StatType.Captures, p.getName())));
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
				if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("arena"))
				{
					p.sendMessage(plugin.util.c(""));
					p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bArena Commands:"));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena create <ArenaName>"));
					p.sendMessage(plugin.util.c(" &a- &e&oCreate an arena."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena remove <ArenaName>"));
					p.sendMessage(plugin.util.c(" &a- &e&oRemove an arena."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena list"));
					p.sendMessage(plugin.util.c(" &a- &e&oList all the arenas"));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena setspawn <ArenaName> <Red/Blue>"));
					p.sendMessage(plugin.util.c(" &a- &e&oSet an arena's team spawn point."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena setblock <ArenaName> <Red/Blue>"));
					p.sendMessage(plugin.util.c(" &a- &e&oSet an arena's target block."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena setname <ArenaName> <Name>"));
					p.sendMessage(plugin.util.c(" &a- &e&oChange an Arena's Name."));
					p.sendMessage(plugin.util.c(" &6/MineFlag arena gotospawn <ArenaName> <Red/Blue>"));
					p.sendMessage(plugin.util.c(" &a- &e&oTeleport to a team's spawnpoint."));
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
				
				if (args[0].equalsIgnoreCase("stats"))
				{
					if (p.hasPermission("MineFlag.command.stats.other." + args[1]))
					{
						if (plugin.stats.exists(args[1]))
						{
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6====== &e" + args[1] + "'s Stats &6======"));
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Kills: &e" + plugin.stats.getStats(StatType.Kills, args[1])));
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Deaths: &e" + plugin.stats.getStats(StatType.Deaths, args[1])));
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Games: &e" + plugin.stats.getStats(StatType.Games, args[1])));
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6Captures: &e" + plugin.stats.getStats(StatType.Captures, args[1])));
						
						} else {
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eThe user you specified &6(" + args[1] + ") &ehas never joined the server!"));
						}
						
						
					} else {
						plugin.util.noPermission(p, "MineFlag.command.stats.other.");
					}
					return true;
				}
				
				if (args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("list"))
				{
					if (p.hasPermission("MineFlag.command.arena.list"))
					{
						if (plugin.arena.getArenaList().size() == 0)
						{
							p.sendMessage(plugin.util.c(""));
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eThere are no arenas!"));
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&6To create one type /MineFlag arena create [Name]"));
						} else {
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&bArena List &3(" + plugin.arena.getArenaList().size() + ")&b:"));
							
							String Arenas = "";
							int Count = 0;
							
							for (String name : plugin.arena.getArenaList())
							{
								Count ++;
								
								if (Count == plugin.arena.getArenaList().size())
								{
									Arenas = Arenas + name + "&8.";
								} else {
									Arenas = Arenas + name + "&8, &7";
								}
							}
							
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&7" + Arenas));
						}
					} else {
						plugin.util.noPermission(p, "MineFlag.command.arena.list");
					}
					
					return true;
				}
			} else if (args.length == 3) {
				if (args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("create"))
				{
					if (p.hasPermission("MineFlag.command.arena.create"))
					{
						if (plugin.arena.exists(args[2]))
						{
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eArena &6(" + args[2] + ") &ealready exists!"));
						} else {
							plugin.arena.createArena(args[2]);
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eArena &6(" + args[2] + ") &ecreated."));
						}
					} else {
						plugin.util.noPermission(p, "MineFlag.command.arena.create");
					}
					
					return true;
				}
				
				if (args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("remove"))
				{
					if (p.hasPermission("MineFlag.command.arena.remove"))
					{
						if (plugin.arena.exists(args[2]))
						{
							plugin.arena.removeArena(args[2]);
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eArena &6(" + args[2] + ") &eremoved."));
						} else {
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eArena &6(" + args[2] + ") &enot found."));
						}
					} else {
						plugin.util.noPermission(p, "MineFlag.command.arena.remove");
					}
					
					return true;
				}
				
				if (args[0].equalsIgnoreCase("stats") && args[1].equalsIgnoreCase("clear"))
				{
					if (p.hasPermission("MineFlag.command.clearstats"))
					{
						if (plugin.stats.exists(args[2]))
						{
							plugin.stats.clearStats(args[2]);
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eStats cleared for &6" + args[2] + "&e."));
						} else {
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eThe user you specified &6(" + args[2] + ") &edoes not exist."));
						}
					} else {
						plugin.util.noPermission(p, "MineFlag.command.clearstats");
					}
					
					return true;
				}
			} else if (args.length == 4) {
				if (args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("setname"))
				{
					if (p.hasPermission("MineFlag.command.arena.setname"))
					{
						if (plugin.arena.exists(args[2]))
						{
							
						} else {
							
						}
					} else {
						plugin.util.noPermission(p, "MineFlag.command.clearstats");
					}
					
					return true;
				}
				
				if (args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("setspawn"))
				{
					if (p.hasPermission("MineFlag.command.arena.setspawn"))
					{
						if (plugin.arena.exists(args[2]))
						{
							if (args[3].equalsIgnoreCase("Red"))
							{
								plugin.arena.setSpawn(Team.Red, args[2], p.getLocation());
								p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eSpawn for &cRed &eArena &6(" + args[2] + ") &eupdated."));
							} else if (args[3].equalsIgnoreCase("Blue"))
							{
								plugin.arena.setSpawn(Team.Blue, args[2], p.getLocation());
								p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eSpawn for &9Blue &eArena &6(" + args[2] + ") &eupdated."));
							} else {
								p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eInvalid team! Either &cRed &eor &9Blue&e."));
							}
						} else {
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eArena &6(" + args[2] + ") &enot found."));
						}
					} else {
						plugin.util.noPermission(p, "MineFlag.command.clearstats");
					}
					
					return true;
				}
				
				if (args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("gotospawn"))
				{
					if (p.hasPermission("MineFlag.command.arena.gotospawn." + args[2]))
					{
						if (plugin.arena.exists(args[2]))
						{
							if (args[3].equalsIgnoreCase("Red"))
							{
								if (plugin.arena.getSpawn(Team.Red, args[2]) != null)
								{
									if (plugin.arena.getSpawn(Team.Red, args[2]).getWorld() != null)
									{
										p.teleport(plugin.arena.getSpawn(Team.Red, args[2]));
										p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eYou are now at &cRed&e's spawn in &6" + args[2]));
									} else {
										p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cError: World &4" + plugin.arena.getSpawn(Team.Red, args[2]).getWorld().getName() + " &cis invalid!"));
									}
								} else {
									p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cNo spawn is set!"));
								}
							} else if (args[3].equalsIgnoreCase("Blue")) {
								if (plugin.arena.getSpawn(Team.Blue, args[2]) != null)
								{
									if (plugin.arena.getSpawn(Team.Blue, args[2]).getWorld() != null)
									{
										p.teleport(plugin.arena.getSpawn(Team.Blue, args[2]));
										p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eYou are now at &9Blue&e's spawn in &6" + args[2]));
									} else {
										p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cError: World &4" + plugin.arena.getSpawn(Team.Red, args[2]).getWorld().getName() + " &cis invalid!"));
									}
								} else {
									p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&cNo spawn is set!"));
								}
							} else {
								p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eInvalid team! Either &cRed &eor &9Blue&e."));
							}
						} else {
							p.sendMessage(plugin.util.c(plugin.util.MessagePrefix + "&eArena &6(" + args[2] + ") &enot found."));
						}
					} else {
						plugin.util.noPermission(p, "MineFlag.command.clearstats");
					}
					
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
				if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("arena"))
				{
					plugin.util.log(LogType.Normal, "");
					plugin.util.log(LogType.Normal, "Arena Commands:");
					plugin.util.log(LogType.Normal, " /MineFlag arena create <ArenaName>");
					plugin.util.log(LogType.Normal, " - Create an arena.");
					plugin.util.log(LogType.Normal, " /MineFlag arena remove <ArenaName>");
					plugin.util.log(LogType.Normal, " - Remove an arena.");
					plugin.util.log(LogType.Normal, " /MineFlag arena list");
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
