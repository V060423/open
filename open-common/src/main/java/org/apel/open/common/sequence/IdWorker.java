package org.apel.open.common.sequence;


public abstract class IdWorker {

    private static final Sequence worker = new Sequence();

    public static long getId() {
        return worker.nextId();
    }
}
