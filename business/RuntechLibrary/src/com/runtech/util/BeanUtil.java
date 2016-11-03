package com.runtech.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.runtech.web.action.PageAction;
import com.runtech.web.form.ModelJson;

public class BeanUtil {

	protected final static Logger exceptionLogger=Logger.getLogger(BeanUtil.class);
	private static final String MODEL_PACKAGE = "com.runtech.onlineshop.model";
	private static final String JSON_SUFFIX = "Json";
	private static final String JSON_PACKAGE = "com.runtech.onlineshop.json.";

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
	                if(value!=null){
			            if(value.getClass().getPackage().getName().equals(MODEL_PACKAGE)){
			            	value = convertModelToMap(value);
			            }else if(value instanceof Set){
			            	Set valueSet = (Set) value;
			            	Set newSet = new HashSet();
			            	for (Iterator iterator = valueSet.iterator(); iterator.hasNext();) {
								Object object = (Object) iterator.next();
								object = convertModelToMap(object);
								newSet.add(object);
							}
			            	value = newSet;
			            }
	                }
	            } catch (Exception e) {
	                throw new RuntimeException(e);
	            }
	            map.put(key, value);
	        }
	    }
	    return map;
	}

	public static Object convertModelToMap(Object value) {
		String simpleName = value.getClass().getSimpleName();
		try {
			Class<ModelJson> forName = (Class<ModelJson>) Class.forName(JSON_PACKAGE+simpleName+JSON_SUFFIX);
			Constructor<ModelJson> constructor = forName.getConstructor(Object.class);
			ModelJson newValue = constructor.newInstance(value);
			value = newValue.toMap();
		} catch (Exception e) {
			exceptionLogger.debug(e);
		}
		return value;
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
