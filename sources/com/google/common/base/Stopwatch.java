package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.concurrent.TimeUnit;

@GwtCompatible
public final class Stopwatch {
    private long elapsedNanos;
    private boolean isRunning;
    private long startTick;
    private final Ticker ticker;

    public static Stopwatch createUnstarted() {
        return new Stopwatch();
    }

    public static Stopwatch createUnstarted(Ticker ticker2) {
        return new Stopwatch(ticker2);
    }

    public static Stopwatch createStarted() {
        return new Stopwatch().start();
    }

    public static Stopwatch createStarted(Ticker ticker2) {
        return new Stopwatch(ticker2).start();
    }

    Stopwatch() {
        this.ticker = Ticker.systemTicker();
    }

    Stopwatch(Ticker ticker2) {
        this.ticker = (Ticker) Preconditions.checkNotNull(ticker2, "ticker");
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    @CanIgnoreReturnValue
    public Stopwatch start() {
        Preconditions.checkState(!this.isRunning, "This stopwatch is already running.");
        this.isRunning = true;
        this.startTick = this.ticker.read();
        return this;
    }

    @CanIgnoreReturnValue
    public Stopwatch stop() {
        long tick = this.ticker.read();
        Preconditions.checkState(this.isRunning, "This stopwatch is already stopped.");
        this.isRunning = false;
        this.elapsedNanos += tick - this.startTick;
        return this;
    }

    @CanIgnoreReturnValue
    public Stopwatch reset() {
        this.elapsedNanos = 0;
        this.isRunning = false;
        return this;
    }

    private long elapsedNanos() {
        return this.isRunning ? (this.ticker.read() - this.startTick) + this.elapsedNanos : this.elapsedNanos;
    }

    public long elapsed(TimeUnit desiredUnit) {
        return desiredUnit.convert(elapsedNanos(), TimeUnit.NANOSECONDS);
    }

    public String toString() {
        long nanos = elapsedNanos();
        TimeUnit unit = chooseUnit(nanos);
        return Platform.formatCompact4Digits(((double) nanos) / ((double) TimeUnit.NANOSECONDS.convert(1, unit))) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + abbreviate(unit);
    }

    private static TimeUnit chooseUnit(long nanos) {
        if (TimeUnit.DAYS.convert(nanos, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.DAYS;
        }
        if (TimeUnit.HOURS.convert(nanos, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.HOURS;
        }
        if (TimeUnit.MINUTES.convert(nanos, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.MINUTES;
        }
        if (TimeUnit.SECONDS.convert(nanos, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.SECONDS;
        }
        if (TimeUnit.MILLISECONDS.convert(nanos, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.MILLISECONDS;
        }
        if (TimeUnit.MICROSECONDS.convert(nanos, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.MICROSECONDS;
        }
        return TimeUnit.NANOSECONDS;
    }

    /* renamed from: com.google.common.base.Stopwatch$1 */
    static /* synthetic */ class C09801 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit = new int[TimeUnit.values().length];

        static {
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    private static String abbreviate(TimeUnit unit) {
        switch (C09801.$SwitchMap$java$util$concurrent$TimeUnit[unit.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "??s";
            case 3:
                return "ms";
            case 4:
                return "s";
            case 5:
                return "min";
            case 6:
                return "h";
            case 7:
                return "d";
            default:
                throw new AssertionError();
        }
    }
}
