/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.Metadata
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.InlineMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.Camera
 *  net.minecraft.client.DeltaTracker
 *  net.minecraft.client.KeyMapping
 *  net.minecraft.client.KeyMapping$Category
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphicsExtractor
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.resources.Identifier
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fStack
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 */
package observable.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;
import observable.Observable;
import observable.client.ClientBridge;
import observable.client.ClientSettings;
import observable.client.ProfilerBridge;
import observable.server.ProfilingData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0002KLB\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010(\u001a\u00020\u001dH\u0016J\b\u0010)\u001a\u00020\u0006H\u0016J\b\u0010*\u001a\u00020\u0006H\u0016J\u0012\u0010+\u001a\u00020\u001d2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-J\u0015\u0010.\u001a\u00020\u001d2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-H\u0086\bJ.\u0010/\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0018\u0001002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000205H\u0002J\u0016\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;J(\u0010<\u001a\u00020\u001d2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u0002032\u0006\u0010B\u001a\u00020CH\u0016J8\u0010D\u001a\u00020\u001d2\u0006\u00108\u001a\u0002092\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u0002012\u0006\u0010H\u001a\u0002012\u0006\u0010I\u001a\u0002012\u0006\u0010J\u001a\u000205H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00110\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020#8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%\u00a8\u0006M"}, d2={"Lobservable/client/Overlay;", "Lobservable/client/ClientBridge;", "Lobservable/client/ProfilerBridge$WorldRenderer;", "<init>", "()V", "KEY_OPEN_SETTINGS", "Lnet/minecraft/client/KeyMapping;", "getKEY_OPEN_SETTINGS", "()Lnet/minecraft/client/KeyMapping;", "KEY_TOGGLE_OVERLAY", "getKEY_TOGGLE_OVERLAY", "isOverlayEnabled", "", "()Z", "setOverlayEnabled", "(Z)V", "entities", "", "Lobservable/client/Overlay$Entry$EntityEntry;", "getEntities", "()Ljava/util/List;", "setEntities", "(Ljava/util/List;)V", "blocks", "Lobservable/client/Overlay$Entry$BlockEntry;", "blockMap", "", "Lnet/minecraft/world/level/ChunkPos;", "init", "", "DIST_FAC", "", "getDIST_FAC", "()D", "font", "Lnet/minecraft/client/gui/Font;", "getFont", "()Lnet/minecraft/client/gui/Font;", "font$delegate", "Lkotlin/Lazy;", "clear", "getSettingsKey", "getOverlayKey", "load", "lvl", "Lnet/minecraft/client/multiplayer/ClientLevel;", "loadSync", "projectToScreen", "Lkotlin/Pair;", "", "worldPos", "Lnet/minecraft/world/phys/Vec3;", "guiW", "", "guiH", "renderHud", "graphics", "Lnet/minecraft/client/gui/GuiGraphicsExtractor;", "delta", "Lnet/minecraft/client/DeltaTracker;", "render", "stack", "Lorg/joml/Matrix4fStack;", "bufferSource", "Lnet/minecraft/client/renderer/MultiBufferSource;", "camera", "modelViewMatrix", "Lorg/joml/Matrix4fc;", "drawLabel", "text", "", "x", "y", "scale", "color", "Color", "Entry", "observable"})
@SourceDebugExtension(value={"SMAP\nOverlay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Overlay.kt\nobservable/client/Overlay\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,202:1\n1586#2:203\n1661#2,3:204\n777#2:207\n873#2,2:208\n1080#2:210\n1586#2:211\n1661#2,3:212\n777#2:215\n873#2,2:216\n1512#2:218\n1538#2,3:219\n1541#2,3:229\n1915#2,2:233\n1915#2,2:235\n383#3,7:222\n1#4:232\n*S KotlinDebug\n*F\n+ 1 Overlay.kt\nobservable/client/Overlay\n*L\n86#1:203\n86#1:204,3\n88#1:207\n88#1:208,2\n89#1:210\n92#1:211\n92#1:212,3\n93#1:215\n93#1:216,2\n96#1:218\n96#1:219,3\n96#1:229,3\n147#1:233,2\n181#1:235,2\n96#1:222,7\n*E\n"})
public final class Overlay
implements ClientBridge,
ProfilerBridge.WorldRenderer {
    @NotNull
    public static final Overlay INSTANCE = new Overlay();
    @NotNull
    private static final KeyMapping KEY_OPEN_SETTINGS = new KeyMapping("key.observable.settings", 85, KeyMapping.Category.MISC);
    @NotNull
    private static final KeyMapping KEY_TOGGLE_OVERLAY = new KeyMapping("key.observable.overlay", 82, KeyMapping.Category.MISC);
    private static boolean isOverlayEnabled = true;
    @NotNull
    private static List<Entry.EntityEntry> entities = new ArrayList();
    @NotNull
    private static List<Entry.BlockEntry> blocks = CollectionsKt.emptyList();
    @NotNull
    private static Map<ChunkPos, ? extends List<Entry.BlockEntry>> blockMap = MapsKt.emptyMap();
    private static final double DIST_FAC = 1.0 / Math.pow((double)2 * Math.pow(16.0, 2), 0.5);
    @NotNull
    private static final Lazy font$delegate = LazyKt.lazy(Overlay::font_delegate$lambda$0);

    private Overlay() {
    }

    @NotNull
    public final KeyMapping getKEY_OPEN_SETTINGS() {
        return KEY_OPEN_SETTINGS;
    }

    @NotNull
    public final KeyMapping getKEY_TOGGLE_OVERLAY() {
        return KEY_TOGGLE_OVERLAY;
    }

    public final boolean isOverlayEnabled() {
        return isOverlayEnabled;
    }

    public final void setOverlayEnabled(boolean bl) {
        isOverlayEnabled = bl;
    }

    @NotNull
    public final List<Entry.EntityEntry> getEntities() {
        return entities;
    }

    public final void setEntities(@NotNull List<Entry.EntityEntry> list) {
        Intrinsics.checkNotNullParameter(list, (String)"<set-?>");
        entities = list;
    }

    public final void init() {
    }

    public final double getDIST_FAC() {
        return DIST_FAC;
    }

    @NotNull
    public final Font getFont() {
        Lazy lazy = font$delegate;
        return (Font)lazy.getValue();
    }

    @Override
    public void clear() {
        entities = CollectionsKt.emptyList();
        blocks = CollectionsKt.emptyList();
        blockMap = MapsKt.emptyMap();
        Observable.RESULTS = null;
    }

    @Override
    @NotNull
    public KeyMapping getSettingsKey() {
        return KEY_OPEN_SETTINGS;
    }

    @Override
    @NotNull
    public KeyMapping getOverlayKey() {
        return KEY_TOGGLE_OVERLAY;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    public final void load(@Nullable ClientLevel lvl) {
        void $this$groupByTo$iv$iv;
        List list;
        List list2;
        void $this$sortedByDescending$iv;
        void $this$filterTo$iv$iv;
        Iterable $this$filter$iv;
        List list3;
        List list4;
        Collection collection;
        ProfilingData profilingData = Observable.RESULTS;
        if (profilingData == null) {
            return;
        }
        ProfilingData data = profilingData;
        ClientLevel clientLevel = lvl;
        if (clientLevel == null && (clientLevel = Minecraft.getInstance().level) == null) {
            return;
        }
        ClientLevel level = clientLevel;
        Identifier identifier = level.dimension().identifier();
        Intrinsics.checkNotNullExpressionValue((Object)identifier, (String)"identifier(...)");
        Identifier levelLocation = identifier;
        int ticks = data.getTicks();
        boolean norm = ClientSettings.INSTANCE.getNormalized();
        Iterable<ProfilingData.Entry> iterable = data.getEntities().get(levelLocation);
        if (iterable != null) {
            void $this$mapTo$iv$iv;
            void $this$map$iv;
            Iterable iterable2 = iterable;
            boolean $i$f$map = false;
            void var10_13 = $this$map$iv;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map$iv, (int)10));
            boolean $i$f$mapTo = false;
            for (Object item$iv$iv : $this$mapTo$iv$iv) {
                void it;
                ProfilingData.Entry entry = (ProfilingData.Entry)item$iv$iv;
                collection = destination$iv$iv;
                boolean bl = false;
                Integer n = it.getEntityId();
                Intrinsics.checkNotNull((Object)n);
                collection.add(new Entry.EntityEntry(n, it.getRate() * (norm ? (double)it.getTicks() / (double)ticks : 1.0)));
            }
            list4 = (List)destination$iv$iv;
        } else {
            list4 = list3 = null;
        }
        if (list4 == null) {
            list3 = CollectionsKt.emptyList();
        }
        iterable = list3;
        boolean $i$f$filter = false;
        void $i$f$map = $this$filter$iv;
        Iterable destination$iv$iv = new ArrayList();
        boolean $i$f$filterTo = false;
        for (Object element$iv$iv : $this$filterTo$iv$iv) {
            Entry.EntityEntry it = (Entry.EntityEntry)element$iv$iv;
            boolean bl = false;
            if (!(it.getRate() >= (double)ClientSettings.INSTANCE.getMinRate())) continue;
            destination$iv$iv.add(element$iv$iv);
        }
        $this$filter$iv = (List)destination$iv$iv;
        boolean $i$f$sortedByDescending22 = false;
        entities = CollectionsKt.sortedWith((Iterable)$this$sortedByDescending$iv, (Comparator)new Comparator(){

            public final int compare(T a, T b) {
                Entry.EntityEntry it = (Entry.EntityEntry)b;
                boolean bl = false;
                Comparable comparable = Double.valueOf(it.getRate());
                it = (Entry.EntityEntry)a;
                Comparable comparable2 = comparable;
                bl = false;
                return ComparisonsKt.compareValues((Comparable)comparable2, (Comparable)Double.valueOf(it.getRate()));
            }
        });
        List<ProfilingData.Entry> $i$f$sortedByDescending22 = data.getBlocks().get(levelLocation);
        if ($i$f$sortedByDescending22 != null) {
            void $this$filterTo$iv$iv2;
            void $this$filter$iv2;
            Entry.BlockEntry it;
            void $this$mapTo$iv$iv;
            Iterable $this$map$iv;
            destination$iv$iv = $i$f$sortedByDescending22;
            boolean $i$f$map2 = false;
            Iterator $i$f$mapTo = $this$map$iv;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map$iv, (int)10));
            boolean $i$f$mapTo2 = false;
            for (Object item$iv$iv : $this$mapTo$iv$iv) {
                ProfilingData.Entry entry = (ProfilingData.Entry)item$iv$iv;
                collection = destination$iv$iv2;
                boolean bl = false;
                collection.add(new Entry.BlockEntry(((ProfilingData.Entry)((Object)it)).getPosition(), ((ProfilingData.Entry)((Object)it)).getRate() * (norm ? (double)((ProfilingData.Entry)((Object)it)).getTicks() / (double)ticks : 1.0)));
            }
            $this$map$iv = (List)destination$iv$iv2;
            boolean $i$f$filter2 = false;
            $this$mapTo$iv$iv = $this$filter$iv2;
            destination$iv$iv2 = new ArrayList();
            boolean $i$f$filterTo2 = false;
            for (Object element$iv$iv : $this$filterTo$iv$iv2) {
                it = (Entry.BlockEntry)element$iv$iv;
                boolean bl = false;
                if (!(it.getRate() >= (double)ClientSettings.INSTANCE.getMinRate())) continue;
                destination$iv$iv2.add(element$iv$iv);
            }
            list2 = (List)destination$iv$iv2;
        } else {
            list2 = list = null;
        }
        if (list2 == null) {
            list = CollectionsKt.emptyList();
        }
        List blks = list;
        Iterable $this$groupBy$iv = blks;
        boolean $i$f$groupBy = false;
        Iterable $i$f$filter2 = $this$groupBy$iv;
        Map destination$iv$iv3 = new LinkedHashMap();
        boolean $i$f$groupByTo = false;
        for (Object element$iv$iv : $this$groupByTo$iv$iv) {
            Object object;
            void $this$getOrPut$iv$iv$iv;
            Entry.BlockEntry it = (Entry.BlockEntry)element$iv$iv;
            boolean bl = false;
            ChunkPos key$iv$iv = ChunkPos.containing((BlockPos)it.getPos());
            Map map = destination$iv$iv3;
            ChunkPos key$iv$iv$iv = key$iv$iv;
            boolean $i$f$getOrPut = false;
            Object value$iv$iv$iv = $this$getOrPut$iv$iv$iv.get(key$iv$iv$iv);
            if (value$iv$iv$iv == null) {
                boolean bl2 = false;
                List answer$iv$iv$iv = new ArrayList();
                $this$getOrPut$iv$iv$iv.put(key$iv$iv$iv, answer$iv$iv$iv);
                object = answer$iv$iv$iv;
            } else {
                object = value$iv$iv$iv;
            }
            List list$iv$iv = (List)object;
            list$iv$iv.add(element$iv$iv);
        }
        Map newMap = destination$iv$iv3;
        Overlay overlay = this;
        synchronized (overlay) {
            boolean bl = false;
            blockMap = newMap;
            Unit unit = Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void load$default(Overlay overlay, ClientLevel clientLevel, int n, Object object) {
        if ((n & 1) != 0) {
            clientLevel = null;
        }
        overlay.load(clientLevel);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void loadSync(@Nullable ClientLevel lvl) {
        boolean $i$f$loadSync = false;
        Overlay overlay = this;
        synchronized (overlay) {
            try {
                boolean bl = false;
                INSTANCE.load(lvl);
                Unit unit = Unit.INSTANCE;
            }
            finally {
                InlineMarker.finallyStart((int)1);
                // MONITOREXIT @DISABLED, blocks:[1, 3] lbl12 : MonitorExitStatement: MONITOREXIT : var3_3
                InlineMarker.finallyEnd((int)1);
            }
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static /* synthetic */ void loadSync$default(Overlay $this, ClientLevel lvl, int n, Object object) {
        if ((n & 1) != 0) {
            lvl = null;
        }
        boolean $i$f$loadSync = false;
        object = $this;
        synchronized (object) {
            try {
                boolean bl = false;
                INSTANCE.load(lvl);
                Unit unit = Unit.INSTANCE;
            }
            finally {
                InlineMarker.finallyStart((int)1);
                // MONITOREXIT @DISABLED, blocks:[1, 3] lbl14 : MonitorExitStatement: MONITOREXIT : var3_3
                InlineMarker.finallyEnd((int)1);
            }
            return;
        }
    }

    private final Pair<Float, Float> projectToScreen(Vec3 worldPos, int guiW, int guiH) {
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        Intrinsics.checkNotNullExpressionValue((Object)camera, (String)"getMainCamera(...)");
        Camera camera2 = camera;
        Matrix4f matrix4f = camera2.getViewRotationProjectionMatrix(new Matrix4f());
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"getViewRotationProjectionMatrix(...)");
        Matrix4f mvp = matrix4f;
        Vec3 vec3 = worldPos.subtract(camera2.position());
        Intrinsics.checkNotNullExpressionValue((Object)vec3, (String)"subtract(...)");
        Vec3 offset = vec3;
        Vector3f ndc = mvp.transformProject(new Vector3f((float)offset.x, (float)offset.y, (float)offset.z));
        if (ndc.z >= 1.0f) {
            return null;
        }
        return TuplesKt.to((Object)Float.valueOf((ndc.x + 1.0f) / 2.0f * (float)guiW), (Object)Float.valueOf((1.0f - ndc.y) / 2.0f * (float)guiH));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void renderHud(@NotNull GuiGraphicsExtractor graphics, @NotNull DeltaTracker delta) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)delta, (String)"delta");
        if (!ProfilerBridge.isOverlayEnabled || Observable.RESULTS == null) {
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraft, (String)"getInstance(...)");
        Minecraft mc = minecraft;
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();
        float partialTicks = delta.getGameTimeDeltaPartialTick(true);
        Vec3 vec3 = mc.gameRenderer.getMainCamera().position();
        Intrinsics.checkNotNullExpressionValue((Object)vec3, (String)"position(...)");
        Vec3 cameraPos = vec3;
        Overlay overlay = this;
        synchronized (overlay) {
            LocalPlayer localPlayer;
            boolean bl = false;
            int distLimit = ClientSettings.INSTANCE.getMaxBlockDist();
            int maxDistSq = distLimit * distLimit;
            Iterator iterator = ((Iterable)entities).iterator();
            int n = 0;
            while (iterator.hasNext()) {
                Vec3 labelPos;
                Pair<Float, Float> screenPos;
                Vec3 pos;
                Entity entity;
                int i = n++;
                Entry.EntityEntry entry = (Entry.EntityEntry)iterator.next();
                if (i > ClientSettings.INSTANCE.getMaxEntityCount() - 1) break;
                if (entry.getRate() < 1000.0 || entry.getEntity() == null || entity.isRemoved()) continue;
                Intrinsics.checkNotNullExpressionValue((Object)entity.getPosition(partialTicks), (String)"getPosition(...)");
                double distSq = cameraPos.distanceToSqr(pos);
                if (distSq > (double)maxDistSq || (screenPos = INSTANCE.projectToScreen(labelPos = new Vec3(pos.x, pos.y + (double)entity.getBbHeight() + 0.33, pos.z), w, h)) == null) continue;
                Pair<Float, Float> pair = screenPos;
                float sx = ((Number)pair.component1()).floatValue();
                float sy = ((Number)pair.component2()).floatValue();
                float scale = RangesKt.coerceIn((float)(8.0f / (float)Math.sqrt(distSq)), (float)0.1f, (float)1.0f);
                INSTANCE.drawLabel(graphics, MathKt.roundToInt((double)(entry.getRate() / (double)1000)) + " \u03bcs/t", sx, sy, scale, entry.getColor().getHex());
            }
            if ((localPlayer = mc.player) == null || (localPlayer = localPlayer.blockPosition()) == null) {
                BlockPos blockPos = BlockPos.ZERO;
                localPlayer = blockPos;
                Intrinsics.checkNotNullExpressionValue((Object)blockPos, (String)"ZERO");
            }
            ChunkPos chunkPos = ChunkPos.containing((BlockPos)localPlayer);
            Intrinsics.checkNotNullExpressionValue((Object)chunkPos, (String)"containing(...)");
            ChunkPos cpos = chunkPos;
            int chunkLimit = RangesKt.coerceAtLeast((int)(distLimit / 16), (int)2);
            int x = cpos.x() - chunkLimit;
            int n2 = cpos.x() + chunkLimit;
            if (x <= n2) {
                while (true) {
                    int n3;
                    int z;
                    if ((z = cpos.z() - chunkLimit) <= (n3 = cpos.z() + chunkLimit)) {
                        while (true) {
                            List<Entry.BlockEntry> list = blockMap.get(new ChunkPos(x, z));
                            if (list != null) {
                                Iterable $this$forEach$iv = list;
                                boolean $i$f$forEach = false;
                                for (Object element$iv : $this$forEach$iv) {
                                    Vec3 labelPos;
                                    double dz;
                                    double dy;
                                    double dx;
                                    Entry.BlockEntry entry = (Entry.BlockEntry)element$iv;
                                    boolean bl2 = false;
                                    if (entry.getRate() < 1000.0 || (dx = (double)entry.getPos().getX() - cameraPos.x) * dx + (dy = (double)entry.getPos().getY() - cameraPos.y) * dy + (dz = (double)entry.getPos().getZ() - cameraPos.z) * dz >= (double)maxDistSq) continue;
                                    Intrinsics.checkNotNullExpressionValue((Object)Vec3.atCenterOf((Vec3i)((Vec3i)entry.getPos())), (String)"atCenterOf(...)");
                                    Pair<Float, Float> screenPos = INSTANCE.projectToScreen(labelPos, w, h);
                                    if (screenPos == null) continue;
                                    Pair<Float, Float> pair = screenPos;
                                    float sx = ((Number)pair.component1()).floatValue();
                                    float sy = ((Number)pair.component2()).floatValue();
                                    double distSq = dx * dx + dy * dy + dz * dz;
                                    float scale = RangesKt.coerceIn((float)(8.0f / (float)Math.sqrt(distSq)), (float)0.1f, (float)1.0f);
                                    INSTANCE.drawLabel(graphics, MathKt.roundToInt((double)(entry.getRate() / (double)1000)) + " \u03bcs/t", sx, sy, scale, 0xFFFFFF);
                                }
                            }
                            if (z == n3) break;
                            ++z;
                        }
                    }
                    if (x == n2) break;
                    ++x;
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void render(@NotNull Matrix4fStack stack, @NotNull MultiBufferSource bufferSource, @NotNull Vec3 camera, @NotNull Matrix4fc modelViewMatrix) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)bufferSource, (String)"bufferSource");
        Intrinsics.checkNotNullParameter((Object)camera, (String)"camera");
        Intrinsics.checkNotNullParameter((Object)modelViewMatrix, (String)"modelViewMatrix");
        if (!ProfilerBridge.isOverlayEnabled) {
            return;
        }
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) {
            return;
        }
        LocalPlayer player = localPlayer;
        if (Observable.RESULTS == null) {
            return;
        }
        List visibleEntries = new ArrayList();
        int distLimit = ClientSettings.INSTANCE.getMaxBlockDist();
        int maxDistSq = distLimit * distLimit;
        Vec3 vec3 = Minecraft.getInstance().gameRenderer.getMainCamera().position();
        Intrinsics.checkNotNullExpressionValue((Object)vec3, (String)"position(...)");
        Vec3 camPos = vec3;
        Overlay overlay = this;
        synchronized (overlay) {
            boolean bl = false;
            ChunkPos chunkPos = ChunkPos.containing((BlockPos)player.blockPosition());
            Intrinsics.checkNotNullExpressionValue((Object)chunkPos, (String)"containing(...)");
            ChunkPos cpos = chunkPos;
            int chunkLimit = RangesKt.coerceAtLeast((int)(distLimit / 16), (int)2);
            int x = cpos.x() - chunkLimit;
            int n = cpos.x() + chunkLimit;
            if (x <= n) {
                while (true) {
                    int n2;
                    int z;
                    if ((z = cpos.z() - chunkLimit) <= (n2 = cpos.z() + chunkLimit)) {
                        while (true) {
                            List<Entry.BlockEntry> list = blockMap.get(new ChunkPos(x, z));
                            if (list != null) {
                                Iterable $this$forEach$iv = list;
                                boolean $i$f$forEach = false;
                                for (Object element$iv : $this$forEach$iv) {
                                    double dz;
                                    double dy;
                                    double dx;
                                    Entry.BlockEntry entry = (Entry.BlockEntry)element$iv;
                                    boolean bl2 = false;
                                    if (entry.getRate() < 1000.0 || !((dx = (double)entry.getPos().getX() - camPos.x) * dx + (dy = (double)entry.getPos().getY() - camPos.y) * dy + (dz = (double)entry.getPos().getZ() - camPos.z) * dz < (double)maxDistSq)) continue;
                                    visibleEntries.add(new ProfilerBridge.BlockEntry(entry.getPos(), entry.getColor().getHex(), entry.getColor().getAlpha()));
                                }
                            }
                            if (z == n2) break;
                            ++z;
                        }
                    }
                    if (x == n) break;
                    ++x;
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        if (!((Collection)visibleEntries).isEmpty()) {
            ProfilerBridge.drawWorldPass(stack, bufferSource, camPos, visibleEntries);
        }
    }

    private final void drawLabel(GuiGraphicsExtractor graphics, String text, float x, float y, float scale, int color) {
        int alphaFixed = color & 0xFFFFFF | 0xFF000000;
        graphics.text(this.getFont(), text, (int)(x - (float)(this.getFont().width(text) / 2)), (int)y, alphaFixed);
    }

    private static final Font font_delegate$lambda$0() {
        Font font = Minecraft.getInstance().font;
        Intrinsics.checkNotNullExpressionValue((Object)font, (String)"font");
        return font;
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0004\b\u0007\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003H\u00d6\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fH\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0011\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0013\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\r\u00a8\u0006!"}, d2={"Lobservable/client/Overlay$Color;", "", "r", "", "g", "b", "a", "<init>", "(IIII)V", "rateMicros", "", "(D)V", "getR", "()I", "getG", "getB", "getA", "hex", "getHex", "alpha", "getAlpha", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "observable"})
    public static final class Color {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final int r;
        private final int g;
        private final int b;
        private final int a;

        public Color(int r, int g, int b, int a) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
        }

        public final int getR() {
            return this.r;
        }

        public final int getG() {
            return this.g;
        }

        public final int getB() {
            return this.b;
        }

        public final int getA() {
            return this.a;
        }

        public Color(double rateMicros) {
            this(RangesKt.coerceIn((int)MathKt.roundToInt((double)(rateMicros / 100.0 * (double)255)), (int)0, (int)255), RangesKt.coerceIn((int)MathKt.roundToInt((double)((100.0 - rateMicros) / 100.0 * (double)255)), (int)0, (int)255), 0, RangesKt.coerceIn((int)MathKt.roundToInt((double)(rateMicros / 100.0 * (double)255)), (int)20, (int)100));
        }

        public final int getHex() {
            return 0xFF000000 | this.r << 16 | this.g << 8 | this.b;
        }

        public final int getAlpha() {
            return this.a;
        }

        public final int component1() {
            return this.r;
        }

        public final int component2() {
            return this.g;
        }

        public final int component3() {
            return this.b;
        }

        public final int component4() {
            return this.a;
        }

        @NotNull
        public final Color copy(int r, int g, int b, int a) {
            return new Color(r, g, b, a);
        }

        public static /* synthetic */ Color copy$default(Color color, int n, int n2, int n3, int n4, int n5, Object object) {
            if ((n5 & 1) != 0) {
                n = color.r;
            }
            if ((n5 & 2) != 0) {
                n2 = color.g;
            }
            if ((n5 & 4) != 0) {
                n3 = color.b;
            }
            if ((n5 & 8) != 0) {
                n4 = color.a;
            }
            return color.copy(n, n2, n3, n4);
        }

        @NotNull
        public String toString() {
            return "Color(r=" + this.r + ", g=" + this.g + ", b=" + this.b + ", a=" + this.a + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.r);
            result = result * 31 + Integer.hashCode(this.g);
            result = result * 31 + Integer.hashCode(this.b);
            result = result * 31 + Integer.hashCode(this.a);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Color)) {
                return false;
            }
            Color color = (Color)other;
            if (this.r != color.r) {
                return false;
            }
            if (this.g != color.g) {
                return false;
            }
            if (this.b != color.b) {
                return false;
            }
            return this.a == color.a;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2={"Lobservable/client/Overlay$Color$Companion;", "", "<init>", "()V", "fromNanos", "Lobservable/client/Overlay$Color;", "rateNanos", "", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final Color fromNanos(double rateNanos) {
                return new Color(rateNanos / 1000.0);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\b\tB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\n\u000b\u00a8\u0006\f"}, d2={"Lobservable/client/Overlay$Entry;", "", "color", "Lobservable/client/Overlay$Color;", "<init>", "(Lobservable/client/Overlay$Color;)V", "getColor", "()Lobservable/client/Overlay$Color;", "EntityEntry", "BlockEntry", "Lobservable/client/Overlay$Entry$BlockEntry;", "Lobservable/client/Overlay$Entry$EntityEntry;", "observable"})
    public static abstract sealed class Entry {
        @NotNull
        private final Color color;

        private Entry(Color color) {
            this.color = color;
        }

        @NotNull
        public final Color getColor() {
            return this.color;
        }

        public /* synthetic */ Entry(Color color, DefaultConstructorMarker $constructor_marker) {
            this(color);
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u00d6\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2={"Lobservable/client/Overlay$Entry$BlockEntry;", "Lobservable/client/Overlay$Entry;", "pos", "Lnet/minecraft/core/BlockPos;", "rate", "", "<init>", "(Lnet/minecraft/core/BlockPos;D)V", "getPos", "()Lnet/minecraft/core/BlockPos;", "getRate", "()D", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "observable"})
        public static final class BlockEntry
        extends Entry {
            @NotNull
            private final BlockPos pos;
            private final double rate;

            public BlockEntry(@NotNull BlockPos pos, double rate) {
                Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
                super(Color.Companion.fromNanos(rate), null);
                this.pos = pos;
                this.rate = rate;
            }

            @NotNull
            public final BlockPos getPos() {
                return this.pos;
            }

            public final double getRate() {
                return this.rate;
            }

            @NotNull
            public final BlockPos component1() {
                return this.pos;
            }

            public final double component2() {
                return this.rate;
            }

            @NotNull
            public final BlockEntry copy(@NotNull BlockPos pos, double rate) {
                Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
                return new BlockEntry(pos, rate);
            }

            public static /* synthetic */ BlockEntry copy$default(BlockEntry blockEntry, BlockPos blockPos, double d, int n, Object object) {
                if ((n & 1) != 0) {
                    blockPos = blockEntry.pos;
                }
                if ((n & 2) != 0) {
                    d = blockEntry.rate;
                }
                return blockEntry.copy(blockPos, d);
            }

            @NotNull
            public String toString() {
                return "BlockEntry(pos=" + this.pos + ", rate=" + this.rate + ")";
            }

            public int hashCode() {
                int result = this.pos.hashCode();
                result = result * 31 + Double.hashCode(this.rate);
                return result;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof BlockEntry)) {
                    return false;
                }
                BlockEntry blockEntry = (BlockEntry)other;
                if (!Intrinsics.areEqual((Object)this.pos, (Object)blockEntry.pos)) {
                    return false;
                }
                return Double.compare(this.rate, blockEntry.rate) == 0;
            }
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003H\u00d6\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\t\u0018\u00010\r\u00a2\u0006\u0002\b\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2={"Lobservable/client/Overlay$Entry$EntityEntry;", "Lobservable/client/Overlay$Entry;", "entityId", "", "rate", "", "<init>", "(ID)V", "getEntityId", "()I", "getRate", "()D", "entity", "Lnet/minecraft/world/entity/Entity;", "Lorg/jspecify/annotations/Nullable;", "getEntity", "()Lnet/minecraft/world/entity/Entity;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "observable"})
        public static final class EntityEntry
        extends Entry {
            private final int entityId;
            private final double rate;

            public EntityEntry(int entityId, double rate) {
                super(Color.Companion.fromNanos(rate), null);
                this.entityId = entityId;
                this.rate = rate;
            }

            public final int getEntityId() {
                return this.entityId;
            }

            public final double getRate() {
                return this.rate;
            }

            @Nullable
            public final Entity getEntity() {
                ClientLevel clientLevel = Minecraft.getInstance().level;
                return clientLevel != null ? clientLevel.getEntity(this.entityId) : null;
            }

            public final int component1() {
                return this.entityId;
            }

            public final double component2() {
                return this.rate;
            }

            @NotNull
            public final EntityEntry copy(int entityId, double rate) {
                return new EntityEntry(entityId, rate);
            }

            public static /* synthetic */ EntityEntry copy$default(EntityEntry entityEntry, int n, double d, int n2, Object object) {
                if ((n2 & 1) != 0) {
                    n = entityEntry.entityId;
                }
                if ((n2 & 2) != 0) {
                    d = entityEntry.rate;
                }
                return entityEntry.copy(n, d);
            }

            @NotNull
            public String toString() {
                return "EntityEntry(entityId=" + this.entityId + ", rate=" + this.rate + ")";
            }

            public int hashCode() {
                int result = Integer.hashCode(this.entityId);
                result = result * 31 + Double.hashCode(this.rate);
                return result;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof EntityEntry)) {
                    return false;
                }
                EntityEntry entityEntry = (EntityEntry)other;
                if (this.entityId != entityEntry.entityId) {
                    return false;
                }
                return Double.compare(this.rate, entityEntry.rate) == 0;
            }
        }
    }
}

