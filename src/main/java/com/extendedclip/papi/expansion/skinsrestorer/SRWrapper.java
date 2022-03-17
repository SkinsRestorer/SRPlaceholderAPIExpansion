package com.extendedclip.papi.expansion.skinsrestorer;

import net.skinsrestorer.api.SkinsRestorerAPI;

public class SRWrapper {
    private SRWrapper() {
    }

    public static String getSkinName(String name) {
        try {
            return SkinsRestorerAPI.getApi().getSkinName(name);
        } catch (NoClassDefFoundError ignored) {
            System.out.println("[SRPlaceholderAPIExpansion] You are using unsupported version of SkinsRestorer. Use v14.1.0 or newer!");
            return null;
        }
    }
    public static String getSkinTextureUrl(String skinName) {
        try {
            return SkinsRestorerAPI.getApi().getSkinTextureUrl(skinName);
        } catch (NoClassDefFoundError ignored) {
            System.out.println("[SRPlaceholderAPIExpansion] You are using unsupported version of SkinsRestorer. Use v14.1.11 or newer!");
            return null;
        }
    }
}
