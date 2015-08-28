package com.leaguehelper.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.leaguehelper.R;

/**
 * Created by zs on 15/8/26.
 * <p/>
 * Description
 */
public class TestActivity extends Activity implements View.OnTouchListener {
    LinearLayout dragto;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        dragto = (LinearLayout) findViewById(R.id.dragto_layout);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        findViewById(R.id.item_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dragto.offsetTopAndBottom(10);
            }
        });

        findViewById(R.id.item_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onTouch", "***************"+scrollView.getScrollY());
            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float y = event.getY();
                Log.e("onTouch", "ACTION_DOWN-----"+y+"");
                break;
            case MotionEvent.ACTION_MOVE:
                float y2 = event.getY();
                Log.e("onTouch", "----------ACTION_MOVE--"+y2 + "");
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return false;
    }


}
