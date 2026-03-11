package com.spike.jvm.instrument;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args[0].equals("StartMyAtmApplication")) {
            MyAtmApplication.run(args);
        } else if (args[0].equals("LoadAgent")) {
            AgentLoader.run(args);
        }
    }
}
