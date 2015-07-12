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
import com.bihan.exportmanager.service.ExportManagerFieldLocalServiceUtil;

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
public class ExportManagerFieldClp extends BaseModelImpl<ExportManagerField>
	implements ExportManagerField {
	public ExportManagerFieldClp() {
	}

	public Class<?> getModelClass() {
		return ExportManagerField.class;
	}

	public String getModelClassName() {
		return ExportManagerField.class.getName();
	}

	public long getPrimaryKey() {
		return _exportManagerFieldId;
	}

	public void setPrimaryKey(long primaryKey) {
		setExportManagerFieldId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_exportManagerFieldId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("exportManagerFieldId", getExportManagerFieldId());
		attributes.put("exportManagerId", getExportManagerId());
		attributes.put("fieldName", getFieldName());
		attributes.put("fieldDisplayName", getFieldDisplayName());
		attributes.put("position", getPosition());

		return attributes;
	}

	@Override
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

	public long getExportManagerFieldId() {
		return _exportManagerFieldId;
	}

	public void setExportManagerFieldId(long exportManagerFieldId) {
		_exportManagerFieldId = exportManagerFieldId;

		if (_exportManagerFieldRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldRemoteModel.getClass();

				Method method = clazz.getMethod("setExportManagerFieldId",
						long.class);

				method.invoke(_exportManagerFieldRemoteModel,
					exportManagerFieldId);
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

		if (_exportManagerFieldRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldRemoteModel.getClass();

				Method method = clazz.getMethod("setExportManagerId", long.class);

				method.invoke(_exportManagerFieldRemoteModel, exportManagerId);
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

		if (_exportManagerFieldRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldRemoteModel.getClass();

				Method method = clazz.getMethod("setFieldName", String.class);

				method.invoke(_exportManagerFieldRemoteModel, fieldName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFieldDisplayName() {
		return _fieldDisplayName;
	}

	public void setFieldDisplayName(String fieldDisplayName) {
		_fieldDisplayName = fieldDisplayName;

		if (_exportManagerFieldRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldRemoteModel.getClass();

				Method method = clazz.getMethod("setFieldDisplayName",
						String.class);

				method.invoke(_exportManagerFieldRemoteModel, fieldDisplayName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;

		if (_exportManagerFieldRemoteModel != null) {
			try {
				Class<?> clazz = _exportManagerFieldRemoteModel.getClass();

				Method method = clazz.getMethod("setPosition", int.class);

				method.invoke(_exportManagerFieldRemoteModel, position);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getExportManagerFieldRemoteModel() {
		return _exportManagerFieldRemoteModel;
	}

	public void setExportManagerFieldRemoteModel(
		BaseModel<?> exportManagerFieldRemoteModel) {
		_exportManagerFieldRemoteModel = exportManagerFieldRemoteModel;
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

		Class<?> remoteModelClass = _exportManagerFieldRemoteModel.getClass();

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

		Object returnValue = method.invoke(_exportManagerFieldRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			ExportManagerFieldLocalServiceUtil.addExportManagerField(this);
		}
		else {
			ExportManagerFieldLocalServiceUtil.updateExportManagerField(this);
		}
	}

	@Override
	public ExportManagerField toEscapedModel() {
		return (ExportManagerField)ProxyUtil.newProxyInstance(ExportManagerField.class.getClassLoader(),
			new Class[] { ExportManagerField.class },
			new AutoEscapeBeanHandler(this));
	}

	public ExportManagerField toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		ExportManagerFieldClp clone = new ExportManagerFieldClp();

		clone.setExportManagerFieldId(getExportManagerFieldId());
		clone.setExportManagerId(getExportManagerId());
		clone.setFieldName(getFieldName());
		clone.setFieldDisplayName(getFieldDisplayName());
		clone.setPosition(getPosition());

		return clone;
	}

	public int compareTo(ExportManagerField exportManagerField) {
		long primaryKey = exportManagerField.getPrimaryKey();

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

		if (!(obj instanceof ExportManagerFieldClp)) {
			return false;
		}

		ExportManagerFieldClp exportManagerField = (ExportManagerFieldClp)obj;

		long primaryKey = exportManagerField.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{exportManagerFieldId=");
		sb.append(getExportManagerFieldId());
		sb.append(", exportManagerId=");
		sb.append(getExportManagerId());
		sb.append(", fieldName=");
		sb.append(getFieldName());
		sb.append(", fieldDisplayName=");
		sb.append(getFieldDisplayName());
		sb.append(", position=");
		sb.append(getPosition());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.bihan.exportmanager.model.ExportManagerField");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>exportManagerFieldId</column-name><column-value><![CDATA[");
		sb.append(getExportManagerFieldId());
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
			"<column><column-name>fieldDisplayName</column-name><column-value><![CDATA[");
		sb.append(getFieldDisplayName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>position</column-name><column-value><![CDATA[");
		sb.append(getPosition());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _exportManagerFieldId;
	private long _exportManagerId;
	private String _fieldName;
	private String _fieldDisplayName;
	private int _position;
	private BaseModel<?> _exportManagerFieldRemoteModel;
}