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

package com.bihan.exportmanager.service.persistence;

import com.bihan.exportmanager.model.ExportManager;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the export manager service. This utility wraps {@link ExportManagerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerPersistence
 * @see ExportManagerPersistenceImpl
 * @generated
 */
public class ExportManagerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(ExportManager exportManager) {
		getPersistence().clearCache(exportManager);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ExportManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExportManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExportManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static ExportManager update(ExportManager exportManager,
		boolean merge) throws SystemException {
		return getPersistence().update(exportManager, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static ExportManager update(ExportManager exportManager,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(exportManager, merge, serviceContext);
	}

	/**
	* Caches the export manager in the entity cache if it is enabled.
	*
	* @param exportManager the export manager
	*/
	public static void cacheResult(
		com.bihan.exportmanager.model.ExportManager exportManager) {
		getPersistence().cacheResult(exportManager);
	}

	/**
	* Caches the export managers in the entity cache if it is enabled.
	*
	* @param exportManagers the export managers
	*/
	public static void cacheResult(
		java.util.List<com.bihan.exportmanager.model.ExportManager> exportManagers) {
		getPersistence().cacheResult(exportManagers);
	}

	/**
	* Creates a new export manager with the primary key. Does not add the export manager to the database.
	*
	* @param exportManagerId the primary key for the new export manager
	* @return the new export manager
	*/
	public static com.bihan.exportmanager.model.ExportManager create(
		long exportManagerId) {
		return getPersistence().create(exportManagerId);
	}

	/**
	* Removes the export manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerId the primary key of the export manager
	* @return the export manager that was removed
	* @throws com.bihan.exportmanager.NoSuchExportManagerException if a export manager with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager remove(
		long exportManagerId)
		throws com.bihan.exportmanager.NoSuchExportManagerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(exportManagerId);
	}

	public static com.bihan.exportmanager.model.ExportManager updateImpl(
		com.bihan.exportmanager.model.ExportManager exportManager, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(exportManager, merge);
	}

	/**
	* Returns the export manager with the primary key or throws a {@link com.bihan.exportmanager.NoSuchExportManagerException} if it could not be found.
	*
	* @param exportManagerId the primary key of the export manager
	* @return the export manager
	* @throws com.bihan.exportmanager.NoSuchExportManagerException if a export manager with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager findByPrimaryKey(
		long exportManagerId)
		throws com.bihan.exportmanager.NoSuchExportManagerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(exportManagerId);
	}

	/**
	* Returns the export manager with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param exportManagerId the primary key of the export manager
	* @return the export manager, or <code>null</code> if a export manager with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManager fetchByPrimaryKey(
		long exportManagerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(exportManagerId);
	}

	/**
	* Returns all the export managers.
	*
	* @return the export managers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManager> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.bihan.exportmanager.model.ExportManager> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the export managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of export managers
	* @param end the upper bound of the range of export managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of export managers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManager> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the export managers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of export managers.
	*
	* @return the number of export managers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ExportManagerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ExportManagerPersistence)PortletBeanLocatorUtil.locate(com.bihan.exportmanager.service.ClpSerializer.getServletContextName(),
					ExportManagerPersistence.class.getName());

			ReferenceRegistry.registerReference(ExportManagerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(ExportManagerPersistence persistence) {
	}

	private static ExportManagerPersistence _persistence;
}