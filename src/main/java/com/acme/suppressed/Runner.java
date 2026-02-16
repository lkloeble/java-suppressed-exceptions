package com.acme.suppressed;

public final class Runner {

    public static void main(String[] args) {
        run("try/finally (naive) — cleanup overwrites business failure", Runner::tryFinallyNaive);
        run("try-with-resources — business failure preserved, cleanup failure suppressed", Runner::tryWithResources);
    }

    private static void tryFinallyNaive() {
        FaultyResource r = new FaultyResource("FINALLY", true);
        try {
            r.businessOperation(); // Exception A
        } finally {
            r.close(); // Exception B overwrites A
        }
    }

    private static void tryWithResources() {
        try (FaultyResource r = new FaultyResource("TWR", true)) {
            r.businessOperation(); // Exception A
        } // close() throws Exception B (suppressed)
    }

    private static void run(String name, Runnable scenario) {
        try {
            scenario.run();
        } catch (Throwable t) {
            System.err.println("\n=== " + name + " ===");

            // Production-ish: one-line reporting, often used in error payloads / structured logs.
            NaiveReporter.report("NAIVE", t);

            // Suppressed-aware reporting: explicitly exposes secondary failures.
            SuppressedAwareReporter.report("AWARE", t);

            // Raw JVM view.
            System.err.println("\n-- printStackTrace() --");
            t.printStackTrace(System.err);
        }

    }
}
