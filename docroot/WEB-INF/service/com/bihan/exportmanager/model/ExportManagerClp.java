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

import com.bihan.exportmanager.service.ClpSerializer;
import com.bihan.exportmanager.service.ExportManagerLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sebastienbihan
 */
public class ExportManagerClp extends BaseModelImpl<ExportManager>
	implements ExportManager {
	public ExportManagerClp() {
	}

	public Class<?> getModelClass() {
		return ExportManager.class;
	}

	public String getModelClassName() {
		return ExportManager.class.getName();
	}

	public long getPrimaryKey() {
		return _exportManagerId;
	}

	public void setPrimaryKey(long primaryKey) {
		setExportManagerId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_exportManagerId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

	@Override
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

	public long getExportManagerId() {
		return _exportManagerId;
	}

	public void setExportManagerId(long exportManagerId) {
		_exportManagerId = exportManagerId;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setExportManagerId", long.class);

				method.invoke(_exportManagerRemoteModel, exportManagerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_exportManagerRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_exportManagerRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_exportManagerRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_exportManagerRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_exportManagerRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_exportManagerRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_exportManagerRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassNameValue() {
		return _classNameValue;
	}

	public void setClassNameValue(String classNameValue) {
		_classNameValue = classNameValue;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameValue",
						String.class);

				method.invoke(_exportManagerRemoteModel, classNameValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getScope() {
		return _scope;
	}

	public void setScope(String scope) {
		_scope = scope;

		if (_exportManagerRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerRemoteModel.getClass();

				Method method = clazz.getMethod("setScope", String.class);

				method.invoke(_exportManagerRemoteModel, scope);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getExportManagerRemoteModel() {
		return _exportManagerRemoteModel;
	}

	public void setExportManagerRemoteModel(
		BaseModel<?> exportManagerRemoteModel) {
		_exportManagerRemoteModel = exportManagerRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _exportManagerRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_exportManagerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			ExportManagerLocalServiceUtil.addExportManager(this);
		}
		else {
			ExportManagerLocalServiceUtil.updateExportManager(this);
		}
	}

	@Override
	public ExportManager toEscapedModel() {
		return (ExportManager)ProxyUtil.newProxyInstance(ExportManager.class.getClassLoader(),
			new Class[] { ExportManager.class }, new AutoEscapeBeanHandler(this));
	}

	public ExportManager toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		ExportManagerClp clone = new ExportManagerClp();

		clone.setExportManagerId(getExportManagerId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setClassNameId(getClassNameId());
		clone.setClassNameValue(getClassNameValue());
		clone.setScope(getScope());

		return clone;
	}

	public int compareTo(ExportManager exportManager) {
		long primaryKey = exportManager.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExportManagerClp)) {
			return false;
		}

		ExportManagerClp exportManager = (ExportManagerClp)obj;

		long primaryKey = exportManager.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{exportManagerId=");
		sb.append(getExportManagerId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classNameValue=");
		sb.append(getClassNameValue());
		sb.append(", scope=");
		sb.append(getScope());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.bihan.exportmanager.model.ExportManager");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>exportManagerId</column-name><column-value><![CDATA[");
		sb.append(getExportManagerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameValue</column-name><column-value><![CDATA[");
		sb.append(getClassNameValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scope</column-name><column-value><![CDATA[");
		sb.append(getScope());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _exportManagerId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private long _classNameId;
	private String _classNameValue;
	private String _scope;
	private BaseModel<?> _exportManagerRemoteModel;
}