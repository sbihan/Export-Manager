package com.bihan.exportmanager.dto;

import java.io.Serializable;
import java.util.Map;

public class EntityAttributesDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8569079950616188851L;
	
	Map<String, String> attributes;

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
