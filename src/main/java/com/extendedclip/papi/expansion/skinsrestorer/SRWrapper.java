package com.extendedclip.papi.expansion.skinsrestorer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.skinsrestorer.api.SkinsRestorerAPI;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SRWrapper {
    private final SkinsRestorerExpansion expansion;

    public String getSkinName(String name) {
        try {
            return SkinsRestorerAPI.getApi().getSkinName(name);
        } catch (Error e) {
            expansion.severe("You are using unsupported version of SkinsRestorer. Use v14.1.0 or newer!", e);
            return null;
        }
    }
    public String getSkinTextureUrl(String skinName) {
        try {
            return SkinsRestorerAPI.getApi().getSkinTextureUrl(skinName);
        } catch (Error e) {
            expansion.severe("You are using unsupported version of SkinsRestorer. Use v14.1.11 or newer!", e);
            return null;
        }
    }
}
