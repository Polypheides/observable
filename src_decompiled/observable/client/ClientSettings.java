/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.multiplayer.ClientLevel
 *  org.jetbrains.annotations.NotNull
 */
package observable.client;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.multiplayer.ClientLevel;
import observable.client.Overlay;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\u001a\u0010\u001a\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\n\u00a8\u0006\u001d"}, d2={"Lobservable/client/ClientSettings;", "", "<init>", "()V", "v", "", "minRate", "getMinRate", "()I", "setMinRate", "(I)V", "maxBlockDist", "getMaxBlockDist", "setMaxBlockDist", "maxEntityDist", "getMaxEntityDist", "setMaxEntityDist", "", "normalized", "getNormalized", "()Z", "setNormalized", "(Z)V", "maxBlockCount", "getMaxBlockCount", "setMaxBlockCount", "maxEntityCount", "getMaxEntityCount", "setMaxEntityCount", "observable"})
@SourceDebugExtension(value={"SMAP\nClientSettings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientSettings.kt\nobservable/client/ClientSettings\n+ 2 Overlay.kt\nobservable/client/Overlay\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,98:1\n102#2:99\n102#2:101\n1#3:100\n1#3:102\n*S KotlinDebug\n*F\n+ 1 ClientSettings.kt\nobservable/client/ClientSettings\n*L\n16#1:99\n28#1:101\n16#1:100\n28#1:102\n*E\n"})
public final class ClientSettings {
    @NotNull
    public static final ClientSettings INSTANCE = new ClientSettings();
    private static int minRate;
    private static int maxBlockDist;
    private static int maxEntityDist;
    private static boolean normalized;
    private static int maxBlockCount;
    private static int maxEntityCount;

    private ClientSettings() {
    }

    public final int getMinRate() {
        return minRate;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void setMinRate(int v) {
        minRate = v;
        Overlay $this$iv = Overlay.INSTANCE;
        ClientLevel lvl$iv = null;
        boolean $i$f$loadSync = false;
        Overlay overlay = $this$iv;
        synchronized (overlay) {
            boolean bl = false;
            Overlay.INSTANCE.load(lvl$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final int getMaxBlockDist() {
        return maxBlockDist;
    }

    public final synchronized void setMaxBlockDist(int n) {
        maxBlockDist = n;
    }

    public final int getMaxEntityDist() {
        return maxEntityDist;
    }

    public final synchronized void setMaxEntityDist(int n) {
        maxEntityDist = n;
    }

    public final boolean getNormalized() {
        return normalized;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void setNormalized(boolean v) {
        normalized = v;
        Overlay $this$iv = Overlay.INSTANCE;
        ClientLevel lvl$iv = null;
        boolean $i$f$loadSync = false;
        Overlay overlay = $this$iv;
        synchronized (overlay) {
            boolean bl = false;
            Overlay.INSTANCE.load(lvl$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final int getMaxBlockCount() {
        return maxBlockCount;
    }

    public final void setMaxBlockCount(int n) {
        maxBlockCount = n;
    }

    public final int getMaxEntityCount() {
        return maxEntityCount;
    }

    public final void setMaxEntityCount(int n) {
        maxEntityCount = n;
    }

    static {
        maxBlockDist = 128;
        maxEntityDist = 2048;
        normalized = true;
        maxBlockCount = 2000;
        maxEntityCount = 2000;
    }
}

