# jvm-tools-parent

# example-asm

- core: `ASMCoreTest`
- tree: `ASMTreeTest`

# example-instrument
* https://docs.oracle.com/en/java/javase/17/docs/api/java.instrument/java/lang/instrument/package-summary.html

## example-instrument-agent

- `MyInstrumentAgent`: static load `premain`, dynamic load `agentmain`.

## example-instrument-application

static load:
```shell
$ java -javaagent:../example-instrument-agent/target/example-instrument-agent-1.0-SNAPSHOT.jar \
  -jar target/example-instrument-application-1.0-SNAPSHOT.jar \
  StartMyAtmApplication 3000 1 2
[Agent] in premain method
[Agent] Transforming class MyAtm
[Application] Starting ATM application
[Application] Successful Withdrawal of [1] units!
[Application] Withdrawal operation completed in:2 seconds!
[Application] Successful Withdrawal of [2] units!
[Application] Withdrawal operation completed in:2 seconds!
```

dynamic load: `AgentLoader`
```shell
# console 1
$ java -jar target/example-instrument-application-1.0-SNAPSHOT.jar \
  StartMyAtmApplication 3000 1 2
[Application] Starting ATM application
[Application] Successful Withdrawal of [1] units!
[Agent] in agentmain method
[Agent] Transforming class MyAtm
[Application] Successful Withdrawal of [2] units!
[Application] Withdrawal operation completed in:2 seconds!

# console 2
$ java -jar target/example-instrument-application-1.0-SNAPSHOT.jar LoadAgent
jvm: target/example-instrument-application-1.0-SNAPSHOT.jar StartMyAtmApplication 3000 1 2
Attaching to target JVM with PID: 22720
Attached to target JVM and loaded Java agent successfully
```

# example-javassist

TODO
```java
/**
 * Example from <a href="https://www.baeldung.com/javassist">Introduction to Javassist</a>
 */
```