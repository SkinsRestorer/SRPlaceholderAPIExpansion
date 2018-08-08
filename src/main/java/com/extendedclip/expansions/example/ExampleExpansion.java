package com.extendedclip.expansions.example;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.deluxetags.DeluxeTags;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class ExampleExpansion extends PlaceholderExpansion {

    private DeluxeTags deluxeTags;

    @Override
    public boolean canRegister() {
        if (!Bukkit.getPluginManager().isPluginEnabled(getRequiredPlugin())) { return false; }
        deluxeTags = (DeluxeTags) Bukkit.getPluginManager().getPlugin(getRequiredPlugin());
        return super.register() && deluxeTags != null;
    }

    @Override
    public String getRequiredPlugin() {
        return "DeluxeTags";
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
    public String getVersion() {
        return "1.0.3";
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String params) {
        if (params.equals("test")) { return "success"; }
        if (offlinePlayer == null || !offlinePlayer.isOnline()) { return "player is not online"; }

        Player player = offlinePlayer.getPlayer();
        switch (params) {
            case "name":
                return player.getName();
            case "display_name":
                return player.getDisplayName();
            case "gamemode":
                return player.getGameMode().name();
            case "health":
                return Double.toString(player.getHealth());
            case "tag_menu_name":
                String name = deluxeTags.getGuiOptions().getMenuName();
                return name != null ? name : "";
            default:
                return null;
        }
    }

}
