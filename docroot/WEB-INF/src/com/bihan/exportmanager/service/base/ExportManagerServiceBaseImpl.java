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

package com.bihan.exportmanager.service.base;

import com.bihan.exportmanager.model.ExportManager;
import com.bihan.exportmanager.service.ExportManagerFieldLocalService;
import com.bihan.exportmanager.service.ExportManagerFieldService;
import com.bihan.exportmanager.service.ExportManagerLocalService;
import com.bihan.exportmanager.service.ExportManagerService;
import com.bihan.exportmanager.service.persistence.ExportManagerFieldPersistence;
import com.bihan.exportmanager.service.persistence.ExportManagerPersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import javax.sql.DataSource;

/**
 * The base implementation of the export manager remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.bihan.exportmanager.service.impl.ExportManagerServiceImpl}.
 * </p>
 *
 * @author sebastienbihan
 * @see com.bihan.exportmanager.service.impl.ExportManagerServiceImpl
 * @see com.bihan.exportmanager.service.ExportManagerServiceUtil
 * @generated
 */
public abstract class ExportManagerServiceBaseImpl extends BaseServiceImpl
	implements ExportManagerService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.bihan.exportmanager.service.ExportManagerServiceUtil} to access the export manager remote service.
	 */

	/**
	 * Returns the export manager local service.
	 *
	 * @return the export manager local service
	 */
	public ExportManagerLocalService getExportManagerLocalService() {
		return exportManagerLocalService;
	}

	/**
	 * Sets the export manager local service.
	 *
	 * @param exportManagerLocalService the export manager local service
	 */
	public void setExportManagerLocalService(
		ExportManagerLocalService exportManagerLocalService) {
		this.exportManagerLocalService = exportManagerLocalService;
	}

	/**
	 * Returns the export manager remote service.
	 *
	 * @return the export manager remote service
	 */
	public ExportManagerService getExportManagerService() {
		return exportManagerService;
	}

	/**
	 * Sets the export manager remote service.
	 *
	 * @param exportManagerService the export manager remote service
	 */
	public void setExportManagerService(
		ExportManagerService exportManagerService) {
		this.exportManagerService = exportManagerService;
	}

	/**
	 * Returns the export manager persistence.
	 *
	 * @return the export manager persistence
	 */
	public ExportManagerPersistence getExportManagerPersistence() {
		return exportManagerPersistence;
	}

	/**
	 * Sets the export manager persistence.
	 *
	 * @param exportManagerPersistence the export manager persistence
	 */
	public void setExportManagerPersistence(
		ExportManagerPersistence exportManagerPersistence) {
		this.exportManagerPersistence = exportManagerPersistence;
	}

	/**
	 * Returns the export manager field local service.
	 *
	 * @return the export manager field local service
	 */
	public ExportManagerFieldLocalService getExportManagerFieldLocalService() {
		return exportManagerFieldLocalService;
	}

	/**
	 * Sets the export manager field local service.
	 *
	 * @param exportManagerFieldLocalService the export manager field local service
	 */
	public void setExportManagerFieldLocalService(
		ExportManagerFieldLocalService exportManagerFieldLocalService) {
		this.exportManagerFieldLocalService = exportManagerFieldLocalService;
	}

	/**
	 * Returns the export manager field remote service.
	 *
	 * @return the export manager field remote service
	 */
	public ExportManagerFieldService getExportManagerFieldService() {
		return exportManagerFieldService;
	}

	/**
	 * Sets the export manager field remote service.
	 *
	 * @param exportManagerFieldService the export manager field remote service
	 */
	public void setExportManagerFieldService(
		ExportManagerFieldService exportManagerFieldService) {
		this.exportManagerFieldService = exportManagerFieldService;
	}

	/**
	 * Returns the export manager field persistence.
	 *
	 * @return the export manager field persistence
	 */
	public ExportManagerFieldPersistence getExportManagerFieldPersistence() {
		return exportManagerFieldPersistence;
	}

	/**
	 * Sets the export manager field persistence.
	 *
	 * @param exportManagerFieldPersistence the export manager field persistence
	 */
	public void setExportManagerFieldPersistence(
		ExportManagerFieldPersistence exportManagerFieldPersistence) {
		this.exportManagerFieldPersistence = exportManagerFieldPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return ExportManager.class;
	}

	protected String getModelClassName() {
		return ExportManager.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = exportManagerPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = ExportManagerLocalService.class)
	protected ExportManagerLocalService exportManagerLocalService;
	@BeanReference(type = ExportManagerService.class)
	protected ExportManagerService exportManagerService;
	@BeanReference(type = ExportManagerPersistence.class)
	protected ExportManagerPersistence exportManagerPersistence;
	@BeanReference(type = ExportManagerFieldLocalService.class)
	protected ExportManagerFieldLocalService exportManagerFieldLocalService;
	@BeanReference(type = ExportManagerFieldService.class)
	protected ExportManagerFieldService exportManagerFieldService;
	@BeanReference(type = ExportManagerFieldPersistence.class)
	protected ExportManagerFieldPersistence exportManagerFieldPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private ExportManagerServiceClpInvoker _clpInvoker = new ExportManagerServiceClpInvoker();
}