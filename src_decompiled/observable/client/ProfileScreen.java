/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function0
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  kotlin.text.Charsets
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.json.Json
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphicsExtractor
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.Checkbox
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.ConfirmLinkScreen
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.util.Util
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.client;

import java.net.URI;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.Util;
import observable.Observable;
import observable.client.BetterCheckboxKt;
import observable.client.ClientSettingsGui;
import observable.client.Overlay;
import observable.net.BetterChannel;
import observable.net.C2SPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u00014B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J>\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00180$H\u0002J\b\u0010%\u001a\u00020\u0018H\u0014J\b\u0010&\u001a\u00020\u0012H\u0016J(\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020-H\u0016J(\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u0002002\u0006\u00103\u001a\u000200H\u0016R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u00065"}, d2={"Lobservable/client/ProfileScreen;", "Lnet/minecraft/client/gui/screens/Screen;", "<init>", "()V", "value", "Lobservable/client/ProfileScreen$Action;", "action", "getAction", "()Lobservable/client/ProfileScreen$Action;", "setAction", "(Lobservable/client/ProfileScreen$Action;)V", "startBtn", "Lnet/minecraft/client/gui/components/Button;", "getStartBtn", "()Lnet/minecraft/client/gui/components/Button;", "setStartBtn", "(Lnet/minecraft/client/gui/components/Button;)V", "sample", "", "getSample", "()Z", "setSample", "(Z)V", "openLink", "", "dest", "", "button", "x", "", "y", "width", "height", "component", "Lnet/minecraft/network/chat/Component;", "onPress", "Lkotlin/Function0;", "init", "isPauseScreen", "extractRenderState", "graphics", "Lnet/minecraft/client/gui/GuiGraphicsExtractor;", "i", "j", "f", "", "mouseScrolled", "mouseX", "", "mouseY", "scrollX", "scrollY", "Action", "observable"})
@SourceDebugExtension(value={"SMAP\nProfileScreen.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProfileScreen.kt\nobservable/client/ProfileScreen\n+ 2 BetterChannel.kt\nobservable/net/BetterChannel\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,205:1\n96#2,5:206\n96#2,5:211\n1#3:216\n*S KotlinDebug\n*F\n+ 1 ProfileScreen.kt\nobservable/client/ProfileScreen\n*L\n180#1:206,5\n98#1:211,5\n*E\n"})
public final class ProfileScreen
extends Screen {
    @NotNull
    private Action action = Action.Companion.getUNAVAILABLE();
    @Nullable
    private Button startBtn;
    private boolean sample;

    public ProfileScreen() {
        super((Component)Component.translatable((String)"screen.observable.profile"));
    }

    @NotNull
    public final Action getAction() {
        return this.action;
    }

    public final void setAction(@NotNull Action value) {
        block0: {
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.action = value;
            if (this.startBtn == null) break block0;
            this.startBtn.active = value instanceof Action.NewProfile;
        }
    }

    @Nullable
    public final Button getStartBtn() {
        return this.startBtn;
    }

    public final void setStartBtn(@Nullable Button button) {
        this.startBtn = button;
    }

    public final boolean getSample() {
        return this.sample;
    }

    public final void setSample(boolean bl) {
        this.sample = bl;
    }

    private final void openLink(String dest) {
        Minecraft minecraft = Minecraft.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraft, (String)"getInstance(...)");
        Minecraft mc = minecraft;
        mc.setScreen((Screen)new ConfirmLinkScreen(arg_0 -> ProfileScreen.openLink$lambda$0(dest, mc, this, arg_0), dest, true));
    }

    private final Button button(int x, int y, int width, int height, Component component, Function0<Unit> onPress) {
        Button button = Button.builder((Component)component, arg_0 -> ProfileScreen.button$lambda$0(onPress, arg_0)).bounds(x, y, width, height).build();
        Intrinsics.checkNotNullExpressionValue((Object)button, (String)"build(...)");
        Button btn = button;
        GuiEventListener guiEventListener = this.addRenderableWidget((GuiEventListener)btn);
        Intrinsics.checkNotNullExpressionValue((Object)guiEventListener, (String)"addRenderableWidget(...)");
        return (Button)guiEventListener;
    }

    /*
     * WARNING - void declaration
     */
    protected void init() {
        super.init();
        int n = this.height / 2 - 48;
        MutableComponent mutableComponent = Component.translatable((String)"text.observable.profile_tps");
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent, (String)"translatable(...)");
        Button startBtn = this.button(0, n, 100, 20, (Component)mutableComponent, (Function0<Unit>)((Function0)() -> ProfileScreen.init$lambda$0(this)));
        startBtn.active = this.action instanceof Action.NewProfile;
        startBtn.setX(this.width / 2 - startBtn.getWidth() - 4);
        int n2 = this.width / 2 + 4;
        int n3 = startBtn.getY();
        int n4 = startBtn.getWidth();
        int n5 = startBtn.getHeight();
        MutableComponent mutableComponent2 = Component.translatable((String)"screen.observable.client_settings");
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent2, (String)"translatable(...)");
        Button settingsBtn = this.button(n2, n3, n4, n5, (Component)mutableComponent2, (Function0<Unit>)((Function0)ProfileScreen::init$lambda$1));
        int n6 = startBtn.getX();
        int n7 = startBtn.getY() + startBtn.getHeight() + 4;
        int n8 = settingsBtn.getX() + settingsBtn.getWidth() - startBtn.getX();
        MutableComponent mutableComponent3 = Component.translatable((String)"text.observable.sampler");
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent3, (String)"translatable(...)");
        GuiEventListener guiEventListener = this.addRenderableWidget((GuiEventListener)BetterCheckboxKt.BetterCheckbox(n6, n7, n8, 20, (Component)mutableComponent3, this.sample, (Function1<? super Boolean, Unit>)((Function1)arg_0 -> ProfileScreen.init$lambda$2(this, arg_0))));
        Intrinsics.checkNotNullExpressionValue((Object)guiEventListener, (String)"addRenderableWidget(...)");
        Checkbox samplerBtn = (Checkbox)guiEventListener;
        int longWidth = settingsBtn.getX() + settingsBtn.getWidth() - samplerBtn.getX();
        int smallWidth = longWidth / 3 - 2;
        int n9 = samplerBtn.getX();
        int n10 = samplerBtn.getY() + samplerBtn.getHeight() + 4;
        int n11 = samplerBtn.getWidth();
        MutableComponent mutableComponent4 = Component.translatable((String)"text.observable.overlay");
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent4, (String)"translatable(...)");
        GuiEventListener guiEventListener2 = this.addRenderableWidget((GuiEventListener)BetterCheckboxKt.BetterCheckbox(n9, n10, n11, 20, (Component)mutableComponent4, Overlay.INSTANCE.isOverlayEnabled(), (Function1<? super Boolean, Unit>)((Function1)ProfileScreen::init$lambda$3)));
        Intrinsics.checkNotNullExpressionValue((Object)guiEventListener2, (String)"addRenderableWidget(...)");
        Checkbox overlayBtn = (Checkbox)guiEventListener2;
        int n12 = startBtn.getX();
        int n13 = overlayBtn.getY() + overlayBtn.getHeight() + 8;
        MutableComponent mutableComponent5 = Component.translatable((String)"text.observable.docs");
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent5, (String)"translatable(...)");
        Button learnBtn = this.button(n12, n13, smallWidth, 20, (Component)mutableComponent5, (Function0<Unit>)((Function0)() -> ProfileScreen.init$lambda$4(this)));
        int n14 = learnBtn.getX() + learnBtn.getWidth() + 4;
        int n15 = learnBtn.getY();
        MutableComponent mutableComponent6 = Component.translatable((String)"text.observable.discord");
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent6, (String)"translatable(...)");
        Button helpBtn = this.button(n14, n15, smallWidth, 20, (Component)mutableComponent6, (Function0<Unit>)((Function0)() -> ProfileScreen.init$lambda$5(this)));
        int n16 = helpBtn.getX() + helpBtn.getWidth() + 4;
        int n17 = helpBtn.getY();
        MutableComponent mutableComponent7 = Component.translatable((String)"text.observable.donate");
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent7, (String)"translatable(...)");
        Button donateBtn = this.button(n16, n17, smallWidth, 20, (Component)mutableComponent7, (Function0<Unit>)((Function0)() -> ProfileScreen.init$lambda$6(this)));
        this.startBtn = startBtn;
        BetterChannel betterChannel = Observable.INSTANCE.getCHANNEL();
        C2SPacket.RequestAvailability msg$iv = C2SPacket.RequestAvailability.INSTANCE;
        boolean $i$f$sendToServer = false;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            void this_$iv;
            String string = C2SPacket.RequestAvailability.class.getName();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
            byte[] byArray = Json.Default.encodeToString((SerializationStrategy)C2SPacket.RequestAvailability.INSTANCE.serializer(), (Object)msg$iv).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
            BetterChannel.SerializedPayload payload$iv = new BetterChannel.SerializedPayload(string, byArray, this_$iv.getC2sLocation());
            ClientPlayNetworking.send((CustomPacketPayload)payload$iv);
        }
    }

    public boolean isPauseScreen() {
        return false;
    }

    public void extractRenderState(@NotNull GuiGraphicsExtractor graphics, int i, int j, float f) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        super.extractRenderState(graphics, i, j, f);
        String msg = this.action.getStatusMsg();
        int textX = this.width / 2 - this.font.width(msg) / 2;
        Button button = this.startBtn;
        Intrinsics.checkNotNull((Object)button);
        int textY = button.getY() - this.font.lineHeight - 6;
        graphics.text(this.font, msg, textX, textY, -1, true);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        Action.NewProfile newProfile;
        Action action = this.action;
        Action.NewProfile newProfile2 = newProfile = action instanceof Action.NewProfile ? (Action.NewProfile)action : null;
        if (newProfile != null) {
            Action.NewProfile it = newProfile;
            boolean bl = false;
            it.setDuration(it.getDuration() + MathKt.roundToInt((double)scrollY) * 5);
            it.setDuration(RangesKt.coerceIn((int)it.getDuration(), (int)5, (int)60));
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    private static final void openLink$lambda$0(String $dest, Minecraft $mc, ProfileScreen this$0, boolean bl) {
        if (bl) {
            Util.getPlatform().openUri(new URI($dest));
        }
        $mc.setScreen((Screen)this$0);
    }

    private static final void button$lambda$0(Function0 $onPress, Button it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        $onPress.invoke();
    }

    /*
     * WARNING - void declaration
     */
    private static final Unit init$lambda$0(ProfileScreen this$0) {
        Action action = this$0.action;
        Intrinsics.checkNotNull((Object)action, (String)"null cannot be cast to non-null type observable.client.ProfileScreen.Action.NewProfile");
        int duration = ((Action.NewProfile)action).getDuration();
        BetterChannel betterChannel = Observable.INSTANCE.getCHANNEL();
        C2SPacket.InitTPSProfile msg$iv = new C2SPacket.InitTPSProfile(duration, this$0.sample);
        boolean $i$f$sendToServer = false;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            void this_$iv;
            String string = C2SPacket.InitTPSProfile.class.getName();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
            byte[] byArray = Json.Default.encodeToString((SerializationStrategy)C2SPacket.InitTPSProfile.Companion.serializer(), (Object)msg$iv).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
            BetterChannel.SerializedPayload payload$iv = new BetterChannel.SerializedPayload(string, byArray, this_$iv.getC2sLocation());
            ClientPlayNetworking.send((CustomPacketPayload)payload$iv);
        }
        return Unit.INSTANCE;
    }

    private static final Unit init$lambda$1() {
        Minecraft.getInstance().setScreen((Screen)new ClientSettingsGui());
        return Unit.INSTANCE;
    }

    private static final Unit init$lambda$2(ProfileScreen this$0, boolean it) {
        this$0.sample = it;
        return Unit.INSTANCE;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final Unit init$lambda$3(boolean it) {
        if (it) {
            Overlay overlay = Overlay.INSTANCE;
            synchronized (overlay) {
                boolean bl = false;
                Overlay.load$default(Overlay.INSTANCE, null, 1, null);
                Unit unit = Unit.INSTANCE;
            }
        }
        Overlay.INSTANCE.setOverlayEnabled(it);
        return Unit.INSTANCE;
    }

    private static final Unit init$lambda$4(ProfileScreen this$0) {
        this$0.openLink("https://github.com/tasgon/observable/wiki");
        return Unit.INSTANCE;
    }

    private static final Unit init$lambda$5(ProfileScreen this$0) {
        this$0.openLink("https://discord.gg/sfPbb3b5tF");
        return Unit.INSTANCE;
    }

    private static final Unit init$lambda$6(ProfileScreen this$0) {
        this$0.openLink("https://github.com/tasgon/observable/wiki/Support-this-project");
        return Unit.INSTANCE;
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00020\u0001:\u0006\b\t\n\u000b\f\rB\t\b\u0004\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0005\u000e\u000f\u0010\u0011\u0012\u00a8\u0006\u0013"}, d2={"Lobservable/client/ProfileScreen$Action;", "", "<init>", "()V", "statusMsg", "", "getStatusMsg", "()Ljava/lang/String;", "Companion", "NewProfile", "TPSProfilerRunning", "TPSProfilerCompleted", "ObservableStatus", "Custom", "Lobservable/client/ProfileScreen$Action$Custom;", "Lobservable/client/ProfileScreen$Action$NewProfile;", "Lobservable/client/ProfileScreen$Action$ObservableStatus;", "Lobservable/client/ProfileScreen$Action$TPSProfilerCompleted;", "Lobservable/client/ProfileScreen$Action$TPSProfilerRunning;", "observable"})
    public static abstract sealed class Action {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private static final NewProfile DEFAULT = new NewProfile(30);
        @NotNull
        private static final ObservableStatus UNAVAILABLE = new ObservableStatus("text.observable.unavailable");
        @NotNull
        private static final ObservableStatus NO_PERMISSIONS = new ObservableStatus("text.observable.no_permissions");

        private Action() {
        }

        @NotNull
        public final String getStatusMsg() {
            Object object;
            Action action = this;
            if (action instanceof NewProfile) {
                object = "Duration (scroll): " + ((NewProfile)this).getDuration() + " seconds";
            } else if (action instanceof TPSProfilerRunning) {
                String string = "Running for another %.1f seconds";
                Object[] objectArray = new Object[]{RangesKt.coerceAtLeast((double)((double)(((TPSProfilerRunning)this).getEndTime() - System.currentTimeMillis()) / 1000.0), (double)0.0)};
                String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
                object = string2;
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            } else if (action instanceof TPSProfilerCompleted) {
                object = "Profiling finished, please wait...";
            } else if (action instanceof ObservableStatus) {
                String string = Component.translatable((String)((ObservableStatus)this).getText()).getString();
                object = string;
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
            } else if (action instanceof Custom) {
                object = ((Custom)this).getText();
            } else {
                throw new NoWhenBranchMatchedException();
            }
            return object;
        }

        public /* synthetic */ Action(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b\u00a8\u0006\u000e"}, d2={"Lobservable/client/ProfileScreen$Action$Companion;", "", "<init>", "()V", "DEFAULT", "Lobservable/client/ProfileScreen$Action$NewProfile;", "getDEFAULT", "()Lobservable/client/ProfileScreen$Action$NewProfile;", "UNAVAILABLE", "Lobservable/client/ProfileScreen$Action$ObservableStatus;", "getUNAVAILABLE", "()Lobservable/client/ProfileScreen$Action$ObservableStatus;", "NO_PERMISSIONS", "getNO_PERMISSIONS", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final NewProfile getDEFAULT() {
                return DEFAULT;
            }

            @NotNull
            public final ObservableStatus getUNAVAILABLE() {
                return UNAVAILABLE;
            }

            @NotNull
            public final ObservableStatus getNO_PERMISSIONS() {
                return NO_PERMISSIONS;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u00d6\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003H\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2={"Lobservable/client/ProfileScreen$Action$Custom;", "Lobservable/client/ProfileScreen$Action;", "text", "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "observable"})
        public static final class Custom
        extends Action {
            @NotNull
            private final String text;

            public Custom(@NotNull String text) {
                Intrinsics.checkNotNullParameter((Object)text, (String)"text");
                super(null);
                this.text = text;
            }

            @NotNull
            public final String getText() {
                return this.text;
            }

            @NotNull
            public final String component1() {
                return this.text;
            }

            @NotNull
            public final Custom copy(@NotNull String text) {
                Intrinsics.checkNotNullParameter((Object)text, (String)"text");
                return new Custom(text);
            }

            public static /* synthetic */ Custom copy$default(Custom custom, String string, int n, Object object) {
                if ((n & 1) != 0) {
                    string = custom.text;
                }
                return custom.copy(string);
            }

            @NotNull
            public String toString() {
                return "Custom(text=" + this.text + ")";
            }

            public int hashCode() {
                return this.text.hashCode();
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Custom)) {
                    return false;
                }
                Custom custom = (Custom)other;
                return Intrinsics.areEqual((Object)this.text, (Object)custom.text);
            }
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0003H\u00d6\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u00d6\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005\u00a8\u0006\u0012"}, d2={"Lobservable/client/ProfileScreen$Action$NewProfile;", "Lobservable/client/ProfileScreen$Action;", "duration", "", "<init>", "(I)V", "getDuration", "()I", "setDuration", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "observable"})
        public static final class NewProfile
        extends Action {
            private int duration;

            public NewProfile(int duration) {
                super(null);
                this.duration = duration;
            }

            public final int getDuration() {
                return this.duration;
            }

            public final void setDuration(int n) {
                this.duration = n;
            }

            public final int component1() {
                return this.duration;
            }

            @NotNull
            public final NewProfile copy(int duration) {
                return new NewProfile(duration);
            }

            public static /* synthetic */ NewProfile copy$default(NewProfile newProfile, int n, int n2, Object object) {
                if ((n2 & 1) != 0) {
                    n = newProfile.duration;
                }
                return newProfile.copy(n);
            }

            @NotNull
            public String toString() {
                return "NewProfile(duration=" + this.duration + ")";
            }

            public int hashCode() {
                return Integer.hashCode(this.duration);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof NewProfile)) {
                    return false;
                }
                NewProfile newProfile = (NewProfile)other;
                return this.duration == newProfile.duration;
            }
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u00d6\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003H\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2={"Lobservable/client/ProfileScreen$Action$ObservableStatus;", "Lobservable/client/ProfileScreen$Action;", "text", "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "observable"})
        public static final class ObservableStatus
        extends Action {
            @NotNull
            private final String text;

            public ObservableStatus(@NotNull String text) {
                Intrinsics.checkNotNullParameter((Object)text, (String)"text");
                super(null);
                this.text = text;
            }

            @NotNull
            public final String getText() {
                return this.text;
            }

            @NotNull
            public final String component1() {
                return this.text;
            }

            @NotNull
            public final ObservableStatus copy(@NotNull String text) {
                Intrinsics.checkNotNullParameter((Object)text, (String)"text");
                return new ObservableStatus(text);
            }

            public static /* synthetic */ ObservableStatus copy$default(ObservableStatus observableStatus, String string, int n, Object object) {
                if ((n & 1) != 0) {
                    string = observableStatus.text;
                }
                return observableStatus.copy(string);
            }

            @NotNull
            public String toString() {
                return "ObservableStatus(text=" + this.text + ")";
            }

            public int hashCode() {
                return this.text.hashCode();
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ObservableStatus)) {
                    return false;
                }
                ObservableStatus observableStatus = (ObservableStatus)other;
                return Intrinsics.areEqual((Object)this.text, (Object)observableStatus.text);
            }
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0083\u0004J\n\u0010\b\u001a\u00020\tH\u00d6\u0081\u0004J\n\u0010\n\u001a\u00020\u000bH\u00d6\u0081\u0004\u00a8\u0006\f"}, d2={"Lobservable/client/ProfileScreen$Action$TPSProfilerCompleted;", "Lobservable/client/ProfileScreen$Action;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "observable"})
        public static final class TPSProfilerCompleted
        extends Action {
            @NotNull
            public static final TPSProfilerCompleted INSTANCE = new TPSProfilerCompleted();

            private TPSProfilerCompleted() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "TPSProfilerCompleted";
            }

            public int hashCode() {
                return -418271182;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof TPSProfilerCompleted)) {
                    return false;
                }
                TPSProfilerCompleted cfr_ignored_0 = (TPSProfilerCompleted)other;
                return true;
            }
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u00d6\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2={"Lobservable/client/ProfileScreen$Action$TPSProfilerRunning;", "Lobservable/client/ProfileScreen$Action;", "endTime", "", "<init>", "(J)V", "getEndTime", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "observable"})
        public static final class TPSProfilerRunning
        extends Action {
            private final long endTime;

            public TPSProfilerRunning(long endTime) {
                super(null);
                this.endTime = endTime;
            }

            public final long getEndTime() {
                return this.endTime;
            }

            public final long component1() {
                return this.endTime;
            }

            @NotNull
            public final TPSProfilerRunning copy(long endTime) {
                return new TPSProfilerRunning(endTime);
            }

            public static /* synthetic */ TPSProfilerRunning copy$default(TPSProfilerRunning tPSProfilerRunning, long l, int n, Object object) {
                if ((n & 1) != 0) {
                    l = tPSProfilerRunning.endTime;
                }
                return tPSProfilerRunning.copy(l);
            }

            @NotNull
            public String toString() {
                return "TPSProfilerRunning(endTime=" + this.endTime + ")";
            }

            public int hashCode() {
                return Long.hashCode(this.endTime);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof TPSProfilerRunning)) {
                    return false;
                }
                TPSProfilerRunning tPSProfilerRunning = (TPSProfilerRunning)other;
                return this.endTime == tPSProfilerRunning.endTime;
            }
        }
    }
}

