/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmClassMappingKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.reflect.KClass
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.server;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import observable.server.TraceMapKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\"B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00000\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bB\u0015\b\u0016\u0012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r\u00a2\u0006\u0004\b\n\u0010\u000eJ\u0014\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eJ\u0017\u0010\u001b\u001a\u00020\u001c2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0!H\u0086\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00000\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006#"}, d2={"Lobservable/server/TraceMap;", "", "className", "", "methodName", "children", "", "Lobservable/server/TraceMap$MapKey;", "count", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;I)V", "target", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "getClassName", "()Ljava/lang/String;", "setClassName", "(Ljava/lang/String;)V", "getMethodName", "setMethodName", "getChildren", "()Ljava/util/Map;", "getCount", "()I", "setCount", "(I)V", "add", "", "stackTrace", "", "Ljava/lang/StackTraceElement;", "traces", "", "MapKey", "observable"})
@SourceDebugExtension(value={"SMAP\nTraceMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TraceMap.kt\nobservable/server/TraceMap\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,46:1\n31#1,10:47\n41#1,4:64\n383#2,7:57\n383#2,7:68\n*S KotlinDebug\n*F\n+ 1 TraceMap.kt\nobservable/server/TraceMap\n*L\n24#1:47,10\n24#1:64,4\n24#1:57,7\n40#1:68,7\n*E\n"})
public final class TraceMap {
    @NotNull
    private String className;
    @NotNull
    private String methodName;
    @NotNull
    private final Map<MapKey, TraceMap> children;
    private int count;

    public TraceMap(@NotNull String className, @NotNull String methodName, @NotNull Map<MapKey, TraceMap> children, int count) {
        Intrinsics.checkNotNullParameter((Object)className, (String)"className");
        Intrinsics.checkNotNullParameter((Object)methodName, (String)"methodName");
        Intrinsics.checkNotNullParameter(children, (String)"children");
        this.className = className;
        this.methodName = methodName;
        this.children = children;
        this.count = count;
    }

