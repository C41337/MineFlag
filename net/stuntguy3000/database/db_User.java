package net.stuntguy3000.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import net.stuntguy3000.MFPlugin;
import net.stuntguy3000.enums.LogType;

public class db_User {
	public MFPlugin plugin;
	
	public db_User (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	public void getUserData(final String username)
	{
		final File uf = new File(plugin.getDataFolder() + File.separator + "users", username + ".yml");
		final YamlConfiguration uc = YamlConfiguration.loadConfiguration(uf);
		
		if (plugin.config.UseMySQL)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				
				plugin.util.log(LogType.Severe, "Error loading MySQL Driver! Disabling Database intergration...");
				plugin.config.UseMySQL = false;
				plugin.util.log(LogType.Debug, "Use DB? " + plugin.config.UseMySQL);
				
				return;
			}
			
			new BukkitRunnable() {
	    		public void run() {
	    			try{
	    				String query = "SELECT * FROM `mineflag_users` WHERE `Username`='" + username + "'";
	    				String connectionUrl = "jdbc:mysql://" + plugin.config.Host + ":3306/" + plugin.config.Name;
	    				String connectionUser = plugin.config.User;
	    				String connectionPassword = plugin.config.Pass;
	    				Connection con = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
	    				Statement statement = con.createStatement();			
	    				ResultSet rs = statement.executeQuery(query);
	    				
	    				int rowCount = 0;
	    				
	    				while (rs.next())
	    				{
	    					rowCount ++;
	    					
	    					uc.set("Kills", rs.getInt("Kills"));
	    					uc.set("Deaths", rs.getInt("Deaths"));
	    					uc.set("Games", rs.getInt("Games"));
	    					uc.set("Captures", rs.getInt("Captures"));
	    				}
	    				
	    				if (rowCount == 0)
	    				{
	    					uc.set("Kills", 0);
	    					uc.set("Deaths", 0);
	    					uc.set("Games", 0);
	    					uc.set("Captures", 0);
	    					
	    					plugin.db_Main.sendQuery("INSERT INTO `plugindev`.`mineflag_users` (`Username`) VALUES ('" + username + "');");
	    				}
	    				
	    				con.close();
	    				
	    				plugin.util.log(LogType.Debug, "Updated user file for " + username);
	    				uc.save(uf);
	    				
	    				plugin.util.log(LogType.Debug, "Query: " + query);
	    			}
	    			catch(Exception e){
	    				e.printStackTrace();
	    					
	    				plugin.util.log(LogType.Severe, "MySQL Error! Disabling Database intergration...");
	    				plugin.config.UseMySQL = false;
	    				plugin.util.log(LogType.Debug, "Use DB? " + plugin.config.UseMySQL);
	    			}
	    		}	 
	    	}.runTask(plugin);
		} else {
			if (!uf.exists())
			{
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
		}
	}
}
