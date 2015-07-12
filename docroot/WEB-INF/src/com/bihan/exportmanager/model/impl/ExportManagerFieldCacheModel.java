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

import com.bihan.exportmanager.model.ExportManagerField;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ExportManagerField in entity cache.
 *
 * @author sebastienbihan
 * @see ExportManagerField
 * @generated
 */
public class ExportManagerFieldCacheModel implements CacheModel<ExportManagerField>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{exportManagerFieldId=");
		sb.append(exportManagerFieldId);
		sb.append(", exportManagerId=");
		sb.append(exportManagerId);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append(", fieldDisplayName=");
		sb.append(fieldDisplayName);
		sb.append(", position=");
		sb.append(position);
		sb.append("}");

		return sb.toString();
	}

	public ExportManagerField toEntityModel() {
		ExportManagerFieldImpl exportManagerFieldImpl = new ExportManagerFieldImpl();

		exportManagerFieldImpl.setExportManagerFieldId(exportManagerFieldId);
		exportManagerFieldImpl.setExportManagerId(exportManagerId);

		if (fieldName == null) {
			exportManagerFieldImpl.setFieldName(StringPool.BLANK);
		}
		else {
			exportManagerFieldImpl.setFieldName(fieldName);
		}

		if (fieldDisplayName == null) {
			exportManagerFieldImpl.setFieldDisplayName(StringPool.BLANK);
		}
		else {
			exportManagerFieldImpl.setFieldDisplayName(fieldDisplayName);
		}

		exportManagerFieldImpl.setPosition(position);

		exportManagerFieldImpl.resetOriginalValues();

		return exportManagerFieldImpl;
	}

	public long exportManagerFieldId;
	public long exportManagerId;
	public String fieldName;
	public String fieldDisplayName;
	public int position;
}