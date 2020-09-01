package com.extendedclip.papi.expansion.skinsrestorer;

import me.clip.deluxetags.DeluxeTags;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import skinsrestorer.bukkit.SkinsRestorer;
import skinsrestorer.bukkit.SkinsRestorerBukkitAPI;

public class SkinsRestorerExpansion extends PlaceholderExpansion {
    private final String VERSION = getClass().getPackage().getImplementationVersion();
    private DeluxeTags deluxeTags;
    private SkinsRestorer skinsRestorer;
    private SkinsRestorerBukkitAPI skinsRestorerBukkitAPI;

    public SkinsRestorerExpansion() {
    }

    public boolean canRegister() {
        return (Bukkit.getPluginManager().getPlugin("SkinsRestorer") != null);
    }

    public boolean register() {
        this.skinsRestorer = (SkinsRestorer) Bukkit.getPluginManager().getPlugin("SkinsRestorer");
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
    public String getRequiredPlugin() {
        return "SkinsRestorer";
    }

    /**
     * This method is called when a placeholder is used and maches the set
     * {@link #getIdentifier() identifier}
     *
     * @param offlinePlayer The player to parse placeholders for
     * @param params        The part after the identifier ({@code %identifier_params%})
     * @return Possible-null String
     */
    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String params) {
        if (params.equals("test")) {
            return "success";
        }
        if (offlinePlayer == null || !offlinePlayer.isOnline()) {
            return "player is not online";
        }

        skinsRestorer = JavaPlugin.getPlugin(SkinsRestorer.class);
        skinsRestorerBukkitAPI = skinsRestorer.getSkinsRestorerBukkitAPI();
        Player player = offlinePlayer.getPlayer();

        String p = player.getName();

        switch (params) {
            case "getSkinName":
                String name = skinsRestorerBukkitAPI.getSkinName(p);
                if (name != null) {
                    if (name.contains(" "))
                        return "Url_skin";
                    return name;
                } else {
                    return p;
                }

            default:
                return null;
        }
    }

}
