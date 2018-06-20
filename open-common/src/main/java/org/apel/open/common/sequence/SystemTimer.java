package org.apel.open.common.sequence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author shiyu.long
 */
public class SystemTimer {
    private final long period;

    private final AtomicLong now;

    private SystemTimer(long period) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        updatingScheduledTimer();
    }

    private static SystemTimer instance() {
        return InstanceHolder.INSTANCE;
    }

    public static long now() {
        return instance().currentTimeMillis();
    }

    private void updatingScheduledTimer() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "system scheduled timer");
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), period, period, TimeUnit.MILLISECONDS);
    }

    private long currentTimeMillis() {
        return now.get();
    }

    private static class InstanceHolder {
        public static final SystemTimer INSTANCE = new SystemTimer(1);
    }
}
