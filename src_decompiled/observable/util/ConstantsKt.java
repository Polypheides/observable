/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.chat.Style
 *  org.jetbrains.annotations.NotNull
 */
package observable.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import observable.util.ClickEventUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=2, xi=48, d1={"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2={"MOD_URL", "", "MOD_URL_COMPONENT", "Lnet/minecraft/network/chat/Component;", "getMOD_URL_COMPONENT", "()Lnet/minecraft/network/chat/Component;", "observable"})
public final class ConstantsKt {
    @NotNull
    public static final String MOD_URL = "https://observable.tas.sh/";
    @NotNull
    private static final Component MOD_URL_COMPONENT;

    @NotNull
    public static final Component getMOD_URL_COMPONENT() {
        return MOD_URL_COMPONENT;
    }

    private static final Style MOD_URL_COMPONENT$lambda$0(Style it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return it.withClickEvent(ClickEventUtils.createOpenUrl(MOD_URL));
    }

    static {
        MutableComponent mutableComponent = Component.literal((String)MOD_URL).withStyle(ChatFormatting.UNDERLINE).withStyle(ConstantsKt::MOD_URL_COMPONENT$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent, (String)"withStyle(...)");
        MOD_URL_COMPONENT = (Component)mutableComponent;
    }
}

