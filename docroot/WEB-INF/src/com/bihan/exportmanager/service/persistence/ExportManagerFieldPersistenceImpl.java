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

import com.bihan.exportmanager.NoSuchExportManagerFieldException;
import com.bihan.exportmanager.model.ExportManagerField;
import com.bihan.exportmanager.model.impl.ExportManagerFieldImpl;
import com.bihan.exportmanager.model.impl.ExportManagerFieldModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.StringPool;
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
 * The persistence implementation for the export manager field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sebastienbihan
 * @see ExportManagerFieldPersistence
 * @see ExportManagerFieldUtil
 * @generated
 */
public class ExportManagerFieldPersistenceImpl extends BasePersistenceImpl<ExportManagerField>
	implements ExportManagerFieldPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExportManagerFieldUtil} to access the export manager field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExportManagerFieldImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EXPORTMANAGERID =
		new FinderPath(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldModelImpl.FINDER_CACHE_ENABLED,
			ExportManagerFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByExportManagerId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPORTMANAGERID =
		new FinderPath(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldModelImpl.FINDER_CACHE_ENABLED,
			ExportManagerFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByExportManagerId",
			new String[] { Long.class.getName() },
			ExportManagerFieldModelImpl.EXPORTMANAGERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EXPORTMANAGERID = new FinderPath(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByExportManagerId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldModelImpl.FINDER_CACHE_ENABLED,
			ExportManagerFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldModelImpl.FINDER_CACHE_ENABLED,
			ExportManagerFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the export manager field in the entity cache if it is enabled.
	 *
	 * @param exportManagerField the export manager field
	 */
	public void cacheResult(ExportManagerField exportManagerField) {
		EntityCacheUtil.putResult(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldImpl.class, exportManagerField.getPrimaryKey(),
			exportManagerField);

		exportManagerField.resetOriginalValues();
	}

	/**
	 * Caches the export manager fields in the entity cache if it is enabled.
	 *
	 * @param exportManagerFields the export manager fields
	 */
	public void cacheResult(List<ExportManagerField> exportManagerFields) {
		for (ExportManagerField exportManagerField : exportManagerFields) {
			if (EntityCacheUtil.getResult(
						ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
						ExportManagerFieldImpl.class,
						exportManagerField.getPrimaryKey()) == null) {
				cacheResult(exportManagerField);
			}
			else {
				exportManagerField.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all export manager fields.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExportManagerFieldImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExportManagerFieldImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the export manager field.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExportManagerField exportManagerField) {
		EntityCacheUtil.removeResult(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldImpl.class, exportManagerField.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ExportManagerField> exportManagerFields) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExportManagerField exportManagerField : exportManagerFields) {
			EntityCacheUtil.removeResult(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
				ExportManagerFieldImpl.class, exportManagerField.getPrimaryKey());
		}
	}

	/**
	 * Creates a new export manager field with the primary key. Does not add the export manager field to the database.
	 *
	 * @param exportManagerFieldId the primary key for the new export manager field
	 * @return the new export manager field
	 */
	public ExportManagerField create(long exportManagerFieldId) {
		ExportManagerField exportManagerField = new ExportManagerFieldImpl();

		exportManagerField.setNew(true);
		exportManagerField.setPrimaryKey(exportManagerFieldId);

		return exportManagerField;
	}

	/**
	 * Removes the export manager field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param exportManagerFieldId the primary key of the export manager field
	 * @return the export manager field that was removed
	 * @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a export manager field with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerField remove(long exportManagerFieldId)
		throws NoSuchExportManagerFieldException, SystemException {
		return remove(Long.valueOf(exportManagerFieldId));
	}

	/**
	 * Removes the export manager field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the export manager field
	 * @return the export manager field that was removed
	 * @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a export manager field with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManagerField remove(Serializable primaryKey)
		throws NoSuchExportManagerFieldException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ExportManagerField exportManagerField = (ExportManagerField)session.get(ExportManagerFieldImpl.class,
					primaryKey);

			if (exportManagerField == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExportManagerFieldException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(exportManagerField);
		}
		catch (NoSuchExportManagerFieldException nsee) {
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
	protected ExportManagerField removeImpl(
		ExportManagerField exportManagerField) throws SystemException {
		exportManagerField = toUnwrappedModel(exportManagerField);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, exportManagerField);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(exportManagerField);

		return exportManagerField;
	}

	@Override
	public ExportManagerField updateImpl(
		com.bihan.exportmanager.model.ExportManagerField exportManagerField,
		boolean merge) throws SystemException {
		exportManagerField = toUnwrappedModel(exportManagerField);

		boolean isNew = exportManagerField.isNew();

		ExportManagerFieldModelImpl exportManagerFieldModelImpl = (ExportManagerFieldModelImpl)exportManagerField;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, exportManagerField, merge);

			exportManagerField.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ExportManagerFieldModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((exportManagerFieldModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPORTMANAGERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(exportManagerFieldModelImpl.getOriginalExportManagerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EXPORTMANAGERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPORTMANAGERID,
					args);

				args = new Object[] {
						Long.valueOf(exportManagerFieldModelImpl.getExportManagerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EXPORTMANAGERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPORTMANAGERID,
					args);
			}
		}

		EntityCacheUtil.putResult(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
			ExportManagerFieldImpl.class, exportManagerField.getPrimaryKey(),
			exportManagerField);

		return exportManagerField;
	}

	protected ExportManagerField toUnwrappedModel(
		ExportManagerField exportManagerField) {
		if (exportManagerField instanceof ExportManagerFieldImpl) {
			return exportManagerField;
		}

		ExportManagerFieldImpl exportManagerFieldImpl = new ExportManagerFieldImpl();

		exportManagerFieldImpl.setNew(exportManagerField.isNew());
		exportManagerFieldImpl.setPrimaryKey(exportManagerField.getPrimaryKey());

		exportManagerFieldImpl.setExportManagerFieldId(exportManagerField.getExportManagerFieldId());
		exportManagerFieldImpl.setExportManagerId(exportManagerField.getExportManagerId());
		exportManagerFieldImpl.setFieldName(exportManagerField.getFieldName());
		exportManagerFieldImpl.setFieldDisplayName(exportManagerField.getFieldDisplayName());
		exportManagerFieldImpl.setPosition(exportManagerField.getPosition());

		return exportManagerFieldImpl;
	}

	/**
	 * Returns the export manager field with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the export manager field
	 * @return the export manager field
	 * @throws com.liferay.portal.NoSuchModelException if a export manager field with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManagerField findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the export manager field with the primary key or throws a {@link com.bihan.exportmanager.NoSuchExportManagerFieldException} if it could not be found.
	 *
	 * @param exportManagerFieldId the primary key of the export manager field
	 * @return the export manager field
	 * @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a export manager field with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerField findByPrimaryKey(long exportManagerFieldId)
		throws NoSuchExportManagerFieldException, SystemException {
		ExportManagerField exportManagerField = fetchByPrimaryKey(exportManagerFieldId);

		if (exportManagerField == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					exportManagerFieldId);
			}

			throw new NoSuchExportManagerFieldException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				exportManagerFieldId);
		}

		return exportManagerField;
	}

	/**
	 * Returns the export manager field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the export manager field
	 * @return the export manager field, or <code>null</code> if a export manager field with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExportManagerField fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the export manager field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param exportManagerFieldId the primary key of the export manager field
	 * @return the export manager field, or <code>null</code> if a export manager field with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerField fetchByPrimaryKey(long exportManagerFieldId)
		throws SystemException {
		ExportManagerField exportManagerField = (ExportManagerField)EntityCacheUtil.getResult(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
				ExportManagerFieldImpl.class, exportManagerFieldId);

		if (exportManagerField == _nullExportManagerField) {
			return null;
		}

		if (exportManagerField == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				exportManagerField = (ExportManagerField)session.get(ExportManagerFieldImpl.class,
						Long.valueOf(exportManagerFieldId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (exportManagerField != null) {
					cacheResult(exportManagerField);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ExportManagerFieldModelImpl.ENTITY_CACHE_ENABLED,
						ExportManagerFieldImpl.class, exportManagerFieldId,
						_nullExportManagerField);
				}

				closeSession(session);
			}
		}

		return exportManagerField;
	}

	/**
	 * Returns all the export manager fields where exportManagerId = &#63;.
	 *
	 * @param exportManagerId the export manager ID
	 * @return the matching export manager fields
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExportManagerField> findByExportManagerId(long exportManagerId)
		throws SystemException {
		return findByExportManagerId(exportManagerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	public List<ExportManagerField> findByExportManagerId(
		long exportManagerId, int start, int end) throws SystemException {
		return findByExportManagerId(exportManagerId, start, end, null);
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
	public List<ExportManagerField> findByExportManagerId(
		long exportManagerId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPORTMANAGERID;
			finderArgs = new Object[] { exportManagerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EXPORTMANAGERID;
			finderArgs = new Object[] {
					exportManagerId,
					
					start, end, orderByComparator
				};
		}

		List<ExportManagerField> list = (List<ExportManagerField>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ExportManagerField exportManagerField : list) {
				if ((exportManagerId != exportManagerField.getExportManagerId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_EXPORTMANAGERFIELD_WHERE);

			query.append(_FINDER_COLUMN_EXPORTMANAGERID_EXPORTMANAGERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(exportManagerId);

				list = (List<ExportManagerField>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first export manager field in the ordered set where exportManagerId = &#63;.
	 *
	 * @param exportManagerId the export manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching export manager field
	 * @throws com.bihan.exportmanager.NoSuchExportManagerFieldException if a matching export manager field could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerField findByExportManagerId_First(
		long exportManagerId, OrderByComparator orderByComparator)
		throws NoSuchExportManagerFieldException, SystemException {
		ExportManagerField exportManagerField = fetchByExportManagerId_First(exportManagerId,
				orderByComparator);

		if (exportManagerField != null) {
			return exportManagerField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("exportManagerId=");
		msg.append(exportManagerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExportManagerFieldException(msg.toString());
	}

	/**
	 * Returns the first export manager field in the ordered set where exportManagerId = &#63;.
	 *
	 * @param exportManagerId the export manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching export manager field, or <code>null</code> if a matching export manager field could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerField fetchByExportManagerId_First(
		long exportManagerId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ExportManagerField> list = findByExportManagerId(exportManagerId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public ExportManagerField findByExportManagerId_Last(long exportManagerId,
		OrderByComparator orderByComparator)
		throws NoSuchExportManagerFieldException, SystemException {
		ExportManagerField exportManagerField = fetchByExportManagerId_Last(exportManagerId,
				orderByComparator);

		if (exportManagerField != null) {
			return exportManagerField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("exportManagerId=");
		msg.append(exportManagerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExportManagerFieldException(msg.toString());
	}

	/**
	 * Returns the last export manager field in the ordered set where exportManagerId = &#63;.
	 *
	 * @param exportManagerId the export manager ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching export manager field, or <code>null</code> if a matching export manager field could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExportManagerField fetchByExportManagerId_Last(
		long exportManagerId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByExportManagerId(exportManagerId);

		List<ExportManagerField> list = findByExportManagerId(exportManagerId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public ExportManagerField[] findByExportManagerId_PrevAndNext(
		long exportManagerFieldId, long exportManagerId,
		OrderByComparator orderByComparator)
		throws NoSuchExportManagerFieldException, SystemException {
		ExportManagerField exportManagerField = findByPrimaryKey(exportManagerFieldId);

		Session session = null;

		try {
			session = openSession();

			ExportManagerField[] array = new ExportManagerFieldImpl[3];

			array[0] = getByExportManagerId_PrevAndNext(session,
					exportManagerField, exportManagerId, orderByComparator, true);

			array[1] = exportManagerField;

			array[2] = getByExportManagerId_PrevAndNext(session,
					exportManagerField, exportManagerId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExportManagerField getByExportManagerId_PrevAndNext(
		Session session, ExportManagerField exportManagerField,
		long exportManagerId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXPORTMANAGERFIELD_WHERE);

		query.append(_FINDER_COLUMN_EXPORTMANAGERID_EXPORTMANAGERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(exportManagerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(exportManagerField);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExportManagerField> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the export manager fields.
	 *
	 * @return the export manager fields
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExportManagerField> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<ExportManagerField> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<ExportManagerField> findAll(int start, int end,
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

		List<ExportManagerField> list = (List<ExportManagerField>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXPORTMANAGERFIELD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXPORTMANAGERFIELD;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ExportManagerField>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ExportManagerField>)QueryUtil.list(q,
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
	 * Removes all the export manager fields where exportManagerId = &#63; from the database.
	 *
	 * @param exportManagerId the export manager ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByExportManagerId(long exportManagerId)
		throws SystemException {
		for (ExportManagerField exportManagerField : findByExportManagerId(
				exportManagerId)) {
			remove(exportManagerField);
		}
	}

	/**
	 * Removes all the export manager fields from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ExportManagerField exportManagerField : findAll()) {
			remove(exportManagerField);
		}
	}

	/**
	 * Returns the number of export manager fields where exportManagerId = &#63;.
	 *
	 * @param exportManagerId the export manager ID
	 * @return the number of matching export manager fields
	 * @throws SystemException if a system exception occurred
	 */
	public int countByExportManagerId(long exportManagerId)
		throws SystemException {
		Object[] finderArgs = new Object[] { exportManagerId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EXPORTMANAGERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EXPORTMANAGERFIELD_WHERE);

			query.append(_FINDER_COLUMN_EXPORTMANAGERID_EXPORTMANAGERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(exportManagerId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EXPORTMANAGERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of export manager fields.
	 *
	 * @return the number of export manager fields
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EXPORTMANAGERFIELD);

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
	 * Initializes the export manager field persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.bihan.exportmanager.model.ExportManagerField")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ExportManagerField>> listenersList = new ArrayList<ModelListener<ExportManagerField>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<ExportManagerField>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ExportManagerFieldImpl.class.getName());
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
	private static final String _SQL_SELECT_EXPORTMANAGERFIELD = "SELECT exportManagerField FROM ExportManagerField exportManagerField";
	private static final String _SQL_SELECT_EXPORTMANAGERFIELD_WHERE = "SELECT exportManagerField FROM ExportManagerField exportManagerField WHERE ";
	private static final String _SQL_COUNT_EXPORTMANAGERFIELD = "SELECT COUNT(exportManagerField) FROM ExportManagerField exportManagerField";
	private static final String _SQL_COUNT_EXPORTMANAGERFIELD_WHERE = "SELECT COUNT(exportManagerField) FROM ExportManagerField exportManagerField WHERE ";
	private static final String _FINDER_COLUMN_EXPORTMANAGERID_EXPORTMANAGERID_2 =
		"exportManagerField.exportManagerId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "exportManagerField.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExportManagerField exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ExportManagerField exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ExportManagerFieldPersistenceImpl.class);
	private static ExportManagerField _nullExportManagerField = new ExportManagerFieldImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ExportManagerField> toCacheModel() {
				return _nullExportManagerFieldCacheModel;
			}
		};

	private static CacheModel<ExportManagerField> _nullExportManagerFieldCacheModel =
		new CacheModel<ExportManagerField>() {
			public ExportManagerField toEntityModel() {
				return _nullExportManagerField;
			}
		};
}