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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the export manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerPersistenceImpl
 * @see ExportManagerUtil
 * @generated
 */
public interface ExportManagerPersistence extends BasePersistence<ExportManager> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExportManagerUtil} to access the export manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the export manager in the entity cache if it is enabled.
	*
	* @param exportManager the export manager
	*/
	public void cacheResult(
		com.bihan.exportmanager.model.ExportManager exportManager);

	/**
	* Caches the export managers in the entity cache if it is enabled.
	*
	* @param exportManagers the export managers
	*/
	public void cacheResult(
		java.util.List<com.bihan.exportmanager.model.ExportManager> exportManagers);

	/**
	* Creates a new export manager with the primary key. Does not add the export manager to the database.
	*
	* @param exportManagerId the primary key for the new export manager
	* @return the new export manager
	*/
	public com.bihan.exportmanager.model.ExportManager create(
		long exportManagerId);

	/**
	* Removes the export manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exportManagerId the primary key of the export manager
	* @return the export manager that was removed
	* @throws com.bihan.exportmanager.NoSuchExportManagerException if a export manager with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManager remove(
		long exportManagerId)
		throws com.bihan.exportmanager.NoSuchExportManagerException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.bihan.exportmanager.model.ExportManager updateImpl(
		com.bihan.exportmanager.model.ExportManager exportManager, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the export manager with the primary key or throws a {@link com.bihan.exportmanager.NoSuchExportManagerException} if it could not be found.
	*
	* @param exportManagerId the primary key of the export manager
	* @return the export manager
	* @throws com.bihan.exportmanager.NoSuchExportManagerException if a export manager with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManager findByPrimaryKey(
		long exportManagerId)
		throws com.bihan.exportmanager.NoSuchExportManagerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the export manager with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param exportManagerId the primary key of the export manager
	* @return the export manager, or <code>null</code> if a export manager with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.bihan.exportmanager.model.ExportManager fetchByPrimaryKey(
		long exportManagerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the export managers.
	*
	* @return the export managers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.bihan.exportmanager.model.ExportManager> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.bihan.exportmanager.model.ExportManager> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.bihan.exportmanager.model.ExportManager> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the export managers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of export managers.
	*
	* @return the number of export managers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}