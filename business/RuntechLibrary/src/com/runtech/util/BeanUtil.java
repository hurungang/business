package com.runtech.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BeanUtil {
	
	public static Map<String, Object> toMap(Object o, Set<String> excludedProperties) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    Class<?> clazz = o.getClass();
	    Method[] methods = clazz.getDeclaredMethods();
	    for (Method m : methods) {
	        String name = m.getName();
	        boolean startsWithGet = name.startsWith("get");
	        boolean startsWithIs = name.startsWith("is");
	        if (( startsWithGet|| startsWithIs) && m.getTypeParameters().length==0) {
	            String key = name.replaceFirst(startsWithGet ? "get" : "is", "");
	            key = key.substring(0, 1).toLowerCase()+key.substring(1);
	            if (excludedProperties.contains(key)) {
	                continue;
	            }
	            Object value = null;
	            try {
	                value = m.invoke(o);
	            } catch (Exception e) {
	                throw new RuntimeException(e);
	            }
	            map.put(key, value);
	        }
	    }
	    return map;
	}

	public static Map<String, Object> toMap(Object o, String ... excludedProperties) {
	    Set<String> set = new HashSet<String>();
	    for (String s : excludedProperties) {
	        set.add(s);
	    }
	    return toMap(o, set);
	}

	public static Collection<Map<String, Object>> toMap(Collection<?> c, String ... excludedProperties) {
	    Set<String> expSet = new HashSet<String>();
	    for (String s : excludedProperties) {
	        expSet.add(s);
	    }
	    Collection<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
	    for (Object o : c) {
	        result.add(toMap(o, expSet));
	    }
	    return result;
	}
}
