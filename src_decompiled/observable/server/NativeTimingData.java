/*
 * Decompiled with CFR 0.152.
 */
package observable.server;

public class NativeTimingData {
    public long time;
    public int ticks;
    public String name = "";
    public Object traces;

    public NativeTimingData() {
        this.time = 0L;
        this.ticks = 0;
    }

    public NativeTimingData(long time, int ticks, String name, Object traces) {
        this.time = time;
        this.ticks = ticks;
        this.name = name;
        this.traces = traces;
    }
}

