package net.stuntguy3000;

import java.io.File;
import java.io.IOException;

import net.stuntguy3000.command.MFCommand;
import net.stuntguy3000.database.db_Main;
import net.stuntguy3000.database.db_Stats;
import net.stuntguy3000.database.db_User;
import net.stuntguy3000.enums.LogType;
import net.stuntguy3000.events.evt_LoginHandler;
import net.stuntguy3000.manager.mgr_Arena;
import net.stuntguy3000.manager.mgr_Game;
import net.stuntguy3000.manager.mgr_Stats;
import net.stuntguy3000.manager.mgr_User;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MFPlugin extends JavaPlugin {
	
	public MFUtil util;
	public MFConfig config;
	
	public mgr_User user;
	public mgr_Game game;
	public mgr_Arena arena;
	public mgr_Stats stats;
	
	public db_Main db_Main;
	public db_User db_User;
	public db_Stats db_Stats;
	
	public void onEnable() {
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		
		util = new MFUtil(this);
		config = new MFConfig(this);
		user = new mgr_User(this);
		game = new mgr_Game(this);
		arena = new mgr_Arena(this);
		stats = new mgr_Stats(this);
		
		db_Main = new db_Main(this);
		db_User = new db_User(this);
		db_Stats = new db_Stats(this);
		
		File dir = new File(getDataFolder() + File.separator + "users"); 
		dir.mkdirs();
		
		File ArenasFile = new File(getDataFolder() + "/arenas.yml"); 
		
		if (!ArenasFile.exists())
		{
			saveResource("arenas.yml", true);
		}
		
		config.loadOptions();
		
		util.log(LogType.Debug, "Coppying default config.");
		getConfig().options().copyDefaults(true);
		saveConfig();
		util.log(LogType.Debug, "Copy complete.");
		
		getCommand("mineflag").setExecutor(new MFCommand(this));
		
		util.log(LogType.Debug, "Running initial query...");
		db_Main.sendQuery("CREATE TABLE IF NOT EXISTS `mineflag_arenas` " +
						"( `ArenaName` varchar(256) NOT NULL, " +
						"`World` varchar(256) NOT NULL, " +
						"`TotalPlayers` int(10) NOT NULL )" +
						" ENGINE=InnoDB DEFAULT CHARSET=latin1; ");
		
		db_Main.sendQuery("CREATE TABLE IF NOT EXISTS `mineflag_locations` " +
				"( `Arena` varchar(256) NOT NULL, " +
				"`Team` enum('Red','Blue') NOT NULL, " +
				"`Type` enum('Spawn','TargetBlock') NOT NULL, " +
				"`x` int(10) NOT NULL, " +
				"`y` int(10) NOT NULL, " +
				"`z` int(10) NOT NULL, " +
				"`yaw` double NOT NULL, " +
				"`pitch` double NOT NULL ) ENGINE=InnoDB DEFAULT CHARSET=latin1; ");
		
		db_Main.sendQuery("CREATE TABLE IF NOT EXISTS `mineflag_users` " +
				"( `ID` int(11) NOT NULL AUTO_INCREMENT, " +
				"`Username` varchar(16) NOT NULL, " +
				"`Kills` int(10) NOT NULL, " +
				"`Deaths` int(10) NOT NULL, " +
				"`Games` int(10) NOT NULL, " +
				"`Captures` int(10) NOT NULL, " +
				"PRIMARY KEY (`ID`) ) " +
			"ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
		
		if (config.UseMySQL)
		{
			util.log(LogType.Normal, "MySQL Support enabled.");
		} else {
			util.log(LogType.Normal, "MySQL Support disabled.");
		}
		
		Bukkit.getServer().getPluginManager().registerEvents(new evt_LoginHandler(this), this);
	}
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
	}
	
}
