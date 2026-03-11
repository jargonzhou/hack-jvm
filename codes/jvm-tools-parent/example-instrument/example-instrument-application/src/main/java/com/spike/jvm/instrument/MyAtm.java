package com.spike.jvm.instrument;

public class MyAtm {
    private static final int account = 10;

    public static void withdrawMoney(int amount) throws InterruptedException {
        Thread.sleep(2000L); //processing going on here
        System.out.println("[Application] Successful Withdrawal of [" + amount + "] units!");
    }
}
