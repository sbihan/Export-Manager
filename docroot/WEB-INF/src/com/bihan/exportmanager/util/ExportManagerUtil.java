package com.bihan.exportmanager.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExportManagerUtil {

	private static final String MODEL="model";
	private static final String MODEL_PERIOD="model.";
	private static final String SERVICE="service";
	private static final String MINIMAL_S="s";
	private static final String MINIMAL_Y="y";
	private static final String MINIMAL_ES="es";
	private static final String MINIMAL_CH="ch";
	private static final String MINIMAL_SH="sh";
	private static final String MINIMAL_IES="ies";
	
	private static final String GET="get";
	private static final String COUNT="Count";

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
	
	public static Method getEntitiesMethod (ClassName className){
				
		String classNameValue = getPluralWord(className.getClassName());
		
		int indexOfClassNameModel = classNameValue.indexOf(MODEL_PERIOD)+MODEL_PERIOD.length();
		
		String methodGetClassNames = GET+classNameValue.substring(indexOfClassNameModel);
		
		return getEntitiesMethodWithMethodGetClassNames(className, indexOfClassNameModel, methodGetClassNames);
		
	}
	
	private static String getPluralWord(String classNameValue) {
		
		if(StringUtil.endsWith(classNameValue, MINIMAL_S) || StringUtil.endsWith(classNameValue, MINIMAL_CH) || StringUtil.endsWith(classNameValue, MINIMAL_SH)){
			return classNameValue+MINIMAL_ES;
		} else if(StringUtil.endsWith(classNameValue, MINIMAL_Y)){
			classNameValue = classNameValue.substring(0,classNameValue.length() - 1);
			return classNameValue+MINIMAL_IES;
		} else{
			return classNameValue+MINIMAL_S;
		}
		
	}

	@SuppressWarnings("unchecked")
	private static Method getEntitiesMethodWithMethodGetClassNames(ClassName className, int indexOfClassNameModel,
			String methodGetClassNames){
		
		Class<Object> classLocalServiceUtil = getLocalServiceUtilClass(className);
		
		try {
			if(classLocalServiceUtil!=null){
				Method method = classLocalServiceUtil.getMethod(methodGetClassNames, int.class, int.class);
				
				return method;	
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}
	
	public static Method getEntitiesMethodCount (ClassName className){
		
		String classNameValue = getPluralWord(className.getClassName());

		int indexOfClassNameModel = classNameValue.indexOf(MODEL_PERIOD)+MODEL_PERIOD.length();
		
		String methodGetClassNamesCount = GET+classNameValue.substring(indexOfClassNameModel)+COUNT;
			
		return getEntitiesMethodWithMethodGetClassNamesCount(className, indexOfClassNameModel, methodGetClassNamesCount);
	
	}
	
	@SuppressWarnings("unchecked")
	private static Method getEntitiesMethodWithMethodGetClassNamesCount(ClassName className, int indexOfClassNameModel,
			String methodGetClassNamesCount){
		
		Class<Object> classLocalServiceUtil = getLocalServiceUtilClass(className);
		
		try {
			if(classLocalServiceUtil!=null){
				Method method = classLocalServiceUtil.getMethod(methodGetClassNamesCount);
				return method;	
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
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
		
		Collections.sort(localServiceClassNames, new ClassNameComparator());
		
		return localServiceClassNames;
	}
	
	public static List<Object> getEntities(String classNameValue, int startSearchContainer, int endSearchContainer) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SystemException{
		
		ClassName className = ClassNameLocalServiceUtil.getClassName(classNameValue);
		
		Method methodGET = ExportManagerUtil.getEntitiesMethod(className);
		
		@SuppressWarnings("unchecked")
		List<Object> listObject = (List<Object>) methodGET.invoke(null, startSearchContainer, endSearchContainer);
		
		return listObject;
	}

}
