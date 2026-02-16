package com.acme.suppressed;

public final class NaiveReporter {

    private NaiveReporter() {}

    public static void report(String label, Throwable t) {
        System.err.println(label + ": " + t.getClass().getSimpleName() + ": " + t.getMessage());

        Throwable cause = t.getCause();
        if (cause != null) {
            System.err.println(label + " CAUSE: " + cause.getClass().getSimpleName() + ": " + cause.getMessage());
        }

        // Intentionally ignores suppressed exceptions.
    }
}
