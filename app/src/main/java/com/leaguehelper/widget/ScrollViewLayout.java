package com.leaguehelper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.leaguehelper.AppContext;

/**
 * Created by zs on 15/8/27.
 * <p/>
 * Description
 */
public class ScrollViewLayout extends ScrollView {
    private static final String TAG = ScrollViewLayout.class.getSimpleName();
    public ScrollViewLayout(Context context) {
        super(context);
    }

    public ScrollViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG,"onInterceptTouchEvent()...");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG,"onTouchEvent()...");
        int scrollY = getScrollY();//顶部已滑出的距离
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if(scrollY == 0||scrollY < 0){
                    AppContext.newInstance().setDispatch(false);
                }else{
                    AppContext.newInstance().setDispatch(super.onTouchEvent(ev));
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }

}
