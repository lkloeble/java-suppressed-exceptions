# Suppressed Exceptions — A Minimal Demonstration

This repository contains a minimal Java program that demonstrates how
diagnostic information can be silently lost when multiple exceptions occur.

The focus is not syntax.
It is failure semantics.

## What the code demonstrates

A common production scenario:

- an exception is thrown in business logic
- a second exception is thrown during resource cleanup

The code compares exception handling strategies and reporting paths to show:

- when suppressed exceptions are preserved
- when they are lost
- when they exist but are not observed

No logging framework.
No abstraction.
Just JVM behavior.

## How to run

```bash
mvn clean package
java -jar target/java-suppressed-exceptions.jar
