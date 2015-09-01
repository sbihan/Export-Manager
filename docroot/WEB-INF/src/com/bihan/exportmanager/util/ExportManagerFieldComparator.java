package com.bihan.exportmanager.util;

import com.bihan.exportmanager.model.ExportManagerField;

import java.util.Comparator;

public class ExportManagerFieldComparator implements Comparator<ExportManagerField> {

	@Override
	public int compare(ExportManagerField exportManagerField1, ExportManagerField exportManagerField2) {
		

		return ((Integer) exportManagerField1.getPosition()).compareTo(exportManagerField2.getPosition()); 
  
	}
	
	
	

}
