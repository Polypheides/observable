/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.random.Random
 *  org.jetbrains.annotations.NotNull
 */
package observable.server;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import observable.Observable;
import observable.Props;
import observable.server.NativeTimingData;
import observable.server.ServerSettingsKt;
import observable.server.TraceMap;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2={"Lobservable/server/TaggedSampler;", "Ljava/lang/Runnable;", "thread", "Ljava/lang/Thread;", "<init>", "(Ljava/lang/Thread;)V", "getThread", "()Ljava/lang/Thread;", "run", "", "observable"})
public final class TaggedSampler
implements Runnable {
    @NotNull
    private final Thread thread;

    public TaggedSampler(@NotNull Thread thread) {
        Intrinsics.checkNotNullParameter((Object)thread, (String)"thread");
        this.thread = thread;
    }

    @NotNull
    public final Thread getThread() {
        return this.thread;
    }

    @Override
    public void run() {
        Observable.INSTANCE.getLOGGER().info("Started sampler thread");
        long interval = ServerSettingsKt.getServerSettings().getTraceInterval();
        long deviation = ServerSettingsKt.getServerSettings().getDeviation();
        Object[] trace = null;
        NativeTimingData target = null;
        if (interval > 0L) {
            while (!Props.notProcessing.get()) {
                if (Props.currentTarget.get() == null) continue;
                Intrinsics.checkNotNullExpressionValue((Object)this.thread.getStackTrace(), (String)"getStackTrace(...)");
                Object object = target.traces;
                Intrinsics.checkNotNull((Object)object, (String)"null cannot be cast to non-null type observable.server.TraceMap");
                ((TraceMap)object).add(ArraysKt.toList((Object[])trace));
                Thread.sleep(interval + Random.Default.nextLong(-deviation, deviation));
            }
        } else {
            while (!Props.notProcessing.get()) {
                if (Props.currentTarget.get() == null) continue;
                Intrinsics.checkNotNullExpressionValue((Object)this.thread.getStackTrace(), (String)"getStackTrace(...)");
                Object object = target.traces;
                Intrinsics.checkNotNull((Object)object, (String)"null cannot be cast to non-null type observable.server.TraceMap");
                ((TraceMap)object).add(ArraysKt.toList((Object[])trace));
            }
        }
    }
}

