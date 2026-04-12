/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.components.Checkbox
 *  net.minecraft.network.chat.Component
 *  org.jetbrains.annotations.NotNull
 */
package observable.client;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=2, xi=48, d1={"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001aJ\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r0\f\u00a8\u0006\u000e"}, d2={"BetterCheckbox", "Lnet/minecraft/client/gui/components/Checkbox;", "x", "", "y", "width", "height", "component", "Lnet/minecraft/network/chat/Component;", "default", "", "callback", "Lkotlin/Function1;", "", "observable"})
public final class BetterCheckboxKt {
    @NotNull
    public static final Checkbox BetterCheckbox(int x, int y, int width, int height, @NotNull Component component, boolean bl, @NotNull Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter((Object)component, (String)"component");
        Intrinsics.checkNotNullParameter(callback, (String)"callback");
        Checkbox checkbox = Checkbox.builder((Component)component, (Font)Minecraft.getInstance().font).pos(x, y).maxWidth(width).selected(bl).onValueChange((arg_0, arg_1) -> BetterCheckboxKt.BetterCheckbox$lambda$0(callback, arg_0, arg_1)).build();
        Intrinsics.checkNotNullExpressionValue((Object)checkbox, (String)"build(...)");
        return checkbox;
    }

    private static final void BetterCheckbox$lambda$0(Function1 $callback, Checkbox checkbox, boolean bl) {
        Intrinsics.checkNotNullParameter((Object)checkbox, (String)"<unused var>");
        $callback.invoke((Object)bl);
    }
}

