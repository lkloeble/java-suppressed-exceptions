package com.acme.suppressed;

public final class SuppressedAwareReporter {

    private SuppressedAwareReporter() {}

    public static void report(String label, Throwable t) {
        NaiveReporter.report(label, t);

        Throwable[] suppressed = t.getSuppressed();
        if (suppressed.length > 0) {
            for (Throwable s : suppressed) {
                System.err.println(label + " SUPPRESSED: " + s.getClass().getSimpleName() + ": " + s.getMessage());
            }
        }
    }
}
