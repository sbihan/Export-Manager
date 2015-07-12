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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the export manager fields service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerFieldsPersistenceImpl
 * @see ExportManagerFieldsUtil
 * @generated
 */
public interface ExportManagerFieldsPersistence extends BasePersistence<ExportManagerFields> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExportManagerFieldsUtil} to access the export manager fields persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the export manager fields in the entity cache if it is enabled.
	*
	* @param exportManagerFields the export manager fields
	*/
	public void cacheResult(
		com.bihan.exportmanager.model.ExportManagerFields exportManagerFields);

	/**
	* Caches the export manager fieldses in the entity cache if it is enabled.
	*
	* @param exportManagerFieldses the export manager fieldses
	*/
	public void cacheResult(
		java.util.List<com.bihan.exportmanager.model.ExportManagerFields> exportManagerFieldses);

	/**
	* Creates a new export manager fields with the primary key. Does not add the export manager fields to the database.
	*
	* @param exportManagerFieldsId the primary key for the new export manager fields
	* @return the new export manager fields
	*/
	public com.bihan.exportmanager.model.ExportManagerFields create(
		long exportManagerFieldsId);

	/**
	* Removes the export manager fields with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerFieldsId the primary key of the export manager fields
	* @return the export manager fields that was removed
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldsException if a export manager fields with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerFields remove(
		long exportManagerFieldsId)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldsException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.bihan.exportmanager.model.ExportManagerFields updateImpl(
		com.bihan.exportmanager.model.ExportManagerFields exportManagerFields,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the export manager fields with the primary key or throws a {@link com.bihan.exportmanager.NoSuchExportManagerFieldsException} if it could not be found.
	*
	* @param exportManagerFieldsId the primary key of the export manager fields
	* @return the export manager fields
	* @throws com.bihan.exportmanager.NoSuchExportManagerFieldsException if a export manager fields with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerFields findByPrimaryKey(
		long exportManagerFieldsId)
		throws com.bihan.exportmanager.NoSuchExportManagerFieldsException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the export manager fields with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param exportManagerFieldsId the primary key of the export manager fields
	* @return the export manager fields, or <code>null</code> if a export manager fields with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManagerFields fetchByPrimaryKey(
		long exportManagerFieldsId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the export manager fieldses.
	*
	* @return the export manager fieldses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.bihan.exportmanager.model.ExportManagerFields> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.bihan.exportmanager.model.ExportManagerFields> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.bihan.exportmanager.model.ExportManagerFields> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the export manager fieldses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of export manager fieldses.
	*
	* @return the number of export manager fieldses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}