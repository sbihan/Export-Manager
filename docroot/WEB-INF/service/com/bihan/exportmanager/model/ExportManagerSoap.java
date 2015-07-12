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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.bihan.exportmanager.service.http.ExportManagerServiceSoap}.
 *
 * @author    sebastienbihan
 * @see       com.bihan.exportmanager.service.http.ExportManagerServiceSoap
 * @generated
 */
public class ExportManagerSoap implements Serializable {
	public static ExportManagerSoap toSoapModel(ExportManager model) {
		ExportManagerSoap soapModel = new ExportManagerSoap();

		soapModel.setExportManagerId(model.getExportManagerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassNameValue(model.getClassNameValue());
		soapModel.setScope(model.getScope());

		return soapModel;
	}

	public static ExportManagerSoap[] toSoapModels(ExportManager[] models) {
		ExportManagerSoap[] soapModels = new ExportManagerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ExportManagerSoap[][] toSoapModels(ExportManager[][] models) {
		ExportManagerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ExportManagerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ExportManagerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ExportManagerSoap[] toSoapModels(List<ExportManager> models) {
		List<ExportManagerSoap> soapModels = new ArrayList<ExportManagerSoap>(models.size());

		for (ExportManager model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ExportManagerSoap[soapModels.size()]);
	}

	public ExportManagerSoap() {
	}

	public long getPrimaryKey() {
		return _exportManagerId;
	}

	public void setPrimaryKey(long pk) {
		setExportManagerId(pk);
	}

	public long getExportManagerId() {
		return _exportManagerId;
	}

	public void setExportManagerId(long exportManagerId) {
		_exportManagerId = exportManagerId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public String getClassNameValue() {
		return _classNameValue;
	}

	public void setClassNameValue(String classNameValue) {
		_classNameValue = classNameValue;
	}

	public String getScope() {
		return _scope;
	}

	public void setScope(String scope) {
		_scope = scope;
	}

	private long _exportManagerId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private long _classNameId;
	private String _classNameValue;
	private String _scope;
}