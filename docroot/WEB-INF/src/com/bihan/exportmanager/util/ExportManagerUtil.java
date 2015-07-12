package com.bihan.exportmanager.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExportManagerUtil {

	private static final String MODEL="model";
	private static final String MODEL_PERIOD="model.";
	private static final String SERVICE="service";
	private static final String MINIMAL_S="s";
	private static final String GET="get";

	@SuppressWarnings("rawtypes")
	public static Class getLocalServiceUtilClass(ClassName className){
		
		String classNameLocalServiceUtil = className.getClassName()+"LocalServiceUtil";
		
		classNameLocalServiceUtil = classNameLocalServiceUtil.replaceAll(MODEL, SERVICE);
		
		try {
			return Class.forName(classNameLocalServiceUtil);
		} catch (ClassNotFoundException e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static Method getEntitiesMethod (ClassName className){
		
		int indexOfClassNameModel = className.getClassName().indexOf(MODEL_PERIOD)+MODEL_PERIOD.length();
		
		String methodGetClassNames = GET+className.getClassName().substring(indexOfClassNameModel)+MINIMAL_S;
			
		Class<Object> classLocalServiceUtil = getLocalServiceUtilClass(className);
		
		try {
			if(classLocalServiceUtil!=null){
				Method method = classLocalServiceUtil.getMethod(methodGetClassNames, int.class, int.class);
				
				return method;	
			}
		} catch (NoSuchMethodException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		}

		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<ClassName> getUnLocalServiceClassNames() {
		
		List<ClassName> classNames = new ArrayList<ClassName>();
		try {
			classNames = ClassNameLocalServiceUtil.getClassNames(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		List<ClassName> localServiceClassNames = new ArrayList<ClassName>();
		
		for (ClassName className : classNames) {
			
			Class localServiceUtilClass = getLocalServiceUtilClass(className);
			
			if(localServiceUtilClass!=null){
				localServiceClassNames.add(className);
			}
		}
		
		return localServiceClassNames;
	}

}
