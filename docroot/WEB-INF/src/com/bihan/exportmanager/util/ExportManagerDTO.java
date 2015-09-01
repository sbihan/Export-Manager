package com.bihan.exportmanager.util;

import com.bihan.exportmanager.model.ExportManager;
import com.bihan.exportmanager.model.ExportManagerField;
import com.liferay.portal.model.ClassName;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

public class ExportManagerDTO {
	

	public ExportManager exportManager;
	public List<Object> objects = new ArrayList<Object>();
	public int sizeObjects;
	public String classNameObject;
	public String primaryKey;
	public List<ExportManagerField> fields;
	public PortletURL portletURL;
	public Method methodGET;
	public ClassName className;
	
	public ClassName getClassName() {
		return className;
	}
	public void setClassName(ClassName className) {
		this.className = className;
	}
	public Method getMethodGET() {
		return methodGET;
	}
	public void setMethodGET(Method methodGET) {
		this.methodGET = methodGET;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public List<ExportManagerField> getFields() {
		return fields;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setFields(List<ExportManagerField> fields) {
		this.fields = fields;
	}
	public String getClassNameObject() {
		return classNameObject;
	}
	public void setClassNameObject(String classNameObject) {
		this.classNameObject = classNameObject;
	}
	public ExportManager getExportManager() {
		return exportManager;
	}
	public List<Object> getObjects() {
		return objects;
	}
	public void setExportManager(ExportManager exportManager) {
		this.exportManager = exportManager;
	}
	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}
	public int getSizeObjects() {
		return sizeObjects;
	}
	public PortletURL getPortletURL() {
		return portletURL;
	}
	public void setSizeObjects(int sizeObjects) {
		this.sizeObjects = sizeObjects;
	}
	public void setPortletURL(PortletURL portletURL) {
		this.portletURL = portletURL;
	}
}
