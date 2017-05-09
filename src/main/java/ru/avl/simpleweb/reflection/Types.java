package ru.avl.simpleweb.reflection;

public enum Types {
    STRING,
    CHAR,
    BYTE,
    SHORT,
    INT,
    LONG,
    DOUBLE,
    FLOAT,
    BOOLEAN;

    public static Types getType(Class<?> clazz) {
        String typeName = clazz.getSimpleName().toUpperCase();
        return Types.valueOf(typeName);
    }
}
