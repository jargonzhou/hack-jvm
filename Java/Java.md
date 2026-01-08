# Java

# Language
## IO
- Friesen, Jeff. **Java IO, NIO and NIO.2**. 2015. Apress.
  - Classic IO
    - File
    - RandomAccessFile
    - Streams
    - Writers, Readers
  - New IO
    - Buffers
    - Channels
    - Selectors
    - Regular Expressions
    - Charsets
    - Formatter
  - More New IO
    - File System Interface
    - Asynchronous IO
    - Socket Channel 
- Leonard, Anghel. **Pro Java 7 NIO.2**. 2011. Apress.
  - Path
  - File Attributes
  - Symbolic and Hard Linkes
  - Files, Directories
  - Walks
  - Random Acess Files
  - Sockets
  - Asynchronous Channel API


## Concurrent

java.lang.Thread
- volatile String name
- boolean daemon
- volatile boolean interrupted
- Runnable target
- ThreadGroup group
- ClassLoader contextClassLoader
- ThreadLocal.ThreadLocalMap threadLocals // ThreadLocal
- ThreadLocal.ThreadLocalMap inheritableThreadLocals // InheritableThreadLocal

<img src="./images/Java Thread states.png" width="800"/>

java.lang.ThreadLocal
- java.lang.ThreadLocal.ThreadLocalMap
  - Entry(ThreadLocal<?> k, Object v): extends WeakReference<ThreadLocal<?>>
  - java.lang.ThreadLocal.ThreadLocalMap#getEntry(ThreadLocal<?> key)
  - java.lang.ThreadLocal.ThreadLocalMap#set(ThreadLocal<?> key, Object value)
  - java.lang.ThreadLocal.ThreadLocalMap#remove(ThreadLocal<?> key)
- java.lang.ThreadLocal#get()
  - java.lang.Thread#currentThread#threadLocals
- java.lang.ThreadLocal#set(T value)
- java.lang.ThreadLocal#remove()

## Collections

# Cryptography
- [Bouncy Castle – Open-source cryptographic APIs](https://www.bouncycastle.org/)

Books:
- David Hook and Jon Eaves. **Java Cryptography: Tools and Techniques**. 2018

```java
// Base64
// SHA256
// example in Spring Security in Action, 2nd Edition, P.290.
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Base64;

// code验证器
SecureRandom secureRandom = new SecureRandom();
byte[] code = new byte[32];
secureRandom.nextBytes(code);
String verifier = Base64.getUrlEncoder()
  .withoutPadding()
  .encodeToString(code);
System.out.println(verifier);
// code验证器的SHA256消息摘要
MessageDigest md = MessageDigest.getInstance("SHA-256");
byte[] digest = md.digest(verifier.getBytes());
String codeChallenge = Base64.getUrlEncoder()
  .withoutPadding()
  .encodeToString(digest);
System.out.println(codeChallenge);
```

```java
import java.util.Base64;

System.out.println(Base64.getUrlEncoder()
  .withoutPadding()
  .encodeToString("client:secret".getBytes()));

System.out.println(Base64.getUrlEncoder()
  .withoutPadding()
  .encodeToString("resource-client:secret".getBytes()));

System.out.println(Base64.getUrlEncoder()
  .withoutPadding()
  .encodeToString("resource_server:resource_server_secret".getBytes()));
```

# See Also
* [Awesome Java](https://github.com/akullpp/awesome-java): A curated list of awesome frameworks, libraries and software for the Java programming language.