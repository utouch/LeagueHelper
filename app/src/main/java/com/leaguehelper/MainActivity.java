package com.leaguehelper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.leaguehelper.adapter.RecyclerViewAdapter;
import com.leaguehelper.beans.ActivityClassInfo;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<ActivityClassInfo> infos;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(this);
        //默认不写为垂直滚动
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        infos = getActivitysPackage();
        adapter = new RecyclerViewAdapter(infos);
        mRecyclerView.setAdapter(adapter);
        //这里要自己写监听事件
        adapter.setOnItemClickListener(new mOnItemClickListener());


        AppContext.newInstance().getDaoSession();


//        Intent intent =new Intent(getApplicationContext(), BootReceiver.class);
//        intent.setAction("BOOT_COMPLETED");
//        PendingIntent sender=PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
//
//        //开始时间
//        long firstime= SystemClock.elapsedRealtime();
//
//        AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);
//        //5秒一个周期，不停的发送广播
//        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 5 * 1000, sender);
    }

    private ArrayList<ActivityClassInfo> getActivitysPackage() {
        ArrayList<ActivityClassInfo> activityClasses = new ArrayList<>();
        try {
            PackageInfo pi = getApplicationContext().getPackageManager().getPackageInfo("com.leaguehelper", PackageManager.GET_ACTIVITIES);
            ActivityClassInfo info;
            Class c;
            for (ActivityInfo activity : pi.activities) {
                try {
                    info = new ActivityClassInfo();
                    if (!TextUtils.equals(TAG, activity.name)) {
                        c = Class.forName(activity.name);
                        info.setCl(c);
                        info.setName(activity.name.substring(activity.name.lastIndexOf(".") + 1, activity.name.length()));
                        if (Activity.class.isAssignableFrom(c)) {
                            activityClasses.add(info);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return activityClasses;
    }

    class mOnItemClickListener implements RecyclerViewAdapter.onItemClickListener {
        @Override
        public void onItemClick(View view, int position) {
            startActivity(new Intent(getApplicationContext(), infos.get(position).getCl()));
        }
    }



}
