package com.extendedclip.papiexpansion.example;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class ExampleExpansion extends PlaceholderExpansion {

	@Override
	public String getAuthor() {
		return "clip";
	}

	@Override
	public String getIdentifier() {
		return "example";
	}

	@Override
	public String getPlugin() {
		return null;
	}

	@Override
	public String getVersion() {
		return "1.0.1";
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
		}
		return null;
	}

}
