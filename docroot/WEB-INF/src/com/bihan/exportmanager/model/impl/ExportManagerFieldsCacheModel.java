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

package com.bihan.exportmanager.model.impl;

import com.bihan.exportmanager.model.ExportManagerFields;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ExportManagerFields in entity cache.
 *
 * @author sebastienbihan
 * @see ExportManagerFields
 * @generated
 */
public class ExportManagerFieldsCacheModel implements CacheModel<ExportManagerFields>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{exportManagerFieldsId=");
		sb.append(exportManagerFieldsId);
		sb.append(", exportManagerId=");
		sb.append(exportManagerId);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append(", fieldLabel=");
		sb.append(fieldLabel);
		sb.append("}");

		return sb.toString();
	}

	public ExportManagerFields toEntityModel() {
		ExportManagerFieldsImpl exportManagerFieldsImpl = new ExportManagerFieldsImpl();

		exportManagerFieldsImpl.setExportManagerFieldsId(exportManagerFieldsId);
		exportManagerFieldsImpl.setExportManagerId(exportManagerId);

		if (fieldName == null) {
			exportManagerFieldsImpl.setFieldName(StringPool.BLANK);
		}
		else {
			exportManagerFieldsImpl.setFieldName(fieldName);
		}

		if (fieldLabel == null) {
			exportManagerFieldsImpl.setFieldLabel(StringPool.BLANK);
		}
		else {
			exportManagerFieldsImpl.setFieldLabel(fieldLabel);
		}

		exportManagerFieldsImpl.resetOriginalValues();

		return exportManagerFieldsImpl;
	}

	public long exportManagerFieldsId;
	public long exportManagerId;
	public String fieldName;
	public String fieldLabel;
}