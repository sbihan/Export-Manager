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

import com.bihan.exportmanager.model.ExportManagerFields;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the export manager fields service. This utility wraps {@link ExportManagerFieldsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerFieldsPersistence
 * @see ExportManagerFieldsPersistenceImpl
 * @generated
 */
public class ExportManagerFieldsUtil {
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
	public static void clearCache(ExportManagerFields exportManagerFields) {
		getPersistence().clearCache(exportManagerFields);
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
	public static List<ExportManagerFields> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExportManagerFields> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExportManagerFields> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static ExportManagerFields update(
		ExportManagerFields exportManagerFields, boolean merge)
		throws SystemException {
		return getPersistence().update(exportManagerFields, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static ExportManagerFields update(
		ExportManagerFields exportManagerFields, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(exportManagerFields, merge, serviceContext);
	}

	/**
	* Caches the export manager fields in the entity cache if it is enabled.
	*
	* @param exportManagerFields the export manager fields
	*/
	public static void cacheResult(
		com.bihan.exportmanager.model.ExportManagerFields exportManagerFields) {
		getPersistence().cacheResult(exportManagerFields);
	}

	/**
	* Caches the export manager fieldses in the entity cache if it is enabled.
	*
	* @param exportManagerFieldses the export manager fieldses
	*/
	public static void cacheResult(
		java.util.List<com.bihan.exportmanager.model.ExportManagerFields> exportManagerFieldses) {
		getPersistence().cacheResult(exportManagerFieldses);
	}

	/**
	* Creates a new export manager fields with the primary key. Does not add the export manager fields to the database.
	*
	* @param exportManagerFieldsId the primary key for the new export manager fields
	* @return the new export manager fields
	*/
	public static com.bihan.exportmanager.model.ExportManagerFields create(
		long exportManagerFieldsId) {
		return getPersistence().create(exportManagerFieldsId);
	}

	/**
	* Removes the export manager fields with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerFieldsId the primary key of the export manager fields
	* @return the export manager fields that was removed
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldsException if a export manager fields with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerFields remove(
		long exportManagerFieldsId)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldsException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(exportManagerFieldsId);
	}

	public static com.bihan.exportmanager.model.ExportManagerFields updateImpl(
		com.bihan.exportmanager.model.ExportManagerFields exportManagerFields,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(exportManagerFields, merge);
	}

	/**
	* Returns the export manager fields with the primary key or throws a {@link com.bihan.exportmanager.NoSuchExportManagerFieldsException} if it could not be found.
	*
	* @param exportManagerFieldsId the primary key of the export manager fields
	* @return the export manager fields
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldsException if a export manager fields with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerFields findByPrimaryKey(
		long exportManagerFieldsId)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldsException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(exportManagerFieldsId);
	}

	/**
	* Returns the export manager fields with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param exportManagerFieldsId the primary key of the export manager fields
	* @return the export manager fields, or <code>null</code> if a export manager fields with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerFields fetchByPrimaryKey(
		long exportManagerFieldsId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(exportManagerFieldsId);
	}

	/**
	* Returns all the export manager fieldses.
	*
	* @return the export manager fieldses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerFields> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the export manager fieldses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of export manager fieldses
	* @param end the upper bound of the range of export manager fieldses (not inclusive)
	* @return the range of export manager fieldses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerFields> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the export manager fieldses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of export manager fieldses
	* @param end the upper bound of the range of export manager fieldses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of export manager fieldses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerFields> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the export manager fieldses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of export manager fieldses.
	*
	* @return the number of export manager fieldses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ExportManagerFieldsPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ExportManagerFieldsPersistence)PortletBeanLocatorUtil.locate(com.bihan.exportmanager.service.ClpSerializer.getServletContextName(),
					ExportManagerFieldsPersistence.class.getName());

			ReferenceRegistry.registerReference(ExportManagerFieldsUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(ExportManagerFieldsPersistence persistence) {
	}

	private static ExportManagerFieldsPersistence _persistence;
}