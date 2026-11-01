package com.spike.jvm.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

// https://github.com/raphw/byte-buddy
public class TestByteBuddy {
    @Test
    public void testHelloWorld() throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        try (DynamicType.Unloaded<Object> unloaded = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()) {

            Class<?> dynamicType = unloaded.load(getClass().getClassLoader())
                    .getLoaded();
            System.out.println(dynamicType.toString());
            Assertions.assertThat(dynamicType.getDeclaredConstructor().newInstance().toString())
                    .isEqualTo("Hello World!");
        }
    }

    public class GreetingInterceptor {
        public Object greet(Object argument) {
            return "Hello from " + argument;
        }
    }


    // ???
    public class GeneralInterceptor {
        @RuntimeType
        public Object intercept(@AllArguments Object[] allArguments,
                                @Origin Method method) {
            // intercept any method of any signature
            return "Intercepted " + method.getName() + " with arguments " + java.util.Arrays.toString(allArguments);
        }
    }

    @Test
    public void TestAMoreComplexExample() throws Exception {
        Class<? extends java.util.function.Function> dynamicType = new ByteBuddy()
                .subclass(java.util.function.Function.class)
                .method(ElementMatchers.named("apply"))
                .intercept(MethodDelegation.to(new GreetingInterceptor()))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        System.out.println(dynamicType.toString());
        Assertions.assertThat((String) dynamicType.getDeclaredConstructor().newInstance().apply("Byte Buddy"))
                .isEqualTo("Hello from Byte Buddy");
    }





    @Test
    public void testChangingExistingClasses() {

    }

}