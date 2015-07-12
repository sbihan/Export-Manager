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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ExportManager}.
 * </p>
 *
 * @author    sebastienbihan
 * @see       ExportManager
 * @generated
 */
public class ExportManagerWrapper implements ExportManager,
	ModelWrapper<ExportManager> {
	public ExportManagerWrapper(ExportManager exportManager) {
		_exportManager = exportManager;
	}

	public Class<?> getModelClass() {
		return ExportManager.class;
	}

	public String getModelClassName() {
		return ExportManager.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("exportManagerId", getExportManagerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classNameValue", getClassNameValue());
		attributes.put("scope", getScope());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long exportManagerId = (Long)attributes.get("exportManagerId");

		if (exportManagerId != null) {
			setExportManagerId(exportManagerId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		String classNameValue = (String)attributes.get("classNameValue");

		if (classNameValue != null) {
			setClassNameValue(classNameValue);
		}

		String scope = (String)attributes.get("scope");

		if (scope != null) {
			setScope(scope);
		}
	}

	/**
	* Returns the primary key of this export manager.
	*
	* @return the primary key of this export manager
	*/
	public long getPrimaryKey() {
		return _exportManager.getPrimaryKey();
	}

	/**
	* Sets the primary key of this export manager.
	*
	* @param primaryKey the primary key of this export manager
	*/
	public void setPrimaryKey(long primaryKey) {
		_exportManager.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the export manager ID of this export manager.
	*
	* @return the export manager ID of this export manager
	*/
	public long getExportManagerId() {
		return _exportManager.getExportManagerId();
	}

	/**
	* Sets the export manager ID of this export manager.
	*
	* @param exportManagerId the export manager ID of this export manager
	*/
	public void setExportManagerId(long exportManagerId) {
		_exportManager.setExportManagerId(exportManagerId);
	}

	/**
	* Returns the company ID of this export manager.
	*
	* @return the company ID of this export manager
	*/
	public long getCompanyId() {
		return _exportManager.getCompanyId();
	}

	/**
	* Sets the company ID of this export manager.
	*
	* @param companyId the company ID of this export manager
	*/
	public void setCompanyId(long companyId) {
		_exportManager.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this export manager.
	*
	* @return the user ID of this export manager
	*/
	public long getUserId() {
		return _exportManager.getUserId();
	}

	/**
	* Sets the user ID of this export manager.
	*
	* @param userId the user ID of this export manager
	*/
	public void setUserId(long userId) {
		_exportManager.setUserId(userId);
	}

	/**
	* Returns the user uuid of this export manager.
	*
	* @return the user uuid of this export manager
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManager.getUserUuid();
	}

	/**
	* Sets the user uuid of this export manager.
	*
	* @param userUuid the user uuid of this export manager
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_exportManager.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this export manager.
	*
	* @return the create date of this export manager
	*/
	public java.util.Date getCreateDate() {
		return _exportManager.getCreateDate();
	}

	/**
	* Sets the create date of this export manager.
	*
	* @param createDate the create date of this export manager
	*/
	public void setCreateDate(java.util.Date createDate) {
		_exportManager.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this export manager.
	*
	* @return the modified date of this export manager
	*/
	public java.util.Date getModifiedDate() {
		return _exportManager.getModifiedDate();
	}

	/**
	* Sets the modified date of this export manager.
	*
	* @param modifiedDate the modified date of this export manager
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_exportManager.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this export manager.
	*
	* @return the name of this export manager
	*/
	public java.lang.String getName() {
		return _exportManager.getName();
	}

	/**
	* Sets the name of this export manager.
	*
	* @param name the name of this export manager
	*/
	public void setName(java.lang.String name) {
		_exportManager.setName(name);
	}

	/**
	* Returns the description of this export manager.
	*
	* @return the description of this export manager
	*/
	public java.lang.String getDescription() {
		return _exportManager.getDescription();
	}

	/**
	* Sets the description of this export manager.
	*
	* @param description the description of this export manager
	*/
	public void setDescription(java.lang.String description) {
		_exportManager.setDescription(description);
	}

	/**
	* Returns the fully qualified class name of this export manager.
	*
	* @return the fully qualified class name of this export manager
	*/
	public java.lang.String getClassName() {
		return _exportManager.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_exportManager.setClassName(className);
	}

	/**
	* Returns the class name ID of this export manager.
	*
	* @return the class name ID of this export manager
	*/
	public long getClassNameId() {
		return _exportManager.getClassNameId();
	}

	/**
	* Sets the class name ID of this export manager.
	*
	* @param classNameId the class name ID of this export manager
	*/
	public void setClassNameId(long classNameId) {
		_exportManager.setClassNameId(classNameId);
	}

	/**
	* Returns the class name value of this export manager.
	*
	* @return the class name value of this export manager
	*/
	public java.lang.String getClassNameValue() {
		return _exportManager.getClassNameValue();
	}

	/**
	* Sets the class name value of this export manager.
	*
	* @param classNameValue the class name value of this export manager
	*/
	public void setClassNameValue(java.lang.String classNameValue) {
		_exportManager.setClassNameValue(classNameValue);
	}

	/**
	* Returns the scope of this export manager.
	*
	* @return the scope of this export manager
	*/
	public java.lang.String getScope() {
		return _exportManager.getScope();
	}

	/**
	* Sets the scope of this export manager.
	*
	* @param scope the scope of this export manager
	*/
	public void setScope(java.lang.String scope) {
		_exportManager.setScope(scope);
	}

	public boolean isNew() {
		return _exportManager.isNew();
	}

	public void setNew(boolean n) {
		_exportManager.setNew(n);
	}

	public boolean isCachedModel() {
		return _exportManager.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_exportManager.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _exportManager.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _exportManager.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_exportManager.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _exportManager.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_exportManager.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ExportManagerWrapper((ExportManager)_exportManager.clone());
	}

	public int compareTo(
		com.bihan.exportmanager.model.ExportManager exportManager) {
		return _exportManager.compareTo(exportManager);
	}

	@Override
	public int hashCode() {
		return _exportManager.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.bihan.exportmanager.model.ExportManager> toCacheModel() {
		return _exportManager.toCacheModel();
	}

	public com.bihan.exportmanager.model.ExportManager toEscapedModel() {
		return new ExportManagerWrapper(_exportManager.toEscapedModel());
	}

	public com.bihan.exportmanager.model.ExportManager toUnescapedModel() {
		return new ExportManagerWrapper(_exportManager.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _exportManager.toString();
	}

	public java.lang.String toXmlString() {
		return _exportManager.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_exportManager.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExportManagerWrapper)) {
			return false;
		}

		ExportManagerWrapper exportManagerWrapper = (ExportManagerWrapper)obj;

		if (Validator.equals(_exportManager, exportManagerWrapper._exportManager)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ExportManager getWrappedExportManager() {
		return _exportManager;
	}

	public ExportManager getWrappedModel() {
		return _exportManager;
	}

	public void resetOriginalValues() {
		_exportManager.resetOriginalValues();
	}

	private ExportManager _exportManager;
}