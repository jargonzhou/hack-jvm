# Java SE
* https://docs.oracle.com/javase/8/docs/index.html
* https://docs.oracle.com/en/java/javase/21/index.html

# Java Language/Java语言

# Tools & Tool APIs/工具和工具API
* https://docs.oracle.com/en/java/javase/21/docs/specs/man/index.html

```shell
# 查看进程下线程数量
cat /proc/45132/status | grep Threads
# watch -n1 "cat /proc/45132/status | grep Threads"

# 进程内线程: 45132
ps -Lfp 45132
top -Hp 45132
# 转为16进制: b04c
printf "%x\\n" 45132

# 查看系统启动参数
jinfo -flags 45132

# 查看线程方法堆栈
jstack 45132 | grep b04c

# 查看进程堆情况
jmap -heap 45132
```

## java: Java runtime launcher

- Standard Options
- Non-Standard Options
- Advanced Runtime Options
- Advanced JIT Compiler Options
- Advanced Serviceability Options
- Advanced Garbage Collection Options

## javac: Java compiler
## javadoc: Java documentation generator
## jar: Java archive tool
## javap: Java class file disassembler



## jdeps: Java class dependency analyzer

# Deployment/部署

# User Interface Toolkits/用户界面工具集

# Integration Libraries/集成库

# Other Base Libraries/其他基础库

# lang and util Base Libraries/语言和工具基础库

# Java Virtual Machine/Java虚拟机