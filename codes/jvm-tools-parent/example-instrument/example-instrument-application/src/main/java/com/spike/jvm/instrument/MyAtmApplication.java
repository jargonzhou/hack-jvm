package com.spike.jvm.instrument;

public class MyAtmApplication {
    // args example: 600000 1 2
    public static void run(String[] args) throws Exception {
        System.out.println("[Application] Starting ATM application");
        MyAtm.withdrawMoney(Integer.parseInt(args[2]));

        Thread.sleep(Long.parseLong(args[1]));

        MyAtm.withdrawMoney(Integer.parseInt(args[3]));
    }
}
