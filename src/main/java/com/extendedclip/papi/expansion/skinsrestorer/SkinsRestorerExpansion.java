package com.extendedclip.papi.expansion.skinsrestorer;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import skinsrestorer.bukkit.SkinsRestorer;
import skinsrestorer.shared.exception.SkinRequestException;
import me.clip.deluxetags.DeluxeTags;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class SkinsRestorerExpansion extends PlaceholderExpansion {
    private DeluxeTags deluxeTags;
    private SkinsRestorer skinsRestorer;

    private final String VERSION = getClass().getPackage().getImplementationVersion();

    public boolean canRegister() {
        return (Bukkit.getPluginManager().getPlugin("SkinsRestorer") != null);
        // might not be needed
        /*if (!Bukkit.getPluginManager().isPluginEnabled(getRequiredPlugin())) { return false; }
        deluxeTags = (DeluxeTags) Bukkit.getPluginManager().getPlugin(getRequiredPlugin());
        return super.register() && deluxeTags != null; */
    }

    public boolean register() {
        this.skinsRestorer = (SkinsRestorer)Bukkit.getPluginManager().getPlugin("SkinsRestorer");
        if (this.skinsRestorer != null)
            return super.register();
        return false;
    }

    public String getAuthor() {
        return "SRTeam";
    }

    public String getIdentifier() {
        return "SkinsRestorer";
    }

    public String getVersion() {
        return VERSION;
    }

    //dunno what this is
    /*public String getRequiredPlugin() {
        return "SkinsRestorer";
    }*/


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
