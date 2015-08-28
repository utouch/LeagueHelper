package com.leaguehelper.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.greenrobot.dao.AbstractDao;
import com.greenrobot.dao.Property;
import com.greenrobot.dao.internal.DaoConfig;

import com.leaguehelper.beans.Hero;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HERO".
*/
public class HeroDao extends AbstractDao<Hero, Long> {

    public static final String TABLENAME = "HERO";

    /**
     * Properties of entity Hero.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Sn = new Property(1, String.class, "sn", false, "SN");
        public final static Property Fn = new Property(2, String.class, "fn", false, "FN");
        public final static Property SnEn = new Property(3, String.class, "snEn", false, "SN_EN");
    };


    public HeroDao(DaoConfig config) {
        super(config);
    }
    
    public HeroDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HERO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SN\" TEXT NOT NULL ," + // 1: sn
                "\"FN\" TEXT NOT NULL ," + // 2: fn
                "\"SN_EN\" TEXT NOT NULL );"); // 3: snEn
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HERO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Hero entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getSn());
        stmt.bindString(3, entity.getFn());
        stmt.bindString(4, entity.getSnEn());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Hero readEntity(Cursor cursor, int offset) {
        Hero entity = new Hero( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // sn
            cursor.getString(offset + 2), // fn
            cursor.getString(offset + 3) // snEn
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Hero entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSn(cursor.getString(offset + 1));
        entity.setFn(cursor.getString(offset + 2));
        entity.setSnEn(cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Hero entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Hero entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
