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

import com.bihan.exportmanager.model.ExportManagerField;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the export manager field service. This utility wraps {@link ExportManagerFieldPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerFieldPersistence
 * @see ExportManagerFieldPersistenceImpl
 * @generated
 */
public class ExportManagerFieldUtil {
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
	public static void clearCache(ExportManagerField exportManagerField) {
		getPersistence().clearCache(exportManagerField);
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
	public static List<ExportManagerField> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExportManagerField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExportManagerField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static ExportManagerField update(
		ExportManagerField exportManagerField, boolean merge)
		throws SystemException {
		return getPersistence().update(exportManagerField, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static ExportManagerField update(
		ExportManagerField exportManagerField, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(exportManagerField, merge, serviceContext);
	}

	/**
	* Caches the export manager field in the entity cache if it is enabled.
	*
	* @param exportManagerField the export manager field
	*/
	public static void cacheResult(
		com.bihan.exportmanager.model.ExportManagerField exportManagerField) {
		getPersistence().cacheResult(exportManagerField);
	}

	/**
	* Caches the export manager fields in the entity cache if it is enabled.
	*
	* @param exportManagerFields the export manager fields
	*/
	public static void cacheResult(
		java.util.List<com.bihan.exportmanager.model.ExportManagerField> exportManagerFields) {
		getPersistence().cacheResult(exportManagerFields);
	}

	/**
	* Creates a new export manager field with the primary key. Does not add the export manager field to the database.
	*
	* @param exportManagerFieldId the primary key for the new export manager field
	* @return the new export manager field
	*/
	public static com.bihan.exportmanager.model.ExportManagerField create(
		long exportManagerFieldId) {
		return getPersistence().create(exportManagerFieldId);
	}

	/**
	* Removes the export manager field with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerFieldId the primary key of the export manager field
	* @return the export manager field that was removed
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a export manager field with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerField remove(
		long exportManagerFieldId)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(exportManagerFieldId);
	}

	public static com.bihan.exportmanager.model.ExportManagerField updateImpl(
		com.bihan.exportmanager.model.ExportManagerField exportManagerField,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(exportManagerField, merge);
	}

	/**
	* Returns the export manager field with the primary key or throws a {@link com.bihan.exportmanager.NoSuchExportManagerFieldException} if it could not be found.
	*
	* @param exportManagerFieldId the primary key of the export manager field
	* @return the export manager field
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a export manager field with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerField findByPrimaryKey(
		long exportManagerFieldId)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(exportManagerFieldId);
	}

	/**
	* Returns the export manager field with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param exportManagerFieldId the primary key of the export manager field
	* @return the export manager field, or <code>null</code> if a export manager field with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerField fetchByPrimaryKey(
		long exportManagerFieldId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(exportManagerFieldId);
	}

	/**
	* Returns all the export manager fields where exportManagerId = &#63;.
	*
	* @param exportManagerId the export manager ID
	* @return the matching export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerField> findByExportManagerId(
		long exportManagerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByExportManagerId(exportManagerId);
	}

	/**
	* Returns a range of all the export manager fields where exportManagerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param exportManagerId the export manager ID
	* @param start the lower bound of the range of export manager fields
	* @param end the upper bound of the range of export manager fields (not inclusive)
	* @return the range of matching export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerField> findByExportManagerId(
		long exportManagerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByExportManagerId(exportManagerId, start, end);
	}

	/**
	* Returns an ordered range of all the export manager fields where exportManagerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param exportManagerId the export manager ID
	* @param start the lower bound of the range of export manager fields
	* @param end the upper bound of the range of export manager fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerField> findByExportManagerId(
		long exportManagerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByExportManagerId(exportManagerId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first export manager field in the ordered set where exportManagerId = &#63;.
	*
	* @param exportManagerId the export manager ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching export manager field
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a matching export manager field could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerField findByExportManagerId_First(
		long exportManagerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByExportManagerId_First(exportManagerId,
			orderByComparator);
	}

	/**
	* Returns the first export manager field in the ordered set where exportManagerId = &#63;.
	*
	* @param exportManagerId the export manager ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching export manager field, or <code>null</code> if a matching export manager field could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerField fetchByExportManagerId_First(
		long exportManagerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByExportManagerId_First(exportManagerId,
			orderByComparator);
	}

	/**
	* Returns the last export manager field in the ordered set where exportManagerId = &#63;.
	*
	* @param exportManagerId the export manager ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching export manager field
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a matching export manager field could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerField findByExportManagerId_Last(
		long exportManagerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByExportManagerId_Last(exportManagerId,
			orderByComparator);
	}

	/**
	* Returns the last export manager field in the ordered set where exportManagerId = &#63;.
	*
	* @param exportManagerId the export manager ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching export manager field, or <code>null</code> if a matching export manager field could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerField fetchByExportManagerId_Last(
		long exportManagerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByExportManagerId_Last(exportManagerId,
			orderByComparator);
	}

	/**
	* Returns the export manager fields before and after the current export manager field in the ordered set where exportManagerId = &#63;.
	*
	* @param exportManagerFieldId the primary key of the current export manager field
	* @param exportManagerId the export manager ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next export manager field
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a export manager field with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bihan.exportmanager.model.ExportManagerField[] findByExportManagerId_PrevAndNext(
		long exportManagerFieldId, long exportManagerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByExportManagerId_PrevAndNext(exportManagerFieldId,
			exportManagerId, orderByComparator);
	}

	/**
	* Returns all the export manager fields.
	*
	* @return the export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerField> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerField> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the export manager fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of export manager fields
	* @param end the upper bound of the range of export manager fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bihan.exportmanager.model.ExportManagerField> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the export manager fields where exportManagerId = &#63; from the database.
	*
	* @param exportManagerId the export manager ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByExportManagerId(long exportManagerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByExportManagerId(exportManagerId);
	}

	/**
	* Removes all the export manager fields from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of export manager fields where exportManagerId = &#63;.
	*
	* @param exportManagerId the export manager ID
	* @return the number of matching export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public static int countByExportManagerId(long exportManagerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByExportManagerId(exportManagerId);
	}

	/**
	* Returns the number of export manager fields.
	*
	* @return the number of export manager fields
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ExportManagerFieldPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ExportManagerFieldPersistence)PortletBeanLocatorUtil.locate(com.bihan.exportmanager.service.ClpSerializer.getServletContextName(),
					ExportManagerFieldPersistence.class.getName());

			ReferenceRegistry.registerReference(ExportManagerFieldUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(ExportManagerFieldPersistence persistence) {
	}

	private static ExportManagerFieldPersistence _persistence;
}