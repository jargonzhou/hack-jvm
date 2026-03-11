package com.spike.jvm.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Callable;

/**
 * A base test class that captures {@link System#out} output for testing purposes.
 */
public class OutputTestBase {
    protected final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    protected PrintStream testOut;
    protected final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        testOut = new PrintStream(baos);
        System.setOut(testOut);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.out.println("Captured output:\n" + getOutput());
    }

    /**
     * @return captured output
     */
    public String getOutput() {
        return baos.toString();
    }

    /**
     * Output to original {@link System#out}
     *
     * @param callable work
     * @param <T>      type of result
     * @return result of work
     */
    public <T> T output(Callable<T> callable) throws Exception {
        try {
            System.setOut(originalOut);
            return callable.call();
        } finally {
            System.setOut(testOut);
        }
    }

    /**
     * Output to original {@link System#out}
     *
     * @param runnable work
     */
    public void output(Runnable runnable) {
        try {
            System.setOut(originalOut);
            runnable.run();
        } finally {
            System.setOut(testOut);
        }
    }
}
