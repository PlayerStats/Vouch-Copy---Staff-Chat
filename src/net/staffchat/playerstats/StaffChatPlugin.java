package net.staffchat.playerstats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import net.staffchat.playerstats.command.StaffChatCommand;
import net.staffchat.playerstats.listener.StaffChatListener;

public class StaffChatPlugin extends JavaPlugin {
	
	@Getter private List<String> toggled = new ArrayList<>();
	//TODO: Use dependency injection
	@Getter private static StaffChatPlugin instance;
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		
		registerCommands();
		registerListeners();
	}
	
	
	@Override
	public void onDisable() {
		
	}
	
	void registerCommands() {
		new StaffChatCommand(this);
	}
	void registerListeners() {
		new StaffChatListener(this);
	}

}
