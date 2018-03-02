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
		return "1.0.0";
	}

	@Override
	public String onPlaceholderRequest(Player p, String arg) {
		if (arg.equals("test")) {
			return "success";
		}
		return null;
	}

}
