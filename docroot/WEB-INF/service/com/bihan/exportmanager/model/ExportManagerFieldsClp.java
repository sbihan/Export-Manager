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
import com.bihan.exportmanager.service.ExportManagerFieldsLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sebastienbihan
 */
public class ExportManagerFieldsClp extends BaseModelImpl<ExportManagerFields>
	implements ExportManagerFields {
	public ExportManagerFieldsClp() {
	}

	public Class<?> getModelClass() {
		return ExportManagerFields.class;
	}

	public String getModelClassName() {
		return ExportManagerFields.class.getName();
	}

	public long getPrimaryKey() {
		return _exportManagerFieldsId;
	}

	public void setPrimaryKey(long primaryKey) {
		setExportManagerFieldsId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_exportManagerFieldsId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("exportManagerFieldsId", getExportManagerFieldsId());
		attributes.put("exportManagerId", getExportManagerId());
		attributes.put("fieldName", getFieldName());
		attributes.put("fieldLabel", getFieldLabel());

		return attributes;
	}

	@Override
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

	public long getExportManagerFieldsId() {
		return _exportManagerFieldsId;
	}

	public void setExportManagerFieldsId(long exportManagerFieldsId) {
		_exportManagerFieldsId = exportManagerFieldsId;

		if (_exportManagerFieldsRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldsRemoteModel.getClass();

				Method method = clazz.getMethod("setExportManagerFieldsId",
						long.class);

				method.invoke(_exportManagerFieldsRemoteModel,
					exportManagerFieldsId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getExportManagerId() {
		return _exportManagerId;
	}

	public void setExportManagerId(long exportManagerId) {
		_exportManagerId = exportManagerId;

		if (_exportManagerFieldsRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldsRemoteModel.getClass();

				Method method = clazz.getMethod("setExportManagerId", long.class);

				method.invoke(_exportManagerFieldsRemoteModel, exportManagerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFieldName() {
		return _fieldName;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;

		if (_exportManagerFieldsRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldsRemoteModel.getClass();

				Method method = clazz.getMethod("setFieldName", String.class);

				method.invoke(_exportManagerFieldsRemoteModel, fieldName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFieldLabel() {
		return _fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		_fieldLabel = fieldLabel;

		if (_exportManagerFieldsRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldsRemoteModel.getClass();

				Method method = clazz.getMethod("setFieldLabel", String.class);

				method.invoke(_exportManagerFieldsRemoteModel, fieldLabel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getExportManagerFieldsRemoteModel() {
		return _exportManagerFieldsRemoteModel;
	}

	public void setExportManagerFieldsRemoteModel(
		BaseModel<?> exportManagerFieldsRemoteModel) {
		_exportManagerFieldsRemoteModel = exportManagerFieldsRemoteModel;
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

		Class<?> remoteModelClass = _exportManagerFieldsRemoteModel.getClass();

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

		Object returnValue = method.invoke(_exportManagerFieldsRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			ExportManagerFieldsLocalServiceUtil.addExportManagerFields(this);
		}
		else {
			ExportManagerFieldsLocalServiceUtil.updateExportManagerFields(this);
		}
	}

	@Override
	public ExportManagerFields toEscapedModel() {
		return (ExportManagerFields)ProxyUtil.newProxyInstance(ExportManagerFields.class.getClassLoader(),
			new Class[] { ExportManagerFields.class },
			new AutoEscapeBeanHandler(this));
	}

	public ExportManagerFields toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		ExportManagerFieldsClp clone = new ExportManagerFieldsClp();

		clone.setExportManagerFieldsId(getExportManagerFieldsId());
		clone.setExportManagerId(getExportManagerId());
		clone.setFieldName(getFieldName());
		clone.setFieldLabel(getFieldLabel());

		return clone;
	}

	public int compareTo(ExportManagerFields exportManagerFields) {
		long primaryKey = exportManagerFields.getPrimaryKey();

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

		if (!(obj instanceof ExportManagerFieldsClp)) {
			return false;
		}

		ExportManagerFieldsClp exportManagerFields = (ExportManagerFieldsClp)obj;

		long primaryKey = exportManagerFields.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{exportManagerFieldsId=");
		sb.append(getExportManagerFieldsId());
		sb.append(", exportManagerId=");
		sb.append(getExportManagerId());
		sb.append(", fieldName=");
		sb.append(getFieldName());
		sb.append(", fieldLabel=");
		sb.append(getFieldLabel());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.bihan.exportmanager.model.ExportManagerFields");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>exportManagerFieldsId</column-name><column-value><![CDATA[");
		sb.append(getExportManagerFieldsId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>exportManagerId</column-name><column-value><![CDATA[");
		sb.append(getExportManagerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldName</column-name><column-value><![CDATA[");
		sb.append(getFieldName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldLabel</column-name><column-value><![CDATA[");
		sb.append(getFieldLabel());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _exportManagerFieldsId;
	private long _exportManagerId;
	private String _fieldName;
	private String _fieldLabel;
	private BaseModel<?> _exportManagerFieldsRemoteModel;
}