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

import com.bihan.exportmanager.model.ExportManager;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing ExportManager in entity cache.
 *
 * @author sebastienbihan
 * @see ExportManager
 * @generated
 */
public class ExportManagerCacheModel implements CacheModel<ExportManager>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{exportManagerId=");
		sb.append(exportManagerId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classNameValue=");
		sb.append(classNameValue);
		sb.append(", scope=");
		sb.append(scope);
		sb.append("}");

		return sb.toString();
	}

	public ExportManager toEntityModel() {
		ExportManagerImpl exportManagerImpl = new ExportManagerImpl();

		exportManagerImpl.setExportManagerId(exportManagerId);
		exportManagerImpl.setCompanyId(companyId);
		exportManagerImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			exportManagerImpl.setCreateDate(null);
		}
		else {
			exportManagerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			exportManagerImpl.setModifiedDate(null);
		}
		else {
			exportManagerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			exportManagerImpl.setName(StringPool.BLANK);
		}
		else {
			exportManagerImpl.setName(name);
		}

		if (description == null) {
			exportManagerImpl.setDescription(StringPool.BLANK);
		}
		else {
			exportManagerImpl.setDescription(description);
		}

		exportManagerImpl.setClassNameId(classNameId);

		if (classNameValue == null) {
			exportManagerImpl.setClassNameValue(StringPool.BLANK);
		}
		else {
			exportManagerImpl.setClassNameValue(classNameValue);
		}

		if (scope == null) {
			exportManagerImpl.setScope(StringPool.BLANK);
		}
		else {
			exportManagerImpl.setScope(scope);
		}

		exportManagerImpl.resetOriginalValues();

		return exportManagerImpl;
	}

	public long exportManagerId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public long classNameId;
	public String classNameValue;
	public String scope;
}