package com.bihan.exportmanager.util;

import com.bihan.exportmanager.model.ExportManager;

import java.util.ArrayList;
import java.util.List;

public class ExportManagerDTO {
	

	public ExportManager exportManager;
	public List<Object> objects = new ArrayList<Object>();
	public String classNameObject;
	public String primaryKey;
	public List<String> fields;
	
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setFields(List<String> fields) {
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
	
}
