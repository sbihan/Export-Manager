package com.bihan.exportmanager.util;

import com.liferay.portal.model.ClassName;

import java.util.Comparator;

public class ClassNameComparator implements Comparator<ClassName> {

	@Override
	public int compare(ClassName className1, ClassName className2) {
		
		
		String className1End = getEndClassName(className1.getClassName());
		String className2End = getEndClassName(className2.getClassName());
		
		return className1End.compareTo(className2End);
	}
	
	private String getEndClassName(String className){
		
		String classNamePeriod[] = className.split("\\.");
		
		return classNamePeriod[classNamePeriod.length-1];
	}

}
