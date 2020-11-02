package org.acme.resteasy.models;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
    private static Map<Long, Class> classes = new HashMap();
    private static Map<Long, Student> students = new HashMap();

    public static Map<Long, Class> getClasses() {
        return classes;
    }

    public static Map<Long, Student> getStudents() {
        return students;
    }
}
