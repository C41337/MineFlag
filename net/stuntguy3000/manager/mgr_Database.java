package net.stuntguy3000.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.bukkit.scheduler.BukkitRunnable;

import net.stuntguy3000.MFPlugin;
import net.stuntguy3000.enums.LogType;

public class mgr_Database {
	public MFPlugin plugin;
	
	public mgr_Database (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	public void sendQuery(final String query)
	{
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
	    				String connectionUrl = "jdbc:mysql://" + plugin.config.Host + ":3306/" + plugin.config.Name;
	    				String connectionUser = plugin.config.User;
	    				String connectionPassword = plugin.config.Pass;
	    				Connection con = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
	    				
	    				Statement statement = con.createStatement();			
	    				
	    				statement.executeUpdate(query);
	    						
	    				con.close();
	    				
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
		}
	}
	

}
