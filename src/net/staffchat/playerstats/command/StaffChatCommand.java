package net.staffchat.playerstats.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.staffchat.playerstats.StaffChatPlugin;

public class StaffChatCommand implements CommandExecutor {
	
	private StaffChatPlugin plugin;
	
	public StaffChatCommand(StaffChatPlugin plugin) {
		this.plugin = plugin;
		plugin.getCommand("staffchat").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("sc.talk")) {
			if(args.length == 0) {
				if(sender instanceof Player) {
				if(plugin.getToggled().contains(sender.getName())) {
					
					plugin.getToggled().remove(sender.getName());
					sender.sendMessage(ChatColor.RED + "You are no longer in staffchat mode.");
					return true;
				
					
				} else {
					
					plugin.getToggled().add(sender.getName());
					sender.sendMessage(ChatColor.GREEN + "You are now in staffchat mode. Speak if you wish to.");
					return true;
				
				}
				} else {
					sender.sendMessage(ChatColor.RED + "This can ONLY be used by players, console. You can do /sc [message] though!");
					return true;
				}
			}
			if(args.length > 0) {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < args.length; i++) {
					sb.append(args[i]).append(" ");
				}
				String message = sb.toString();
				sendPermissionMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("format").replaceAll("%name%", sender.getName()).replaceAll("%message%", message)),"sc.talk");
			}
			return true;
		}
		sender.sendMessage(ChatColor.RED + "No permission!");
		return true;
	}
	public static void sendPermissionMessage(String message, String perm) {
		Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(perm)).forEach(player -> player.sendMessage(message));
	}
	
	

}
