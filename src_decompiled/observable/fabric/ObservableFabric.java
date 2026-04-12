/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  net.fabricmc.api.ModInitializer
 */
package observable.fabric;

import kotlin.Metadata;
import net.fabricmc.api.ModInitializer;
import observable.Observable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0006"}, d2={"Lobservable/fabric/ObservableFabric;", "Lnet/fabricmc/api/ModInitializer;", "<init>", "()V", "onInitialize", "", "observable"})
public final class ObservableFabric
implements ModInitializer {
    public void onInitialize() {
        Observable.INSTANCE.onInitialize();
    }
}

