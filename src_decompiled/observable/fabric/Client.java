/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.fabricmc.api.ClientModInitializer
 *  org.jetbrains.annotations.NotNull
 */
package observable.fabric;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.fabricmc.api.ClientModInitializer;
import observable.Observable;
import observable.client.ProfileScreen;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0007"}, d2={"Lobservable/fabric/Client;", "Lnet/fabricmc/api/ClientModInitializer;", "<init>", "()V", "onInitializeClient", "", "Companion", "observable"})
public final class Client
implements ClientModInitializer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Lazy<ProfileScreen> PROFILE_SCREEN$delegate = LazyKt.lazy(Client::PROFILE_SCREEN_delegate$lambda$0);

    public void onInitializeClient() {
        Observable.clientInit();
    }

    private static final ProfileScreen PROFILE_SCREEN_delegate$lambda$0() {
        return new ProfileScreen();
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2={"Lobservable/fabric/Client$Companion;", "", "<init>", "()V", "PROFILE_SCREEN", "Lobservable/client/ProfileScreen;", "getPROFILE_SCREEN", "()Lobservable/client/ProfileScreen;", "PROFILE_SCREEN$delegate", "Lkotlin/Lazy;", "observable"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ProfileScreen getPROFILE_SCREEN() {
            Lazy lazy = PROFILE_SCREEN$delegate;
            return (ProfileScreen)((Object)lazy.getValue());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

