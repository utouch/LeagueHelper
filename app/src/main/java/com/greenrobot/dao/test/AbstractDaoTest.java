/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greenrobot.dao.test;

import android.database.sqlite.SQLiteDatabase;

import com.greenrobot.dao.AbstractDao;
import com.greenrobot.dao.DaoLog;
import com.greenrobot.dao.InternalUnitTestDaoAccess;
import com.greenrobot.dao.Property;
import com.greenrobot.dao.identityscope.IdentityScope;

import java.lang.reflect.Method;

/**
 * Base class for DAO related testing. Prepares an in-memory DB and DAO.
 * 
 * @author Markus
 * 
 * @param <D>
 *            DAO class
 * @param <T>
 *            Entity type of the DAO
 * @param <K>
 *            Key type of the DAO
 */
public abstract class AbstractDaoTest<D extends AbstractDao<T, K>, T, K> extends DbTest {

    protected final Class<D> daoClass;
    protected D dao;
    protected InternalUnitTestDaoAccess<T, K> daoAccess;
    protected Property pkColumn;
    protected IdentityScope<K, T> identityScopeForDao;

    public AbstractDaoTest(Class<D> daoClass) {
        this(daoClass, true);
    }

    public AbstractDaoTest(Class<D> daoClass, boolean inMemory) {
        super(inMemory);
        this.daoClass = daoClass;
    }

    public void setIdentityScopeBeforeSetUp(IdentityScope<K, T> identityScope) {
        this.identityScopeForDao = identityScope;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            setUpTableForDao();
            daoAccess = new InternalUnitTestDaoAccess<T, K>(db, (Class<AbstractDao<T, K>>) daoClass, identityScopeForDao);
            dao = (D) daoAccess.getDao();
        } catch (Exception e) {
            throw new RuntimeException("Could not prepare DAO Test", e);
        }
    }

    protected void setUpTableForDao() throws Exception {
        try {
            Method createTableMethod = daoClass.getMethod("createTable", SQLiteDatabase.class, boolean.class);
            createTableMethod.invoke(null, db, false);
        } catch (NoSuchMethodException e) {
            DaoLog.i("No createTable method");
        }
    }

    protected void clearIdentityScopeIfAny() {
        if (identityScopeForDao != null) {
            identityScopeForDao.clear();
            DaoLog.d("Identity scope cleared");
        } else {
            DaoLog.d("No identity scope to clear");
        }
    }

    protected void logTableDump() {
        logTableDump(dao.getTablename());
    }
}