    public /* synthetic */ TraceMap(String string, String string2, Map map, int n, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n2 & 1) != 0) {
            string = "null";
        }
        if ((n2 & 2) != 0) {
            string2 = "null";
        }
        if ((n2 & 4) != 0) {
            map = new LinkedHashMap();
        }
        if ((n2 & 8) != 0) {
            n = 0;
        }
        this(string, string2, map, n);
    }

    @NotNull
    public final String getClassName() {
        return this.className;
    }

    public final void setClassName(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.className = string;
    }

    @NotNull
    public final String getMethodName() {
        return this.methodName;
    }

    public final void setMethodName(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.methodName = string;
    }

    @NotNull
    public final Map<MapKey, TraceMap> getChildren() {
        return this.children;
    }

    public final int getCount() {
        return this.count;
    }

    public final void setCount(int n) {
        this.count = n;
    }

    public TraceMap(@NotNull KClass<?> target) {
        Intrinsics.checkNotNullParameter(target, (String)"target");
        String string = JvmClassMappingKt.getJavaClass(target).getName();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
        this(string, null, null, 0, 14, null);
    }

    /*
     * WARNING - void declaration
     */
    public final void add(@NotNull List<StackTraceElement> stackTrace) {
        Intrinsics.checkNotNullParameter(stackTrace, (String)"stackTrace");
        Iterator traces = CollectionsKt.asReversed(stackTrace).iterator();
        while (traces.hasNext()) {
            String name = ((StackTraceElement)traces.next()).getClassName();
            if (!Intrinsics.areEqual((Object)TraceMapKt.getSERVER_LEVEL_CLASS(), (Object)name)) {
                Intrinsics.checkNotNull((Object)name);
                if (!StringsKt.contains$default((CharSequence)name, (CharSequence)"NativeProfiler", (boolean)false, (int)2, null)) continue;
            }
            TraceMap traceMap = this;
            Iterator traces$iv = traces;
            boolean $i$f$add = false;
            if (traces$iv.hasNext()) {
                void this_$iv;
                this_$iv.setCount(this_$iv.getCount() + 1);
                StackTraceElement tr$iv = (StackTraceElement)traces$iv.next();
                String string = tr$iv.getClassName();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getClassName(...)");
                this_$iv.setClassName(string);
                String string2 = tr$iv.getMethodName();
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getMethodName(...)");
                this_$iv.setMethodName(string2);
                TraceMap target$iv = this_$iv;
                while (traces$iv.hasNext()) {
                    Object object;
                    void $this$getOrPut$iv$iv;
                    StackTraceElement el$iv = (StackTraceElement)traces$iv.next();
                    String string3 = el$iv.getClassName();
                    Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"getClassName(...)");
                    String string4 = el$iv.getMethodName();
                    Intrinsics.checkNotNullExpressionValue((Object)string4, (String)"getMethodName(...)");
                    MapKey key$iv = new MapKey(string3, string4);
                    Map<MapKey, TraceMap> map = target$iv.getChildren();
                    MapKey key$iv$iv = key$iv;
                    boolean $i$f$getOrPut = false;
                    Object value$iv$iv = $this$getOrPut$iv$iv.get(key$iv$iv);
                    if (value$iv$iv == null) {
                        boolean bl = false;
                        String string5 = el$iv.getClassName();
                        Intrinsics.checkNotNullExpressionValue((Object)string5, (String)"getClassName(...)");
                        String string6 = el$iv.getMethodName();
                        Intrinsics.checkNotNullExpressionValue((Object)string6, (String)"getMethodName(...)");
                        TraceMap answer$iv$iv = new TraceMap(string5, string6, null, 0, 12, null);
                        $this$getOrPut$iv$iv.put(key$iv$iv, answer$iv$iv);
                        object = answer$iv$iv;
                    } else {
                        object = value$iv$iv;
                    }
                    TraceMap traceMap$iv = (TraceMap)object;
                    traceMap$iv.setCount(traceMap$iv.getCount() + 1);
                    target$iv = traceMap$iv;
                }
            }
            return;
        }
    }

    /*
     * WARNING - void declaration
     */
    public final void add(@NotNull Iterator<StackTraceElement> traces) {
        Intrinsics.checkNotNullParameter(traces, (String)"traces");
        boolean $i$f$add = false;
        if (!traces.hasNext()) {
            return;
        }
        this.setCount(this.getCount() + 1);
        StackTraceElement tr = traces.next();
        String string = tr.getClassName();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getClassName(...)");
        this.setClassName(string);
        String string2 = tr.getMethodName();
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getMethodName(...)");
        this.setMethodName(string2);
        TraceMap target = this;
        while (traces.hasNext()) {
            Object object;
            void $this$getOrPut$iv;
            StackTraceElement el = traces.next();
            String string3 = el.getClassName();
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"getClassName(...)");
            String string4 = el.getMethodName();
            Intrinsics.checkNotNullExpressionValue((Object)string4, (String)"getMethodName(...)");
            MapKey key = new MapKey(string3, string4);
            Map<MapKey, TraceMap> map = target.getChildren();
            MapKey key$iv = key;
            boolean $i$f$getOrPut = false;
            Object value$iv = $this$getOrPut$iv.get(key$iv);
            if (value$iv == null) {
                boolean bl = false;
                String string5 = el.getClassName();
                Intrinsics.checkNotNullExpressionValue((Object)string5, (String)"getClassName(...)");
                String string6 = el.getMethodName();
                Intrinsics.checkNotNullExpressionValue((Object)string6, (String)"getMethodName(...)");
                TraceMap answer$iv = new TraceMap(string5, string6, null, 0, 12, null);
                $this$getOrPut$iv.put(key$iv, answer$iv);
                object = answer$iv;
            } else {
                object = value$iv;
            }
            TraceMap traceMap = (TraceMap)object;
            traceMap.setCount(traceMap.getCount() + 1);
            target = traceMap;
        }
    }

    public TraceMap() {
        this(null, null, null, 0, 15, null);
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u00d6\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003H\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b\u00a8\u0006\u0013"}, d2={"Lobservable/server/TraceMap$MapKey;", "", "className", "", "classMethod", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "getClassMethod", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "observable"})
    public static final class MapKey {
        @NotNull
        private final String className;
        @NotNull
        private final String classMethod;

        public MapKey(@NotNull String className, @NotNull String classMethod) {
            Intrinsics.checkNotNullParameter((Object)className, (String)"className");
            Intrinsics.checkNotNullParameter((Object)classMethod, (String)"classMethod");
            this.className = className;
            this.classMethod = classMethod;
        }

        @NotNull
        public final String getClassName() {
            return this.className;
        }

        @NotNull
        public final String getClassMethod() {
            return this.classMethod;
        }

        @NotNull
        public final String component1() {
            return this.className;
        }

        @NotNull
        public final String component2() {
            return this.classMethod;
        }

        @NotNull
        public final MapKey copy(@NotNull String className, @NotNull String classMethod) {
            Intrinsics.checkNotNullParameter((Object)className, (String)"className");
            Intrinsics.checkNotNullParameter((Object)classMethod, (String)"classMethod");
            return new MapKey(className, classMethod);
        }

        public static /* synthetic */ MapKey copy$default(MapKey mapKey, String string, String string2, int n, Object object) {
            if ((n & 1) != 0) {
                string = mapKey.className;
            }
            if ((n & 2) != 0) {
                string2 = mapKey.classMethod;
            }
            return mapKey.copy(string, string2);
        }

        @NotNull
        public String toString() {
            return "MapKey(className=" + this.className + ", classMethod=" + this.classMethod + ")";
        }

        public int hashCode() {
            int result = this.className.hashCode();
            result = result * 31 + this.classMethod.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MapKey)) {
                return false;
            }
            MapKey mapKey = (MapKey)other;
            if (!Intrinsics.areEqual((Object)this.className, (Object)mapKey.className)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.classMethod, (Object)mapKey.classMethod);
        }
    }
}

