package com.leaguehelper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.leaguehelper.AppContext;

/**
 * Created by zs on 15/8/27.
 * <p>
 * Description
 */
public class LinearLayoutView extends LinearLayout implements GestureDetector.OnGestureListener {
    private static final String TAG = LinearLayoutView.class.getSimpleName();
    private GestureDetector detector;
    public LinearLayoutView(Context context) {
        super(context);
        if(detector == null){
            detector = new GestureDetector(context,this);
        }
    }

    public LinearLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(detector == null){
            detector = new GestureDetector(context,this);
        }
    }

    public LinearLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(detector == null){
            detector = new GestureDetector(context,this);
        }
    }

    /**
     * 如果事件分发返回true,表示改事件在本层不再进行分发且已经在事件分发自身中被消费了.至此,事件已经完结.
     *
     * 如果事件分发返回 false,表明事件在本层不再继续进行分发,并交由上层控件的onTouchEvent方法进行消费,
     * 当然了,如果本层控件已经是Activity,那么事件将被系统消费或处理.
     *
     * 如果事件分发返回系统默认的 super.dispatchTouchEvent(ev),事件将分发给本层的事件拦截onInterceptTouchEvent 方法进行处理.
     *
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!AppContext.newInstance().isDispatch()){
            if(detector != null){
                detector.onTouchEvent(ev);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 如果 onInterceptTouchEvent 返回 true,则表示将事件进行拦截,并将拦截到的事件交由本层控件 的 onTouchEvent 进行处理.
     *
     * 如果返回结果是false,则表示不对事件进行拦截,事件得以成功分发到子View,并由子View的dispatchTouchEvent进行处理.
     *
     * 如果返回super.onInterceptTouchEvent(ev),事件默认不会被拦截,交由子View的dispatchTouchEvent进行处理.
     *
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG,"onInterceptTouchEvent()...");
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 如果onTouchEvent返回true,表示onTouchEvent处理完事件后消费了此次事件.此时事件终结,将不会进行后续的冒泡.
     *
     * 如果onTouchEvent返回false,事件在onTouchEvent中处理后继续向上层View冒泡,且有上层View的onTouchEvent进行处理.
     *
     * 如果返回super.onTouchEvent(ev),则默认处理的逻辑和返回false时相同.
     *
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"onTouchEvent()...");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll()...");

        int deltaY = 0;
        try {
            deltaY = (int) (e2.getY() - e1.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if(Math.abs(deltaY)>0){
            offsetTopAndBottom(deltaY);
//        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
