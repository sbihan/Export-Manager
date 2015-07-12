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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ExportManagerFields}.
 * </p>
 *
 * @author    sebastienbihan
 * @see       ExportManagerFields
 * @generated
 */
public class ExportManagerFieldsWrapper implements ExportManagerFields,
	ModelWrapper<ExportManagerFields> {
	public ExportManagerFieldsWrapper(ExportManagerFields exportManagerFields) {
		_exportManagerFields = exportManagerFields;
	}

	public Class<?> getModelClass() {
		return ExportManagerFields.class;
	}

	public String getModelClassName() {
		return ExportManagerFields.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("exportManagerFieldsId", getExportManagerFieldsId());
		attributes.put("exportManagerId", getExportManagerId());
		attributes.put("fieldName", getFieldName());
		attributes.put("fieldLabel", getFieldLabel());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long exportManagerFieldsId = (Long)attributes.get(
				"exportManagerFieldsId");

		if (exportManagerFieldsId != null) {
			setExportManagerFieldsId(exportManagerFieldsId);
		}

		Long exportManagerId = (Long)attributes.get("exportManagerId");

		if (exportManagerId != null) {
			setExportManagerId(exportManagerId);
		}

		String fieldName = (String)attributes.get("fieldName");

		if (fieldName != null) {
			setFieldName(fieldName);
		}

		String fieldLabel = (String)attributes.get("fieldLabel");

		if (fieldLabel != null) {
			setFieldLabel(fieldLabel);
		}
	}

	/**
	* Returns the primary key of this export manager fields.
	*
	* @return the primary key of this export manager fields
	*/
	public long getPrimaryKey() {
		return _exportManagerFields.getPrimaryKey();
	}

	/**
	* Sets the primary key of this export manager fields.
	*
	* @param primaryKey the primary key of this export manager fields
	*/
	public void setPrimaryKey(long primaryKey) {
		_exportManagerFields.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the export manager fields ID of this export manager fields.
	*
	* @return the export manager fields ID of this export manager fields
	*/
	public long getExportManagerFieldsId() {
		return _exportManagerFields.getExportManagerFieldsId();
	}

	/**
	* Sets the export manager fields ID of this export manager fields.
	*
	* @param exportManagerFieldsId the export manager fields ID of this export manager fields
	*/
	public void setExportManagerFieldsId(long exportManagerFieldsId) {
		_exportManagerFields.setExportManagerFieldsId(exportManagerFieldsId);
	}

	/**
	* Returns the export manager ID of this export manager fields.
	*
	* @return the export manager ID of this export manager fields
	*/
	public long getExportManagerId() {
		return _exportManagerFields.getExportManagerId();
	}

	/**
	* Sets the export manager ID of this export manager fields.
	*
	* @param exportManagerId the export manager ID of this export manager fields
	*/
	public void setExportManagerId(long exportManagerId) {
		_exportManagerFields.setExportManagerId(exportManagerId);
	}

	/**
	* Returns the field name of this export manager fields.
	*
	* @return the field name of this export manager fields
	*/
	public java.lang.String getFieldName() {
		return _exportManagerFields.getFieldName();
	}

	/**
	* Sets the field name of this export manager fields.
	*
	* @param fieldName the field name of this export manager fields
	*/
	public void setFieldName(java.lang.String fieldName) {
		_exportManagerFields.setFieldName(fieldName);
	}

	/**
	* Returns the field label of this export manager fields.
	*
	* @return the field label of this export manager fields
	*/
	public java.lang.String getFieldLabel() {
		return _exportManagerFields.getFieldLabel();
	}

	/**
	* Sets the field label of this export manager fields.
	*
	* @param fieldLabel the field label of this export manager fields
	*/
	public void setFieldLabel(java.lang.String fieldLabel) {
		_exportManagerFields.setFieldLabel(fieldLabel);
	}

	public boolean isNew() {
		return _exportManagerFields.isNew();
	}

	public void setNew(boolean n) {
		_exportManagerFields.setNew(n);
	}

	public boolean isCachedModel() {
		return _exportManagerFields.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_exportManagerFields.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _exportManagerFields.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _exportManagerFields.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_exportManagerFields.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _exportManagerFields.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_exportManagerFields.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ExportManagerFieldsWrapper((ExportManagerFields)_exportManagerFields.clone());
	}

	public int compareTo(
		com.bihan.exportmanager.model.ExportManagerFields exportManagerFields) {
		return _exportManagerFields.compareTo(exportManagerFields);
	}

	@Override
	public int hashCode() {
		return _exportManagerFields.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.bihan.exportmanager.model.ExportManagerFields> toCacheModel() {
		return _exportManagerFields.toCacheModel();
	}

	public com.bihan.exportmanager.model.ExportManagerFields toEscapedModel() {
		return new ExportManagerFieldsWrapper(_exportManagerFields.toEscapedModel());
	}

	public com.bihan.exportmanager.model.ExportManagerFields toUnescapedModel() {
		return new ExportManagerFieldsWrapper(_exportManagerFields.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _exportManagerFields.toString();
	}

	public java.lang.String toXmlString() {
		return _exportManagerFields.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_exportManagerFields.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExportManagerFieldsWrapper)) {
			return false;
		}

		ExportManagerFieldsWrapper exportManagerFieldsWrapper = (ExportManagerFieldsWrapper)obj;

		if (Validator.equals(_exportManagerFields,
					exportManagerFieldsWrapper._exportManagerFields)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ExportManagerFields getWrappedExportManagerFields() {
		return _exportManagerFields;
	}

	public ExportManagerFields getWrappedModel() {
		return _exportManagerFields;
	}

	public void resetOriginalValues() {
		_exportManagerFields.resetOriginalValues();
	}

	private ExportManagerFields _exportManagerFields;
}