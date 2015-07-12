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
 * This class is used by SOAP remote services, specifically {@link com.bihan.exportmanager.service.http.ExportManagerFieldsServiceSoap}.
 *
 * @author    sebastienbihan
 * @see       com.bihan.exportmanager.service.http.ExportManagerFieldsServiceSoap
 * @generated
 */
public class ExportManagerFieldsSoap implements Serializable {
	public static ExportManagerFieldsSoap toSoapModel(ExportManagerFields model) {
		ExportManagerFieldsSoap soapModel = new ExportManagerFieldsSoap();

		soapModel.setExportManagerFieldsId(model.getExportManagerFieldsId());
		soapModel.setExportManagerId(model.getExportManagerId());
		soapModel.setFieldName(model.getFieldName());
		soapModel.setFieldLabel(model.getFieldLabel());

		return soapModel;
	}

	public static ExportManagerFieldsSoap[] toSoapModels(
		ExportManagerFields[] models) {
		ExportManagerFieldsSoap[] soapModels = new ExportManagerFieldsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ExportManagerFieldsSoap[][] toSoapModels(
		ExportManagerFields[][] models) {
		ExportManagerFieldsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ExportManagerFieldsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ExportManagerFieldsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ExportManagerFieldsSoap[] toSoapModels(
		List<ExportManagerFields> models) {
		List<ExportManagerFieldsSoap> soapModels = new ArrayList<ExportManagerFieldsSoap>(models.size());

		for (ExportManagerFields model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ExportManagerFieldsSoap[soapModels.size()]);
	}

	public ExportManagerFieldsSoap() {
	}

	public long getPrimaryKey() {
		return _exportManagerFieldsId;
	}

	public void setPrimaryKey(long pk) {
		setExportManagerFieldsId(pk);
	}

	public long getExportManagerFieldsId() {
		return _exportManagerFieldsId;
	}

	public void setExportManagerFieldsId(long exportManagerFieldsId) {
		_exportManagerFieldsId = exportManagerFieldsId;
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

	public String getFieldLabel() {
		return _fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		_fieldLabel = fieldLabel;
	}

	private long _exportManagerFieldsId;
	private long _exportManagerId;
	private String _fieldName;
	private String _fieldLabel;
}