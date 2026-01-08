package com.spike.language.kotlin.core.demo;

public class HelloWorld {
    public static void main(String[] args) {
        Greeter greeter = new Greeter("hello");

        // initialize a class defined by Kotlin
        KotlinGreetingJoiner joiner = new KotlinGreetingJoiner(greeter);
        joiner.addName("Harry");
        joiner.addName("Ron");
        joiner.addName(null);
        joiner.addName("Hermione");
        System.out.println(joiner.getJoinedGreeting());
    }
}
