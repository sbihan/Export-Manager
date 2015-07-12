/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.bihan.exportmanager.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.bihan.exportmanager.service.http.ExportManagerFieldServiceSoap}.
 *
 * @author    sebastienbihan
 * @see       com.bihan.exportmanager.service.http.ExportManagerFieldServiceSoap
 * @generated
 */
public class ExportManagerFieldSoap implements Serializable {
	public static ExportManagerFieldSoap toSoapModel(ExportManagerField model) {
		ExportManagerFieldSoap soapModel = new ExportManagerFieldSoap();

		soapModel.setExportManagerFieldId(model.getExportManagerFieldId());
		soapModel.setExportManagerId(model.getExportManagerId());
		soapModel.setFieldName(model.getFieldName());
		soapModel.setFieldDisplayName(model.getFieldDisplayName());
		soapModel.setPosition(model.getPosition());

		return soapModel;
	}

	public static ExportManagerFieldSoap[] toSoapModels(
		ExportManagerField[] models) {
		ExportManagerFieldSoap[] soapModels = new ExportManagerFieldSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ExportManagerFieldSoap[][] toSoapModels(
		ExportManagerField[][] models) {
		ExportManagerFieldSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ExportManagerFieldSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ExportManagerFieldSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ExportManagerFieldSoap[] toSoapModels(
		List<ExportManagerField> models) {
		List<ExportManagerFieldSoap> soapModels = new ArrayList<ExportManagerFieldSoap>(models.size());

		for (ExportManagerField model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ExportManagerFieldSoap[soapModels.size()]);
	}

	public ExportManagerFieldSoap() {
	}

	public long getPrimaryKey() {
		return _exportManagerFieldId;
	}

	public void setPrimaryKey(long pk) {
		setExportManagerFieldId(pk);
	}

	public long getExportManagerFieldId() {
		return _exportManagerFieldId;
	}

	public void setExportManagerFieldId(long exportManagerFieldId) {
		_exportManagerFieldId = exportManagerFieldId;
	}

	public long getExportManagerId() {
		return _exportManagerId;
	}

	public void setExportManagerId(long exportManagerId) {
		_exportManagerId = exportManagerId;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public String getFieldDisplayName() {
		return _fieldDisplayName;
	}

	public void setFieldDisplayName(String fieldDisplayName) {
		_fieldDisplayName = fieldDisplayName;
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;
	}

	private long _exportManagerFieldId;
	private long _exportManagerId;
	private String _fieldName;
	private String _fieldDisplayName;
	private int _position;
}