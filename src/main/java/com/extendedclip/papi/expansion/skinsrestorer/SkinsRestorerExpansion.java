package com.extendedclip.papi.expansion.skinsrestorer;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class SkinsRestorerExpansion extends PlaceholderExpansion {
    @Override
    public boolean canRegister() {
        return (Bukkit.getPluginManager().getPlugin("SkinsRestorer") != null);
    }

    @Override
    public boolean register() {
        if (Bukkit.getPluginManager().getPlugin("SkinsRestorer") != null) {
            return super.register();
        } else {
            System.out.println("[SRPlaceholderAPIExpansion] ERROR SkinsRestorer not installed!");
            return false;
        }
    }

    @Override
    public @NotNull String getAuthor() {
        return "SRTeam";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "SkinsRestorer";
    }

    @Override
    public @NotNull String getVersion() {
        return getClass().getPackage().getImplementationVersion();
    }

    @Override
    public String getRequiredPlugin() {
        return "SkinsRestorer";
    }

    /**
     * This method is called when a placeholder is used and matches the set
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

        String p = offlinePlayer.getName();

        if (p == null)
            return "Player can't be null";

        if (params.equals("getSkinName")) {
            String name = SRWrapper.getSkinName(p);

            if (name != null) {
                if (name.contains(" "))
                    return "Url_skin";

                return name;
            } else {
                return "None";
            }
        }

        return null;
    }

}
