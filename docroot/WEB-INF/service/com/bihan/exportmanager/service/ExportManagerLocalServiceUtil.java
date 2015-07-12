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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the export manager local service. This utility wraps {@link com.bihan.exportmanager.service.impl.ExportManagerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerLocalService
 * @see com.bihan.exportmanager.service.base.ExportManagerLocalServiceBaseImpl
 * @see com.bihan.exportmanager.service.impl.ExportManagerLocalServiceImpl
 * @generated
 */
public class ExportManagerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.bihan.exportmanager.service.impl.ExportManagerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the export manager to the database. Also notifies the appropriate model listeners.
	*
	* @param exportManager the export manager
	* @return the export manager that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager addExportManager(
		com.bihan.exportmanager.model.ExportManager exportManager)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addExportManager(exportManager);
	}

	/**
	* Creates a new export manager with the primary key. Does not add the export manager to the database.
	*
	* @param exportManagerId the primary key for the new export manager
	* @return the new export manager
	*/
	public static com.bihan.exportmanager.model.ExportManager createExportManager(
		long exportManagerId) {
		return getService().createExportManager(exportManagerId);
	}

	/**
	* Deletes the export manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerId the primary key of the export manager
	* @return the export manager that was removed
	* @throws PortalException if a export manager with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager deleteExportManager(
		long exportManagerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteExportManager(exportManagerId);
	}

	/**
	* Deletes the export manager from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManager the export manager
	* @return the export manager that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager deleteExportManager(
		com.bihan.exportmanager.model.ExportManager exportManager)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteExportManager(exportManager);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.bihan.exportmanager.model.ExportManager fetchExportManager(
		long exportManagerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchExportManager(exportManagerId);
	}

	/**
	* Returns the export manager with the primary key.
	*
	* @param exportManagerId the primary key of the export manager
	* @return the export manager
	* @throws PortalException if a export manager with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager getExportManager(
		long exportManagerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getExportManager(exportManagerId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the export managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of export managers
	* @param end the upper bound of the range of export managers (not inclusive)
	* @return the range of export managers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManager> getExportManagers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getExportManagers(start, end);
	}

	/**
	* Returns the number of export managers.
	*
	* @return the number of export managers
	* @throws SystemException if a system exception occurred
	*/
	public static int getExportManagersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getExportManagersCount();
	}

	/**
	* Updates the export manager in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param exportManager the export manager
	* @return the export manager that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager updateExportManager(
		com.bihan.exportmanager.model.ExportManager exportManager)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateExportManager(exportManager);
	}

	/**
	* Updates the export manager in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param exportManager the export manager
	* @param merge whether to merge the export manager with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the export manager that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager updateExportManager(
		com.bihan.exportmanager.model.ExportManager exportManager, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateExportManager(exportManager, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static void clearService() {
		_service = null;
	}

	public static ExportManagerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ExportManagerLocalService.class.getName());

			if (invokableLocalService instanceof ExportManagerLocalService) {
				_service = (ExportManagerLocalService)invokableLocalService;
			}
			else {
				_service = new ExportManagerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ExportManagerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(ExportManagerLocalService service) {
	}

	private static ExportManagerLocalService _service;
}