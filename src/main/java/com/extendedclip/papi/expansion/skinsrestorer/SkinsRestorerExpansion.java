package com.extendedclip.papi.expansion.skinsrestorer;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

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
    public String onRequest(OfflinePlayer offlinePlayer, String params) {
        if (params.equals("test")) {
            return "success";
        }

        final String p = offlinePlayer.getName();
        if (p == null)
            return "Player can't be null";

        // %getSkinName%
        if (params.equals("getSkinName")) {
            String name = wrapper.getSkinName(p);

            if (name != null) {
                if (name.contains(" "))
                    return "Url_skin";

                return name;
            } else {
                return "None";
            }
        }

        // %getTextureUrl_Or_PlayerName%
        if (params.equals("getTextureUrl_Or_PlayerName")) {
            String url = wrapper.getSkinTextureUrl(wrapper.getSkinName(p));

            if (url == null || url.equals("null"))
                url = p;

            return url;
        }

        // %getTextureUrl_Or_Empty%
        if (params.equals("getTextureUrl_Or_Empty")) {
            String url = wrapper.getSkinTextureUrl(wrapper.getSkinName(p));

            if (url == null || url.equals("null"))
                url = "";

            return url;
        }

        // %getTextureUrl_Or_Null%
        if (params.equals("getTextureUrl_Or_Null")) {
            return wrapper.getSkinTextureUrl(wrapper.getSkinName(p));
        }

        // %getTextureUrl_Or_Steve%
        if (params.equals("getTextureUrl_Or_Steve")) {
            String url = wrapper.getSkinTextureUrl(wrapper.getSkinName(p));

            if (url == null || url.equals("null"))
                url = "http://textures.minecraft.net/texture/6d3b06c38504ffc0229b9492147c69fcf59fd2ed7885f78502152f77b4d50de1";

            return url;
        }

        // %getTextureUrl_Or_Alex%
        if (params.equals("getTextureUrl_Or_Alex")) {
            String url = wrapper.getSkinTextureUrl(wrapper.getSkinName(p));

            if (url == null || url.equals("null"))
                url = "http://textures.minecraft.net/texture/fb9ab3483f8106ecc9e76bd47c71312b0f16a58784d606864f3b3e9cb1fd7b6c";

            return url;
        }

        return null;
    }
}
