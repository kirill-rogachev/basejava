package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import jdk.jfr.Description;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("uuid");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new uuid");
        System.out.println(r);

        // invoke r.toString via reflection
        Method toStr = r.getClass().getMethod("toString");
        System.out.println(toStr.invoke(r));
    }
}
