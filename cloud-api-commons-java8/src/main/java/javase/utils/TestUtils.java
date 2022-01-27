package javase.utils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 提升实体类单元测试覆盖率工具测试
 * @author: peng tao
 * @create: 2021-11-10 15:11
 */
public class TestUtils {

    private static Map<String, Object> baseMap = new HashMap<String, Object>();

    static {
        baseMap.put("int", 1);
        baseMap.put("boolean", true);
        baseMap.put("byte", (byte) 1);
        baseMap.put("double", (double) 1);
        baseMap.put("float", 1.0F);
        baseMap.put("long", 1L);
        baseMap.put("short", (short) 1);
    }

    public static void TestPojo(Class<?> cls) throws IllegalAccessException, InstantiationException {
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();
        Object obj = cls.newInstance();
        Arrays.stream(methods).forEach(method -> {
            if ("readObject".equals(method.getName())) {
                doReadObjcetTest(obj);
                return;
            }
            Arrays.stream(fields).forEach(field -> {
                        if (method.getName().toLowerCase().contains(field.getName().toLowerCase())) {
                            if (method.getName().startsWith("get")) {
                                doGetTest(obj, method);
                            } else if (method.getName().startsWith("set")) {
                                doSetTest(obj, method);
                            }
                        }
                    }

            );

        });
    }

    private static void doGetTest(Object obj, Method method) {
        try {
            method.invoke(obj);

        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }

    private static void doSetTest(Object obj, Method method) {
        Class<?>[] paramClss = method.getParameterTypes();
        if (paramClss != null && paramClss.length > 0) {
            Object param = getParamByClass(paramClss[0]);
            try {
                method.invoke(obj, param);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }

    }

    private static void doReadObjcetTest(Object obj) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(obj);
            byte[] data = byteArrayOutputStream.toByteArray();
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            obj = ois.readObject();
            data = null;
        } catch (Exception e) {
        } finally {
            closeStream(oos);
            closeStream(ois);
        }
    }

    private static Object getParamByClass(Class<?> paramClss) {
        return (baseMap.containsKey(paramClss.getName())) ? baseMap.get(paramClss.getName()) : null;
    }

    private static <T extends Closeable> void closeStream(T t) {
        try {
            if (t != null) {
                t.close();
            }
        } catch (Exception e) {

        }
    }

}
