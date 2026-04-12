/*
 * Decompiled with CFR 0.152.
 */
package observable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import observable.server.NativeTimingData;

public class Props {
    public static final AtomicBoolean notProcessing = new AtomicBoolean(true);
    public static final AtomicReference<NativeTimingData> currentTarget = new AtomicReference<Object>(null);
    public static int entityDepth = -1;
    public static int blockEntityDepth = -1;
    public static int blockDepth = -1;
    public static int fluidDepth = -1;
}

