package net.stuntguy3000.events;

import net.stuntguy3000.MFPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class evt_LoginHandler implements Listener {
	public MFPlugin plugin;
	
	public evt_LoginHandler (MFPlugin instance)
	{
		this.plugin = instance;
	}
	
	@EventHandler
	public void AsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
		final String username = event.getName();
		
		plugin.db_User.getUserData(username);
	}
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
	}
	
	@EventHandler
	public void PlayerQuitEvent(final PlayerQuitEvent event) {
		Player p = event.getPlayer();
		
	}
	
	@EventHandler
	public void PlayerKickEvent(final PlayerKickEvent event) {
		Player p = event.getPlayer();
		
	}
	
}
