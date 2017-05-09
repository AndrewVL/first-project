package ru.avl.simpleweb.reflection;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

import java.lang.reflect.Field;

public class ReflectionHelper {
    private static final Logger logger = Log.getLogger(ReflectionHelper.class);

    public static Object createInstance(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            logger.warn(e);
        }
        return null;
    }

    public static void setFieldValue(Object object, String fieldName, String fieldValue) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            switch (Types.getType(field.getType())) {
                case BOOLEAN:
                    field.setBoolean(object, Boolean.valueOf(fieldValue));
                    break;
                case BYTE:
                    field.setByte(object, Byte.valueOf(fieldValue));
                    break;
                case SHORT:
                    field.setShort(object, Short.valueOf(fieldValue));
                    break;
                case INT:
                    field.setInt(object, Integer.valueOf(fieldValue));
                    break;
                case LONG:
                    field.setLong(object, Long.valueOf(fieldValue));
                    break;
                case DOUBLE:
                    field.setDouble(object, Double.valueOf(fieldValue));
                    break;
                case FLOAT:
                    field.setFloat(object, Float.valueOf(fieldValue));
                    break;
                case CHAR:
                    field.setChar(object, fieldValue.charAt(0));
                    break;
                case STRING:
                    field.set(object, fieldValue);
            }
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.warn(e);
        }
    }
}
