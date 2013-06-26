package net.stuntguy3000;

import net.stuntguy3000.command.MFCommand;
import net.stuntguy3000.enums.LogType;
import net.stuntguy3000.manager.mgr_Database;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MFPlugin extends JavaPlugin {
	
	public MFUtil util;
	public MFConfig config;
	public mgr_Database db;
	
	public void onEnable() {
		util = new MFUtil(this);
		config = new MFConfig(this);
		db = new mgr_Database(this);
		
		config.loadOptions();
		
		util.log(LogType.Debug, "Coppying default config.");
		getConfig().options().copyDefaults(true);
		saveConfig();
		util.log(LogType.Debug, "Copy complete.");
		
		getCommand("mineflag").setExecutor(new MFCommand(this));
		
		util.log(LogType.Debug, "Running initial query...");
		db.sendQuery("CREATE TABLE IF NOT EXISTS `mineflag_arenas` " +
						"( `ArenaName` varchar(256) NOT NULL, " +
						"`World` varchar(256) NOT NULL, " +
						"`TotalPlayers` int(10) NOT NULL )" +
						" ENGINE=InnoDB DEFAULT CHARSET=latin1; ");
		
		db.sendQuery("CREATE TABLE IF NOT EXISTS `mineflag_locations` " +
				"( `Arena` varchar(256) NOT NULL, " +
				"`Team` enum('Red','Blue') NOT NULL, " +
				"`Type` enum('Spawn','TargetBlock') NOT NULL, " +
				"`x` int(10) NOT NULL, " +
				"`y` int(10) NOT NULL, " +
				"`z` int(10) NOT NULL, " +
				"`yaw` double NOT NULL, " +
				"`pitch` double NOT NULL ) ENGINE=InnoDB DEFAULT CHARSET=latin1; ");
		
		db.sendQuery("CREATE TABLE IF NOT EXISTS `mineflag_users` " +
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
	}
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
	}
	
	
	
}
