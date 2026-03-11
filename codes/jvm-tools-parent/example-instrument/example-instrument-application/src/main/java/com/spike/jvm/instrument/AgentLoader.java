package com.spike.jvm.instrument;

import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.util.Optional;

public class AgentLoader {
    public static void run(String[] args) {
        String agentFilePath = "D:\\workspace\\github\\hack-jvm\\codes\\jvm-tools-parent\\example-instrument\\example-instrument-agent\\target\\example-instrument-agent-1.0-SNAPSHOT.jar";
        String applicationName = "MyAtmApplication";

        //iterate all jvms and get the first one that matches our application name
        Optional<String> jvmProcessOpt = Optional.ofNullable(VirtualMachine.list()
                .stream()
                .filter(jvm -> {
                    if (jvm.displayName().contains(applicationName)) {
                        System.out.println("jvm: " + jvm.displayName());
                    }
                    return jvm.displayName().contains(applicationName);
                })
                .findFirst().get().id());

        if (!jvmProcessOpt.isPresent()) {
            System.err.println("Target Application not found");
            return;
        }
        File agentFile = new File(agentFilePath);
        try {
            String jvmPid = jvmProcessOpt.get();
            System.out.println("Attaching to target JVM with PID: " + jvmPid);
            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
            jvm.loadAgent(agentFile.getAbsolutePath());
            jvm.detach();
            System.out.println("Attached to target JVM and loaded Java agent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
