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

import com.bihan.exportmanager.NoSuchExportManagerException;
import com.bihan.exportmanager.model.ExportManager;
import com.bihan.exportmanager.model.impl.ExportManagerImpl;
import com.bihan.exportmanager.model.impl.ExportManagerModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the export manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerPersistence
 * @see ExportManagerUtil
 * @generated
 */
public class ExportManagerPersistenceImpl extends BasePersistenceImpl<ExportManager>
	implements ExportManagerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExportManagerUtil} to access the export manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExportManagerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerModelImpl.FINDER_CACHE_ENABLED,
			ExportManagerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerModelImpl.FINDER_CACHE_ENABLED,
			ExportManagerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the export manager in the entity cache if it is enabled.
	 *
	 * @param exportManager the export manager
	 */
	public void cacheResult(ExportManager exportManager) {
		EntityCacheUtil.putResult(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerImpl.class, exportManager.getPrimaryKey(),
			exportManager);

		exportManager.resetOriginalValues();
	}

	/**
	 * Caches the export managers in the entity cache if it is enabled.
	 *
	 * @param exportManagers the export managers
	 */
	public void cacheResult(List<ExportManager> exportManagers) {
		for (ExportManager exportManager : exportManagers) {
			if (EntityCacheUtil.getResult(
						ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
						ExportManagerImpl.class, exportManager.getPrimaryKey()) == null) {
				cacheResult(exportManager);
			}
			else {
				exportManager.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all export managers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExportManagerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExportManagerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the export manager.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExportManager exportManager) {
		EntityCacheUtil.removeResult(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerImpl.class, exportManager.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ExportManager> exportManagers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExportManager exportManager : exportManagers) {
			EntityCacheUtil.removeResult(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
				ExportManagerImpl.class, exportManager.getPrimaryKey());
		}
	}

	/**
	 * Creates a new export manager with the primary key. Does not add the export manager to the database.
	 *
	 * @param exportManagerId the primary key for the new export manager
	 * @return the new export manager
	 */
	public ExportManager create(long exportManagerId) {
		ExportManager exportManager = new ExportManagerImpl();

		exportManager.setNew(true);
		exportManager.setPrimaryKey(exportManagerId);

		return exportManager;
	}

	/**
	 * Removes the export manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param exportManagerId the primary key of the export manager
	 * @return the export manager that was removed
	 * @throws com.bihan.exportmanager.NoSuchExportManagerException if a export manager with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManager remove(long exportManagerId)
		throws NoSuchExportManagerException, SystemException {
		return remove(Long.valueOf(exportManagerId));
	}

	/**
	 * Removes the export manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the export manager
	 * @return the export manager that was removed
	 * @throws com.bihan.exportmanager.NoSuchExportManagerException if a export manager with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManager remove(Serializable primaryKey)
		throws NoSuchExportManagerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ExportManager exportManager = (ExportManager)session.get(ExportManagerImpl.class,
					primaryKey);

			if (exportManager == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExportManagerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(exportManager);
		}
		catch (NoSuchExportManagerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ExportManager removeImpl(ExportManager exportManager)
		throws SystemException {
		exportManager = toUnwrappedModel(exportManager);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, exportManager);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(exportManager);

		return exportManager;
	}

	@Override
	public ExportManager updateImpl(
		com.bihan.exportmanager.model.ExportManager exportManager, boolean merge)
		throws SystemException {
		exportManager = toUnwrappedModel(exportManager);

		boolean isNew = exportManager.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, exportManager, merge);

			exportManager.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerImpl.class, exportManager.getPrimaryKey(),
			exportManager);

		return exportManager;
	}

	protected ExportManager toUnwrappedModel(ExportManager exportManager) {
		if (exportManager instanceof ExportManagerImpl) {
			return exportManager;
		}

		ExportManagerImpl exportManagerImpl = new ExportManagerImpl();

		exportManagerImpl.setNew(exportManager.isNew());
		exportManagerImpl.setPrimaryKey(exportManager.getPrimaryKey());

		exportManagerImpl.setExportManagerId(exportManager.getExportManagerId());
		exportManagerImpl.setCompanyId(exportManager.getCompanyId());
		exportManagerImpl.setUserId(exportManager.getUserId());
		exportManagerImpl.setCreateDate(exportManager.getCreateDate());
		exportManagerImpl.setModifiedDate(exportManager.getModifiedDate());
		exportManagerImpl.setName(exportManager.getName());
		exportManagerImpl.setDescription(exportManager.getDescription());
		exportManagerImpl.setClassNameId(exportManager.getClassNameId());
		exportManagerImpl.setClassNameValue(exportManager.getClassNameValue());
		exportManagerImpl.setScope(exportManager.getScope());

		return exportManagerImpl;
	}

	/**
	 * Returns the export manager with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the export manager
	 * @return the export manager
	 * @throws com.liferay.portal.NoSuchModelException if a export manager with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManager findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the export manager with the primary key or throws a {@link com.bihan.exportmanager.NoSuchExportManagerException} if it could not be found.
	 *
	 * @param exportManagerId the primary key of the export manager
	 * @return the export manager
	 * @throws com.bihan.exportmanager.NoSuchExportManagerException if a export manager with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManager findByPrimaryKey(long exportManagerId)
		throws NoSuchExportManagerException, SystemException {
		ExportManager exportManager = fetchByPrimaryKey(exportManagerId);

		if (exportManager == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + exportManagerId);
			}

			throw new NoSuchExportManagerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				exportManagerId);
		}

		return exportManager;
	}

	/**
	 * Returns the export manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the export manager
	 * @return the export manager, or <code>null</code> if a export manager with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManager fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the export manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param exportManagerId the primary key of the export manager
	 * @return the export manager, or <code>null</code> if a export manager with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManager fetchByPrimaryKey(long exportManagerId)
		throws SystemException {
		ExportManager exportManager = (ExportManager)EntityCacheUtil.getResult(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
				ExportManagerImpl.class, exportManagerId);

		if (exportManager == _nullExportManager) {
			return null;
		}

		if (exportManager == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				exportManager = (ExportManager)session.get(ExportManagerImpl.class,
						Long.valueOf(exportManagerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (exportManager != null) {
					cacheResult(exportManager);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ExportManagerModelImpl.ENTITY_CACHE_ENABLED,
						ExportManagerImpl.class, exportManagerId,
						_nullExportManager);
				}

				closeSession(session);
			}
		}

		return exportManager;
	}

	/**
	 * Returns all the export managers.
	 *
	 * @return the export managers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExportManager> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<ExportManager> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<ExportManager> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ExportManager> list = (List<ExportManager>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXPORTMANAGER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXPORTMANAGER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ExportManager>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ExportManager>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the export managers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ExportManager exportManager : findAll()) {
			remove(exportManager);
		}
	}

	/**
	 * Returns the number of export managers.
	 *
	 * @return the number of export managers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EXPORTMANAGER);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the export manager persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.bihan.exportmanager.model.ExportManager")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ExportManager>> listenersList = new ArrayList<ModelListener<ExportManager>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<ExportManager>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ExportManagerImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = ExportManagerPersistence.class)
	protected ExportManagerPersistence exportManagerPersistence;
	@BeanReference(type = ExportManagerFieldPersistence.class)
	protected ExportManagerFieldPersistence exportManagerFieldPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_EXPORTMANAGER = "SELECT exportManager FROM ExportManager exportManager";
	private static final String _SQL_COUNT_EXPORTMANAGER = "SELECT COUNT(exportManager) FROM ExportManager exportManager";
	private static final String _ORDER_BY_ENTITY_ALIAS = "exportManager.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExportManager exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ExportManagerPersistenceImpl.class);
	private static ExportManager _nullExportManager = new ExportManagerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ExportManager> toCacheModel() {
				return _nullExportManagerCacheModel;
			}
		};

	private static CacheModel<ExportManager> _nullExportManagerCacheModel = new CacheModel<ExportManager>() {
			public ExportManager toEntityModel() {
				return _nullExportManager;
			}
		};
}