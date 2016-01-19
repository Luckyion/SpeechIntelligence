package me.videa.utils;

import java.lang.reflect.Field;

public class ReflectUtil {
	
	/**
	* 得到某个类的静态属性 java反射机制
	*
	* @param className
	* @param fieldName
	* @return
	* @throws Exception
	*/
	public static Object getStaticProperty(String className, String fieldName)
	        throws Exception {
	    Class ownerClass = Class.forName(className);

	    Field field = ownerClass.getField(fieldName);

	    Object property = field.get(ownerClass);

	    return property;
	}

}
