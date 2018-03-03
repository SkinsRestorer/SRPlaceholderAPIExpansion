package com.extendedclip.papiexpansion.example;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.deluxetags.DeluxeTags;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class ExampleExpansion extends PlaceholderExpansion {

	private DeluxeTags plugin;
	
	@Override
	public boolean canRegister() {
		if (Bukkit.getPluginManager().getPlugin(getPlugin()) == null) {
			return false;
		}
		
		plugin = (DeluxeTags) Bukkit.getPluginManager().getPlugin(getPlugin());
		return plugin != null;
	}
	
	@Override
	public String getAuthor() {
		return "everyone";
	}

	@Override
	public String getIdentifier() {
		return "example";
	}

	@Override
	public String getPlugin() {
		return "DeluxeTags";
	}

	@Override
	public String getVersion() {
		return "1.0.2";
	}

	@Override
	public String onPlaceholderRequest(Player p, String arg) {
		
		if (arg.equals("test")) {
			return "success";
		}
		
		//lets do some stuff with a player
		if (p == null) {
			return "player is null";
		}
		
		switch (arg) {
		case "name":
			return p.getName();
		case "displayname":
			return p.getDisplayName();
		case "gamemode":
			return p.getGameMode().name();
		case "health":
			return Double.toString(p.getHealth());
		case "tag_menu_name":
			String name = plugin.getGuiOptions().getMenuName();
			return name != null ? name : "";
		}
		return null;
	}

}
