package com.acme.suppressed;

public final class FaultyResource implements AutoCloseable {

    private final String name;
    private final boolean failOnClose;

    public FaultyResource(String name, boolean failOnClose) {
        this.name = name;
        this.failOnClose = failOnClose;
    }

    public void businessOperation() {
        throw new IllegalStateException("Business failure in " + name);
    }

    @Override
    public void close() {
        if (failOnClose) {
            throw new RuntimeException("Cleanup failure in close() of " + name);
        }
    }
}
