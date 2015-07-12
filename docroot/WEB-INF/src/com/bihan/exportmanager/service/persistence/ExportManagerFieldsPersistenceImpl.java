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

import com.bihan.exportmanager.NoSuchExportManagerFieldsException;
import com.bihan.exportmanager.model.ExportManagerFields;
import com.bihan.exportmanager.model.impl.ExportManagerFieldsImpl;
import com.bihan.exportmanager.model.impl.ExportManagerFieldsModelImpl;

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
 * The persistence implementation for the export manager fields service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerFieldsPersistence
 * @see ExportManagerFieldsUtil
 * @generated
 */
public class ExportManagerFieldsPersistenceImpl extends BasePersistenceImpl<ExportManagerFields>
	implements ExportManagerFieldsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExportManagerFieldsUtil} to access the export manager fields persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExportManagerFieldsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldsModelImpl.FINDER_CACHE_ENABLED,
			ExportManagerFieldsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldsModelImpl.FINDER_CACHE_ENABLED,
			ExportManagerFieldsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the export manager fields in the entity cache if it is enabled.
	 *
	 * @param exportManagerFields the export manager fields
	 */
	public void cacheResult(ExportManagerFields exportManagerFields) {
		EntityCacheUtil.putResult(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldsImpl.class, exportManagerFields.getPrimaryKey(),
			exportManagerFields);

		exportManagerFields.resetOriginalValues();
	}

	/**
	 * Caches the export manager fieldses in the entity cache if it is enabled.
	 *
	 * @param exportManagerFieldses the export manager fieldses
	 */
	public void cacheResult(List<ExportManagerFields> exportManagerFieldses) {
		for (ExportManagerFields exportManagerFields : exportManagerFieldses) {
			if (EntityCacheUtil.getResult(
						ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
						ExportManagerFieldsImpl.class,
						exportManagerFields.getPrimaryKey()) == null) {
				cacheResult(exportManagerFields);
			}
			else {
				exportManagerFields.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all export manager fieldses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExportManagerFieldsImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExportManagerFieldsImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the export manager fields.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExportManagerFields exportManagerFields) {
		EntityCacheUtil.removeResult(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldsImpl.class, exportManagerFields.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ExportManagerFields> exportManagerFieldses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExportManagerFields exportManagerFields : exportManagerFieldses) {
			EntityCacheUtil.removeResult(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
				ExportManagerFieldsImpl.class,
				exportManagerFields.getPrimaryKey());
		}
	}

	/**
	 * Creates a new export manager fields with the primary key. Does not add the export manager fields to the database.
	 *
	 * @param exportManagerFieldsId the primary key for the new export manager fields
	 * @return the new export manager fields
	 */
	public ExportManagerFields create(long exportManagerFieldsId) {
		ExportManagerFields exportManagerFields = new ExportManagerFieldsImpl();

		exportManagerFields.setNew(true);
		exportManagerFields.setPrimaryKey(exportManagerFieldsId);

		return exportManagerFields;
	}

	/**
	 * Removes the export manager fields with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param exportManagerFieldsId the primary key of the export manager fields
	 * @return the export manager fields that was removed
	 * @throws com.bihan.exportmanager.NoSuchExportManagerFieldsException if a export manager fields with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerFields remove(long exportManagerFieldsId)
		throws NoSuchExportManagerFieldsException, SystemException {
		return remove(Long.valueOf(exportManagerFieldsId));
	}

	/**
	 * Removes the export manager fields with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the export manager fields
	 * @return the export manager fields that was removed
	 * @throws com.bihan.exportmanager.NoSuchExportManagerFieldsException if a export manager fields with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManagerFields remove(Serializable primaryKey)
		throws NoSuchExportManagerFieldsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ExportManagerFields exportManagerFields = (ExportManagerFields)session.get(ExportManagerFieldsImpl.class,
					primaryKey);

			if (exportManagerFields == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExportManagerFieldsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(exportManagerFields);
		}
		catch (NoSuchExportManagerFieldsException nsee) {
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
	protected ExportManagerFields removeImpl(
		ExportManagerFields exportManagerFields) throws SystemException {
		exportManagerFields = toUnwrappedModel(exportManagerFields);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, exportManagerFields);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(exportManagerFields);

		return exportManagerFields;
	}

	@Override
	public ExportManagerFields updateImpl(
		com.bihan.exportmanager.model.ExportManagerFields exportManagerFields,
		boolean merge) throws SystemException {
		exportManagerFields = toUnwrappedModel(exportManagerFields);

		boolean isNew = exportManagerFields.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, exportManagerFields, merge);

			exportManagerFields.setNew(false);
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

		EntityCacheUtil.putResult(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldsImpl.class, exportManagerFields.getPrimaryKey(),
			exportManagerFields);

		return exportManagerFields;
	}

	protected ExportManagerFields toUnwrappedModel(
		ExportManagerFields exportManagerFields) {
		if (exportManagerFields instanceof ExportManagerFieldsImpl) {
			return exportManagerFields;
		}

		ExportManagerFieldsImpl exportManagerFieldsImpl = new ExportManagerFieldsImpl();

		exportManagerFieldsImpl.setNew(exportManagerFields.isNew());
		exportManagerFieldsImpl.setPrimaryKey(exportManagerFields.getPrimaryKey());

		exportManagerFieldsImpl.setExportManagerFieldsId(exportManagerFields.getExportManagerFieldsId());
		exportManagerFieldsImpl.setExportManagerId(exportManagerFields.getExportManagerId());
		exportManagerFieldsImpl.setFieldName(exportManagerFields.getFieldName());
		exportManagerFieldsImpl.setFieldLabel(exportManagerFields.getFieldLabel());

		return exportManagerFieldsImpl;
	}

	/**
	 * Returns the export manager fields with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the export manager fields
	 * @return the export manager fields
	 * @throws com.liferay.portal.NoSuchModelException if a export manager fields with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManagerFields findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the export manager fields with the primary key or throws a {@link com.bihan.exportmanager.NoSuchExportManagerFieldsException} if it could not be found.
	 *
	 * @param exportManagerFieldsId the primary key of the export manager fields
	 * @return the export manager fields
	 * @throws com.bihan.exportmanager.NoSuchExportManagerFieldsException if a export manager fields with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerFields findByPrimaryKey(long exportManagerFieldsId)
		throws NoSuchExportManagerFieldsException, SystemException {
		ExportManagerFields exportManagerFields = fetchByPrimaryKey(exportManagerFieldsId);

		if (exportManagerFields == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					exportManagerFieldsId);
			}

			throw new NoSuchExportManagerFieldsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				exportManagerFieldsId);
		}

		return exportManagerFields;
	}

	/**
	 * Returns the export manager fields with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the export manager fields
	 * @return the export manager fields, or <code>null</code> if a export manager fields with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManagerFields fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the export manager fields with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param exportManagerFieldsId the primary key of the export manager fields
	 * @return the export manager fields, or <code>null</code> if a export manager fields with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerFields fetchByPrimaryKey(long exportManagerFieldsId)
		throws SystemException {
		ExportManagerFields exportManagerFields = (ExportManagerFields)EntityCacheUtil.getResult(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
				ExportManagerFieldsImpl.class, exportManagerFieldsId);

		if (exportManagerFields == _nullExportManagerFields) {
			return null;
		}

		if (exportManagerFields == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				exportManagerFields = (ExportManagerFields)session.get(ExportManagerFieldsImpl.class,
						Long.valueOf(exportManagerFieldsId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (exportManagerFields != null) {
					cacheResult(exportManagerFields);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ExportManagerFieldsModelImpl.ENTITY_CACHE_ENABLED,
						ExportManagerFieldsImpl.class, exportManagerFieldsId,
						_nullExportManagerFields);
				}

				closeSession(session);
			}
		}

		return exportManagerFields;
	}

	/**
	 * Returns all the export manager fieldses.
	 *
	 * @return the export manager fieldses
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExportManagerFields> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<ExportManagerFields> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<ExportManagerFields> findAll(int start, int end,
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

		List<ExportManagerFields> list = (List<ExportManagerFields>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXPORTMANAGERFIELDS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXPORTMANAGERFIELDS;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ExportManagerFields>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ExportManagerFields>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the export manager fieldses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ExportManagerFields exportManagerFields : findAll()) {
			remove(exportManagerFields);
		}
	}

	/**
	 * Returns the number of export manager fieldses.
	 *
	 * @return the number of export manager fieldses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EXPORTMANAGERFIELDS);

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
	 * Initializes the export manager fields persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.bihan.exportmanager.model.ExportManagerFields")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ExportManagerFields>> listenersList = new ArrayList<ModelListener<ExportManagerFields>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<ExportManagerFields>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ExportManagerFieldsImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = ExportManagerPersistence.class)
	protected ExportManagerPersistence exportManagerPersistence;
	@BeanReference(type = ExportManagerFieldsPersistence.class)
	protected ExportManagerFieldsPersistence exportManagerFieldsPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_EXPORTMANAGERFIELDS = "SELECT exportManagerFields FROM ExportManagerFields exportManagerFields";
	private static final String _SQL_COUNT_EXPORTMANAGERFIELDS = "SELECT COUNT(exportManagerFields) FROM ExportManagerFields exportManagerFields";
	private static final String _ORDER_BY_ENTITY_ALIAS = "exportManagerFields.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExportManagerFields exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ExportManagerFieldsPersistenceImpl.class);
	private static ExportManagerFields _nullExportManagerFields = new ExportManagerFieldsImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ExportManagerFields> toCacheModel() {
				return _nullExportManagerFieldsCacheModel;
			}
		};

	private static CacheModel<ExportManagerFields> _nullExportManagerFieldsCacheModel =
		new CacheModel<ExportManagerFields>() {
			public ExportManagerFields toEntityModel() {
				return _nullExportManagerFields;
			}
		};
}