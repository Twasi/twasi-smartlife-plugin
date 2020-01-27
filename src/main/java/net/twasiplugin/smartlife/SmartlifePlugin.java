package net.twasiplugin.smartlife;

import net.twasi.core.plugin.TwasiPlugin;
import net.twasi.core.plugin.api.TwasiUserPlugin;

public class SmartlifePlugin extends TwasiPlugin<Object> {

    public Class<? extends TwasiUserPlugin> getUserPluginClass() {
        return SmartlifeUserplugin.class;
    }
}
