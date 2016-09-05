package com.a.eye.ala.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wusheng on 16/9/5.
 */
public class ALAAgent {
    private static Set<ClassLoader> ALL_CLASS_LOADERS = new HashSet<ClassLoader>();

    private static boolean loaded = false;

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException {
                if (!loaded) {
                    boolean add = ALL_CLASS_LOADERS.add(loader);
                    if (add) {
                        try2Bootstrap(loader);
                    }
                }

                return classfileBuffer;
            }
        });
    }

    public static void try2Bootstrap(ClassLoader loader) {
        try {
            Class bootstrapClass = Class.forName("com.a.eye.ala.core.Bootstrap", true, loader);

            Method bootMethod = bootstrapClass.getDeclaredMethod("boot", int.class);

            bootMethod.invoke(null, 19780);

            loaded = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
