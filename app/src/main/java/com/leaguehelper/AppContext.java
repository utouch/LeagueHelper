package com.leaguehelper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.leaguehelper.dao.DaoMaster;
import com.leaguehelper.dao.DaoMaster.DevOpenHelper;
import com.leaguehelper.dao.DaoSession;

/**
 * Created by zs on 15/8/12.
 * <p/>
 * Description
 */
public class AppContext extends Application {

    private static AppContext instance;
    private SQLiteDatabase db;
    private DaoSession mDaoSession;
    private boolean dispatch;

    public boolean isDispatch() {
        return dispatch;
    }

    public void setDispatch(boolean dispatch) {
        this.dispatch = dispatch;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppContext newInstance() {
        return instance;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public SQLiteDatabase getDatabase(){
        if(db == null){
            DevOpenHelper helper = new DaoMaster.DevOpenHelper(newInstance().getApplicationContext(), "notes-db", null);
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public DaoSession getDaoSession(){
        if(mDaoSession == null){
            mDaoSession = new DaoMaster(getDatabase()).newSession();
        }
        return mDaoSession;
    }



}
