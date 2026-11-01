package com.spike.jvm.bytebuddy.agent.app;

public class ExampleTimed {
    public void doSomething() throws InterruptedException {
        Thread.sleep(100);
    }

    public static void main(String[] args) throws InterruptedException {
        new ExampleTimed().doSomething();
    }
}
