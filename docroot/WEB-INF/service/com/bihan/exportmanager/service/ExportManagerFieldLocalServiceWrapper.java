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
 * This class is a wrapper for {@link ExportManagerFieldLocalService}.
 * </p>
 *
 * @author    sebastienbihan
 * @see       ExportManagerFieldLocalService
 * @generated
 */
public class ExportManagerFieldLocalServiceWrapper
	implements ExportManagerFieldLocalService,
		ServiceWrapper<ExportManagerFieldLocalService> {
	public ExportManagerFieldLocalServiceWrapper(
		ExportManagerFieldLocalService exportManagerFieldLocalService) {
		_exportManagerFieldLocalService = exportManagerFieldLocalService;
	}

	/**
	* Adds the export manager field to the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerField the export manager field
	* @return the export manager field that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerField addExportManagerField(
		com.bihan.exportmanager.model.ExportManagerField exportManagerField)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.addExportManagerField(exportManagerField);
	}

	/**
	* Creates a new export manager field with the primary key. Does not add the export manager field to the database.
	*
	* @param exportManagerFieldId the primary key for the new export manager field
	* @return the new export manager field
	*/
	public com.bihan.exportmanager.model.ExportManagerField createExportManagerField(
		long exportManagerFieldId) {
		return _exportManagerFieldLocalService.createExportManagerField(exportManagerFieldId);
	}

	/**
	* Deletes the export manager field with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerFieldId the primary key of the export manager field
	* @return the export manager field that was removed
	* @throws PortalException if a export manager field with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerField deleteExportManagerField(
		long exportManagerFieldId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.deleteExportManagerField(exportManagerFieldId);
	}

	/**
	* Deletes the export manager field from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerField the export manager field
	* @return the export manager field that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerField deleteExportManagerField(
		com.bihan.exportmanager.model.ExportManagerField exportManagerField)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.deleteExportManagerField(exportManagerField);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _exportManagerFieldLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.bihan.exportmanager.model.ExportManagerField fetchExportManagerField(
		long exportManagerFieldId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.fetchExportManagerField(exportManagerFieldId);
	}

	/**
	* Returns the export manager field with the primary key.
	*
	* @param exportManagerFieldId the primary key of the export manager field
	* @return the export manager field
	* @throws PortalException if a export manager field with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerField getExportManagerField(
		long exportManagerFieldId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.getExportManagerField(exportManagerFieldId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the export manager fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of export manager fields
	* @param end the upper bound of the range of export manager fields (not inclusive)
	* @return the range of export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.bihan.exportmanager.model.ExportManagerField> getExportManagerFields(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.getExportManagerFields(start, end);
	}

	/**
	* Returns the number of export manager fields.
	*
	* @return the number of export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public int getExportManagerFieldsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.getExportManagerFieldsCount();
	}

	/**
	* Updates the export manager field in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param exportManagerField the export manager field
	* @return the export manager field that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerField updateExportManagerField(
		com.bihan.exportmanager.model.ExportManagerField exportManagerField)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.updateExportManagerField(exportManagerField);
	}

	/**
	* Updates the export manager field in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param exportManagerField the export manager field
	* @param merge whether to merge the export manager field with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the export manager field that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerField updateExportManagerField(
		com.bihan.exportmanager.model.ExportManagerField exportManagerField,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.updateExportManagerField(exportManagerField,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _exportManagerFieldLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_exportManagerFieldLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _exportManagerFieldLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public java.util.List<com.bihan.exportmanager.model.ExportManagerField> getExportManagerFields(
		long exportManagerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _exportManagerFieldLocalService.getExportManagerFields(exportManagerId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ExportManagerFieldLocalService getWrappedExportManagerFieldLocalService() {
		return _exportManagerFieldLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedExportManagerFieldLocalService(
		ExportManagerFieldLocalService exportManagerFieldLocalService) {
		_exportManagerFieldLocalService = exportManagerFieldLocalService;
	}

	public ExportManagerFieldLocalService getWrappedService() {
		return _exportManagerFieldLocalService;
	}

	public void setWrappedService(
		ExportManagerFieldLocalService exportManagerFieldLocalService) {
		_exportManagerFieldLocalService = exportManagerFieldLocalService;
	}

	private ExportManagerFieldLocalService _exportManagerFieldLocalService;
}