package com.extendedclip.papi.expansion.skinsrestorer;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkinsRestorerExpansion extends PlaceholderExpansion {
    private SRWrapper wrapper;

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

    @Override
    public boolean register() {
        wrapper = new SRWrapper(this);
        return super.register();
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
    public @Nullable String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {
        params = params.toLowerCase();
        if (params.equalsIgnoreCase("test")) {
            return "success";
        }

        final String p = offlinePlayer.getName();
        if (p == null)
            return "Player can't be null";

        // %getSkinName%
        if (params.equalsIgnoreCase("getSkinName")) {
            String name = wrapper.getSkinName(p);

            if (name != null) {
                if (name.contains(" "))
                    return "Url_skin";

                return name;
            } else {
                return "None";
            }
        }

        if (params.toLowerCase().startsWith("gettextureurl_"))
            return getSkinTextureUrl(p, params.toLowerCase().replace("gettextureurl_", ""));

        // getSkinTextureID
        if (params.toLowerCase().startsWith("gettextureid_"))
            return getSkinTextureUrl(p, params.toLowerCase().replace("gettextureid_", ""))
                .replace("https://textures.minecraft.net/texture/", "")
                .replace("http://textures.minecraft.net/texture/", "");

        return null;
    }
    public String getSkinTextureUrl(String p, String params) {
        String url = wrapper.getSkinTextureUrl(wrapper.getSkinName(p));

        //noinspection ConstantConditions
        if (url != null || ("null").equals(url))
            return url;

        // %getTextureUrl_Or_PlayerName%
        if (params.equalsIgnoreCase("Or_PlayerName"))
            return p;

        // %getTextureUrl_Or_Empty%
        if (params.equalsIgnoreCase("Or_Empty"))
            return "";

        // %getTextureUrl_Or_Null%
        if (params.equalsIgnoreCase("Or_Null"))
            return null;

        // %getTextureUrl_Or_Steve%
        if (params.equalsIgnoreCase("Or_Steve"))
            return "http://textures.minecraft.net/texture/6d3b06c38504ffc0229b9492147c69fcf59fd2ed7885f78502152f77b4d50de1";

        // %getTextureUrl_Or_Alex%
        if (params.equalsIgnoreCase("Or_Alex"))
            return "http://textures.minecraft.net/texture/fb9ab3483f8106ecc9e76bd47c71312b0f16a58784d606864f3b3e9cb1fd7b6c";

        return null;
    }
}
