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

package com.bihan.exportmanager.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ExportManagerFieldsService}.
 * </p>
 *
 * @author    sebastienbihan
 * @see       ExportManagerFieldsService
 * @generated
 */
public class ExportManagerFieldsServiceWrapper
	implements ExportManagerFieldsService,
		ServiceWrapper<ExportManagerFieldsService> {
	public ExportManagerFieldsServiceWrapper(
		ExportManagerFieldsService exportManagerFieldsService) {
		_exportManagerFieldsService = exportManagerFieldsService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _exportManagerFieldsService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_exportManagerFieldsService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _exportManagerFieldsService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ExportManagerFieldsService getWrappedExportManagerFieldsService() {
		return _exportManagerFieldsService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedExportManagerFieldsService(
		ExportManagerFieldsService exportManagerFieldsService) {
		_exportManagerFieldsService = exportManagerFieldsService;
	}

	public ExportManagerFieldsService getWrappedService() {
		return _exportManagerFieldsService;
	}

	public void setWrappedService(
		ExportManagerFieldsService exportManagerFieldsService) {
		_exportManagerFieldsService = exportManagerFieldsService;
	}

	private ExportManagerFieldsService _exportManagerFieldsService;
}