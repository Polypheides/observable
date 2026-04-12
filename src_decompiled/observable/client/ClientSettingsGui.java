/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Pair
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.MutablePropertyReference0Impl
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.reflect.KMutableProperty0
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphicsExtractor
 *  net.minecraft.client.gui.components.AbstractWidget
 *  net.minecraft.client.gui.components.EditBox
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.util.FormattedCharSequence
 *  org.jetbrains.annotations.NotNull
 */
package observable.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KMutableProperty0;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.FormattedCharSequence;
import observable.client.BetterCheckboxKt;
import observable.client.ClientSettings;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0002J\b\u0010\u0010\u001a\u00020\u0005H\u0014J(\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u001c\u0010\n\u001a\r\u0012\t\u0012\u00070\f\u00a2\u0006\u0002\b\r0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0018"}, d2={"Lobservable/client/ClientSettingsGui;", "Lnet/minecraft/client/gui/screens/Screen;", "<init>", "()V", "entry", "", "y", "", "prop", "Lkotlin/reflect/KMutableProperty0;", "fields", "", "Lnet/minecraft/network/chat/MutableComponent;", "Lkotlin/jvm/internal/EnhancedNullability;", "getFields", "()Ljava/util/List;", "init", "extractRenderState", "graphics", "Lnet/minecraft/client/gui/GuiGraphicsExtractor;", "i", "j", "f", "", "observable"})
@SourceDebugExtension(value={"SMAP\nClientSettings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientSettings.kt\nobservable/client/ClientSettingsGui\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,98:1\n1586#2:99\n1661#2,3:100\n*S KotlinDebug\n*F\n+ 1 ClientSettings.kt\nobservable/client/ClientSettingsGui\n*L\n59#1:99\n59#1:100,3\n*E\n"})
public final class ClientSettingsGui
extends Screen {
    @NotNull
    private final List<MutableComponent> fields;

    /*
     * WARNING - void declaration
     */
    public ClientSettingsGui() {
        super((Component)Component.translatable((String)"screen.observable.client_settings"));
        void $this$mapTo$iv$iv;
        void $this$map$iv;
        Object object = new String[]{"maxBlockDist", "maxEntityDist", "maxEntityCount", "normalize"};
        object = CollectionsKt.listOf((Object[])object);
        ClientSettingsGui clientSettingsGui = this;
        boolean $i$f$map = false;
        void var3_4 = $this$map$iv;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map$iv, (int)10));
        boolean $i$f$mapTo = false;
        for (Object item$iv$iv : $this$mapTo$iv$iv) {
            void it;
            String string = (String)item$iv$iv;
            Collection collection = destination$iv$iv;
            boolean bl = false;
            collection.add(Component.translatable((String)("text.observable." + (String)it)));
        }
        clientSettingsGui.fields = (List)destination$iv$iv;
    }

    private final void entry(int y, KMutableProperty0<Integer> prop) {
        EditBox box = new EditBox(Minecraft.getInstance().font, this.width * 3 / 4, y, 40, 20, (Component)Component.literal((String)""));
        box.setValue(String.valueOf(((Number)prop.get()).intValue()));
        box.setResponder(arg_0 -> ClientSettingsGui.entry$lambda$0(prop, arg_0));
        this.addRenderableWidget((GuiEventListener)box);
    }

    @NotNull
    public final List<MutableComponent> getFields() {
        return this.fields;
    }

    protected void init() {
        super.init();
        this.entry(20, (KMutableProperty0<Integer>)((KMutableProperty0)new MutablePropertyReference0Impl(ClientSettings.INSTANCE){

            public Object get() {
                return ((ClientSettings)this.receiver).getMaxBlockDist();
            }

            public void set(Object value) {
                ((ClientSettings)this.receiver).setMaxBlockDist(((Number)value).intValue());
            }
        }));
        this.entry(40, (KMutableProperty0<Integer>)((KMutableProperty0)new MutablePropertyReference0Impl(ClientSettings.INSTANCE){

            public Object get() {
                return ((ClientSettings)this.receiver).getMaxEntityDist();
            }

            public void set(Object value) {
                ((ClientSettings)this.receiver).setMaxEntityDist(((Number)value).intValue());
            }
        }));
        this.entry(60, (KMutableProperty0<Integer>)((KMutableProperty0)new MutablePropertyReference0Impl(ClientSettings.INSTANCE){

            public Object get() {
                return ((ClientSettings)this.receiver).getMaxEntityCount();
            }

            public void set(Object value) {
                ((ClientSettings)this.receiver).setMaxEntityCount(((Number)value).intValue());
            }
        }));
        int n = this.width * 3 / 4;
        int n2 = this.width / 2;
        MutableComponent mutableComponent = Component.literal((String)"");
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent, (String)"literal(...)");
        this.addRenderableWidget((GuiEventListener)BetterCheckboxKt.BetterCheckbox(n, 90, n2, 20, (Component)mutableComponent, true, (Function1<? super Boolean, Unit>)((Function1)ClientSettingsGui::init$lambda$0)));
    }

    public void extractRenderState(@NotNull GuiGraphicsExtractor graphics, int i, int j, float f) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        super.extractRenderState(graphics, i, j, f);
        Iterable iterable = this.fields;
        List list = this.children();
        Intrinsics.checkNotNullExpressionValue((Object)list, (String)"children(...)");
        for (Pair pair : CollectionsKt.zip((Iterable)iterable, (Iterable)list)) {
            Object object = pair.component1();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"component1(...)");
            MutableComponent field = (MutableComponent)object;
            GuiEventListener entry = (GuiEventListener)pair.component2();
            FormattedCharSequence formattedCharSequence = field.getVisualOrderText();
            int n = this.width / 4;
            Intrinsics.checkNotNull((Object)entry, (String)"null cannot be cast to non-null type net.minecraft.client.gui.components.AbstractWidget");
            graphics.text(this.font, formattedCharSequence, n, ((AbstractWidget)entry).getY(), 0xFFFFFF, true);
        }
    }

    private static final void entry$lambda$0(KMutableProperty0 $prop, String it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        try {
            $prop.set((Object)Integer.parseInt(it));
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
    }

    private static final Unit init$lambda$0(boolean it) {
        ClientSettings.INSTANCE.setNormalized(it);
        return Unit.INSTANCE;
    }
}

