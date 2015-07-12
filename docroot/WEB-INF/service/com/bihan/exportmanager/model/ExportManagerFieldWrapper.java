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
 * This class is a wrapper for {@link ExportManagerField}.
 * </p>
 *
 * @author    sebastienbihan
 * @see       ExportManagerField
 * @generated
 */
public class ExportManagerFieldWrapper implements ExportManagerField,
	ModelWrapper<ExportManagerField> {
	public ExportManagerFieldWrapper(ExportManagerField exportManagerField) {
		_exportManagerField = exportManagerField;
	}

	public Class<?> getModelClass() {
		return ExportManagerField.class;
	}

	public String getModelClassName() {
		return ExportManagerField.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("exportManagerFieldId", getExportManagerFieldId());
		attributes.put("exportManagerId", getExportManagerId());
		attributes.put("fieldName", getFieldName());
		attributes.put("fieldDisplayName", getFieldDisplayName());
		attributes.put("position", getPosition());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long exportManagerFieldId = (Long)attributes.get("exportManagerFieldId");

		if (exportManagerFieldId != null) {
			setExportManagerFieldId(exportManagerFieldId);
		}

		Long exportManagerId = (Long)attributes.get("exportManagerId");

		if (exportManagerId != null) {
			setExportManagerId(exportManagerId);
		}

		String fieldName = (String)attributes.get("fieldName");

		if (fieldName != null) {
			setFieldName(fieldName);
		}

		String fieldDisplayName = (String)attributes.get("fieldDisplayName");

		if (fieldDisplayName != null) {
			setFieldDisplayName(fieldDisplayName);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}
	}

	/**
	* Returns the primary key of this export manager field.
	*
	* @return the primary key of this export manager field
	*/
	public long getPrimaryKey() {
		return _exportManagerField.getPrimaryKey();
	}

	/**
	* Sets the primary key of this export manager field.
	*
	* @param primaryKey the primary key of this export manager field
	*/
	public void setPrimaryKey(long primaryKey) {
		_exportManagerField.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the export manager field ID of this export manager field.
	*
	* @return the export manager field ID of this export manager field
	*/
	public long getExportManagerFieldId() {
		return _exportManagerField.getExportManagerFieldId();
	}

	/**
	* Sets the export manager field ID of this export manager field.
	*
	* @param exportManagerFieldId the export manager field ID of this export manager field
	*/
	public void setExportManagerFieldId(long exportManagerFieldId) {
		_exportManagerField.setExportManagerFieldId(exportManagerFieldId);
	}

	/**
	* Returns the export manager ID of this export manager field.
	*
	* @return the export manager ID of this export manager field
	*/
	public long getExportManagerId() {
		return _exportManagerField.getExportManagerId();
	}

	/**
	* Sets the export manager ID of this export manager field.
	*
	* @param exportManagerId the export manager ID of this export manager field
	*/
	public void setExportManagerId(long exportManagerId) {
		_exportManagerField.setExportManagerId(exportManagerId);
	}

	/**
	* Returns the field name of this export manager field.
	*
	* @return the field name of this export manager field
	*/
	public java.lang.String getFieldName() {
		return _exportManagerField.getFieldName();
	}

	/**
	* Sets the field name of this export manager field.
	*
	* @param fieldName the field name of this export manager field
	*/
	public void setFieldName(java.lang.String fieldName) {
		_exportManagerField.setFieldName(fieldName);
	}

	/**
	* Returns the field display name of this export manager field.
	*
	* @return the field display name of this export manager field
	*/
	public java.lang.String getFieldDisplayName() {
		return _exportManagerField.getFieldDisplayName();
	}

	/**
	* Sets the field display name of this export manager field.
	*
	* @param fieldDisplayName the field display name of this export manager field
	*/
	public void setFieldDisplayName(java.lang.String fieldDisplayName) {
		_exportManagerField.setFieldDisplayName(fieldDisplayName);
	}

	/**
	* Returns the position of this export manager field.
	*
	* @return the position of this export manager field
	*/
	public int getPosition() {
		return _exportManagerField.getPosition();
	}

	/**
	* Sets the position of this export manager field.
	*
	* @param position the position of this export manager field
	*/
	public void setPosition(int position) {
		_exportManagerField.setPosition(position);
	}

	public boolean isNew() {
		return _exportManagerField.isNew();
	}

	public void setNew(boolean n) {
		_exportManagerField.setNew(n);
	}

	public boolean isCachedModel() {
		return _exportManagerField.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_exportManagerField.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _exportManagerField.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _exportManagerField.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_exportManagerField.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _exportManagerField.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_exportManagerField.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ExportManagerFieldWrapper((ExportManagerField)_exportManagerField.clone());
	}

	public int compareTo(
		com.bihan.exportmanager.model.ExportManagerField exportManagerField) {
		return _exportManagerField.compareTo(exportManagerField);
	}

	@Override
	public int hashCode() {
		return _exportManagerField.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.bihan.exportmanager.model.ExportManagerField> toCacheModel() {
		return _exportManagerField.toCacheModel();
	}

	public com.bihan.exportmanager.model.ExportManagerField toEscapedModel() {
		return new ExportManagerFieldWrapper(_exportManagerField.toEscapedModel());
	}

	public com.bihan.exportmanager.model.ExportManagerField toUnescapedModel() {
		return new ExportManagerFieldWrapper(_exportManagerField.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _exportManagerField.toString();
	}

	public java.lang.String toXmlString() {
		return _exportManagerField.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_exportManagerField.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExportManagerFieldWrapper)) {
			return false;
		}

		ExportManagerFieldWrapper exportManagerFieldWrapper = (ExportManagerFieldWrapper)obj;

		if (Validator.equals(_exportManagerField,
					exportManagerFieldWrapper._exportManagerField)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ExportManagerField getWrappedExportManagerField() {
		return _exportManagerField;
	}

	public ExportManagerField getWrappedModel() {
		return _exportManagerField;
	}

	public void resetOriginalValues() {
		_exportManagerField.resetOriginalValues();
	}

	private ExportManagerField _exportManagerField;
}