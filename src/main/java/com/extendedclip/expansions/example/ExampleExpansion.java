package com.extendedclip.expansions.example;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.deluxetags.DeluxeTags;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class ExampleExpansion extends PlaceholderExpansion {
    
    private final String VERSION = getClass().getPackage().getImplementationVersion();
    private DeluxeTags deluxeTags;

    /**
     * Defines the name of the expansion that is also used in the
     * placeholder itself.
     * 
     * @return {@code example} as String
     */
    @Override
    public String getIdentifier() {
        return "example";
    }

    /**
     * The author of the expansion.
     * 
     * @return {@code everyone} as String
     */
    @Override
    public String getAuthor() {
        return "everyone";
    }

    /**
     * Returns the version of the expansion as String.
     *
     * @return The VERSION String
     */
    @Override
    public String getVersion() {
        return VERSION;
    }

    /**
     * Returns the name of the required plugin.
     *
     * @return {@code DeluxeTags} as String
     */
    @Override
    public String getRequiredPlugin() {
        return "DeluxeTags";
    }

    /**
     * Used to check if the expansion is able to register.
     * 
     * @return true or false depending on if the required plugin is installed
     */
    @Override
    public boolean canRegister() {
        if (!Bukkit.getPluginManager().isPluginEnabled(getRequiredPlugin())) { return false; }
        deluxeTags = (DeluxeTags) Bukkit.getPluginManager().getPlugin(getRequiredPlugin());
        return super.register() && deluxeTags != null;
    }

    /**
     * This method is called when a placeholder is used and maches the set
     * {@link #getIdentifier() identifier}
     *
     * @param  offlinePlayer
     *         The player to parse placeholders for
     * @param  params
     *         The part after the identifier ({@code %identifier_params%})
     *
     * @return Possible-null String
     */
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
