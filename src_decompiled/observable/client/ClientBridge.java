/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.KeyMapping
 */
package observable.client;

import net.minecraft.client.KeyMapping;

public interface ClientBridge {
    public void clear();

    public KeyMapping getSettingsKey();

    public KeyMapping getOverlayKey();
}

