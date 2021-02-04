package com.rediron.platform.domain.todo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// For testing only
public class ReflectUtil {

    public static String getStrippedFieldName(String sourceFieldName) {
        String[] split = sourceFieldName.toLowerCase().split("_");
        StringBuffer sb = new StringBuffer(split[0]);
        for (int i=1; i<split.length; i++) { 
            sb.append(split[i].toUpperCase().charAt(0)+split[i].substring(1));
        }
        return sb.toString();
    }
    
    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get") && !method.getName().startsWith("is")) {
            return false;
        } else if (method.getName().equals("getClass")) {
            return false;
        } else if (method.getParameterTypes().length != 0) {
            return false;
        } else if (void.class.equals(method.getReturnType())) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        } else if (method.getParameterTypes().length != 1) {
            return false;
        } else {
            return true;
        }
    }

    public static String getFieldNameFromGetterOrSetter(Method method, Class<?> pojoClass) {
        String fieldNamePastelCase;
        if (isGetter(method) && method.getName().startsWith("is")) {
            fieldNamePastelCase = method.getName().substring(2);
        } else {
            fieldNamePastelCase = method.getName().substring(3);
        }
        return pastelCaseToCamelCase(fieldNamePastelCase);
    }

    public static Method getSetterFromGetter(Method method, Class<?>[] parameterTypes, Class<?> pojoClass) throws SecurityException, NoSuchMethodException {
        String fieldNamePastelCase;
        if (method.getName().startsWith("is")) {
            fieldNamePastelCase = method.getName().substring(2);
        } else {
            fieldNamePastelCase = method.getName().substring(3);
        }
        return pojoClass.getMethod("set" + fieldNamePastelCase, parameterTypes);
    }

    public static Method getGetterFromFieldName(String fieldName, Class<?> pojoClass) throws SecurityException, NoSuchMethodException {        
        String getterNameAsDefault = "get" + ReflectUtil.camelCaseToPastelCase(fieldName);
        String getterNameAsBoolean = "is" + ReflectUtil.camelCaseToPastelCase(fieldName);
        Method getter = null;
        try {
            getter = pojoClass.getMethod(getterNameAsDefault, (Class[]) null);
        } catch (NoSuchMethodException e) {
            try {
                getter = pojoClass.getMethod(getterNameAsBoolean, (Class[]) null);
            } catch (NoSuchMethodException e1) {
            }
        }
        return getter;
    }

    public static Method getSetterFromFieldName(String fieldName, Class<?> pojoClass) throws SecurityException, NoSuchMethodException {
        Method getter = getGetterFromFieldName(fieldName, pojoClass);
        Class<?> getterReturnType = getter.getReturnType();
        return getSetterFromGetter(getter, new Class<?>[] { getterReturnType }, pojoClass);
    }

    public static String pastelCaseToCamelCase(String sPastel) {
        char firstChar = sPastel.charAt(0);
        return String.valueOf(Character.toLowerCase(firstChar)) + sPastel.substring(1);
    }

    public static String camelCaseToPastelCase(String sCamel) {
        char firstChar = sCamel.charAt(0);
        return String.valueOf(Character.toUpperCase(firstChar)) + sCamel.substring(1);
    }
    
    /**
     * Obtains all fields (including for superclasses) for a given {@code Class}
     * 
     * @param clazz
     * @return
     * @since EI Commons 1.9
     * @see Class#getDeclaredFields()
     */
    public static Field[] getAllFields(Class<?> clazz) throws SecurityException {
    	Set<String> fieldNames = new HashSet<String>();
    	List<Field> allFields = new ArrayList<Field>();
    	for (;clazz != null; clazz = clazz.getSuperclass()) {
    		Field[] fields = clazz.getDeclaredFields();
    		for(Field field : fields) {
    			String name = field.getName();
    			if (! fieldNames.contains(name)) {
    				fieldNames.add(name);
    				allFields.add(field);
    			}
    		}
    	}
    	return allFields.toArray(new Field[allFields.size()]);
    }

}
