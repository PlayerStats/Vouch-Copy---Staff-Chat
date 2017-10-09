package net.staffchat.playerstats.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.staffchat.playerstats.StaffChatPlugin;
import net.staffchat.playerstats.command.StaffChatCommand;

public class StaffChatListener implements Listener {
	
	private StaffChatPlugin plugin;
	
	public StaffChatListener(StaffChatPlugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		if(plugin.getToggled().contains(e.getPlayer().getName()))
			plugin.getToggled().remove(e.getPlayer().getName());
	}
	
	@EventHandler
	public void command(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().equalsIgnoreCase("/screload")) {
			if(e.getPlayer().hasPermission("sc.reload")) {
			plugin.reloadConfig();
			e.getPlayer().sendMessage(ChatColor.RED + "Done.");
			e.setCancelled(true);
			} else {
				e.getPlayer().sendMessage(ChatColor.RED + "No permission.");
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onSpeak(AsyncPlayerChatEvent e) {
		if(plugin.getToggled().contains(e.getPlayer().getName())) {
			StaffChatCommand.sendPermissionMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("format").replaceAll("%name%", e.getPlayer().getName()).replaceAll("%message%", e.getMessage())),"sc.talk");
			e.setCancelled(true);
		}
	}

}
