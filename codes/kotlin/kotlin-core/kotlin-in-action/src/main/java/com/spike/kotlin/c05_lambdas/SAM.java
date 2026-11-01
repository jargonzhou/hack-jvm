package com.spike.kotlin.c05_lambdas;

import kotlin.Unit;

public class SAM {
    public void postponeComputation(int delay, Runnable computation) {
    }

    public static void main(String[] args) {
        // Java call Kotlin SAM
        SamsKt.consumeHello(s -> System.out.println(s.toUpperCase()));
        SamsKt.consumeHelloFunctional(s -> {
            System.out.println(s.toUpperCase());
            // explicit return Unit.INSTANCE
            return Unit.INSTANCE;
        });
    }
}
