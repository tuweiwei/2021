package com.demotuwei.demotuwei.uitl;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

/**
 * 反射相关工具类
 */
public class ReflectUtil {
    /**
     * 判断是否基础类型数据
     * @param clazz
     * @return
     */
    public static boolean isPrimitive(Class clazz){
        try {
            return ((Class<?>)clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否基础类型数据或者String类型
     * @param clazz
     * @return
     */
    public static boolean isBassClassType(Class clazz){
        try {
            return isPrimitive(clazz) || clazz.getName().equals("java.lang.String");
        } catch (Exception e) {
            return false;
        }
    }


    public static Integer getCollectSize(Object value) {
        Class collectClass = Collection.class;
        try {
            Method sizeMethod = collectClass.getDeclaredMethod("size");
            if (sizeMethod != null) {
                Object sizeObject = sizeMethod.invoke(value);
                if (sizeObject != null && sizeObject instanceof Integer) {
                    return  (int) sizeObject;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        /*SimEleRequest ele = new SimEleRequest();
        Field[] fields = ele.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName()+"-"+field.getType().getName()+":"+isPrimitive(field.getType()));

        }*/

    }

    public static boolean isGroupClassType(Class clazz) {
        return Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz) || clazz.isArray();
    }

    public static Class getListTClass(Field field){
        Type genType = field.getGenericType();
        if (ParameterizedType.class.isAssignableFrom(genType.getClass())) {
            for (Type subT: ((ParameterizedType) genType).getActualTypeArguments()) {
                try {
                    return Class.forName(subT.getTypeName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Object getFieldValue(Object object, Field field, Class clazz) {
        try {
            Method method = clazz.getDeclaredMethod(getGetMethodName(field.getName()));
            if (method != null) {
                return method.invoke(object);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
        /*Object value = null;
        try {
            value = field.get(param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;*/
    }

    public static void setFieldValue(Object object, Field field, Class clazz, Object value) {
        try {
            Method method = clazz.getDeclaredMethod(getSetMethodName(field.getName()));
            if (method != null) {
                method.invoke(object, value);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static String getGetMethodName(String fileName) {
        //拿到第二个字母
        char[] chars = fileName.toCharArray();
        if (chars.length > 1 && chars[1] >= 'A' &&  chars[1] <= 'Z') {
            return "get"+fileName;
        }
        return "get"+fileName.substring(0,1).toUpperCase(Locale.ENGLISH)+fileName.substring(1);
    }

    private static String getSetMethodName(String fileName) {
        //拿到第二个字母
        char[] chars = fileName.toCharArray();
        if (chars.length > 1 && chars[1] >= 'A' &&  chars[1] <= 'Z') {
            return "set"+fileName;
        }
        return "set"+fileName.substring(0,1).toUpperCase(Locale.ENGLISH)+fileName.substring(1);
    }

    /**
     * 根据属性名称和java类型，获取对应的getter方法名
     * @param property
     * @param javaType
     * @return
     */
    public static String getGetterMethodName(String property, String javaType) {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        if ("boolean".equals(javaType)) {
            sb.insert(0, "is");
        } else {
            sb.insert(0, "get");
        }
        return sb.toString();
    }
    /**
     * 根据属性名称获取对应的setter方法名称
     * @param property
     * @return
     */
    public static String getSetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "set");
        return sb.toString();
    }
}
