package com.example.administrator.testactivity.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

class MyViewGroup:FrameLayout{
//    E/EventActivity: =====dispatchTouchEvent=====ACTION_DOWN
//    E/MyViewGroup: dispatchTouchEvent: ======ACTION_DOWN
//                   onInterceptTouchEvent: ======ACTION_DOWN
//    E/MyView: dispatchTouchEvent: ======ACTION_DOWN
//                  onTouchEvent: ======ACTION_DOWN
//    E/MyViewGroup: onTouchEvent: ======ACTION_DOWN
//    E/EventActivity: =====onTouchEvent=====ACTION_DOWN
//    E/EventActivity: =====dispatchTouchEvent=====ACTION_UP
//                      =====onTouchEvent=====ACTION_UP

    constructor(context:Context):super(context){}
    constructor(context: Context,attr: AttributeSet?):super(context,attr)
    constructor(context: Context,attr:AttributeSet?,defStyleAttr: Int):super(context,attr,defStyleAttr){}
    constructor(context: Context,attr: AttributeSet?,defStyleAttr: Int,defStyleRes: Int):super(context,attr,defStyleAttr,defStyleRes){}

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_UP->Log.e(TAG,"onInterceptTouchEvent: ======ACTION_UP")
            MotionEvent.ACTION_DOWN->Log.e(TAG,"onInterceptTouchEvent: ======ACTION_DOWN")
            MotionEvent.ACTION_MOVE->Log.e(TAG,"onInterceptTouchEvent: ======ACTION_MOVE")
            MotionEvent.ACTION_CANCEL->Log.e(TAG, "onInterceptTouchEvent: ======ACTION_CANCEL")
            else->Log.e(TAG,"dispatchTouchEvent: ======ACTION_default")
        }
        //当返回true的时候代表自己去处理，把事件传递给自己
        return true
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN -> Log.e(TAG,"dispatchTouchEvent: ======ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> Log.e(TAG,"dispatchTouchEvent: ======ACTION_MOVE")
            MotionEvent.ACTION_UP -> Log.e(TAG,"dispatchTouchEvent: ======ACTION_UP")
            MotionEvent.ACTION_CANCEL -> Log.e(TAG,"dispatchTouchEvent: ======ACTION_CANCEL")
            else -> Log.e(TAG,"dispatchTouchEvent: ======ACTION_default")
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_MOVE -> Log.e(TAG,"onTouchEvent: ======ACTION_MOVE")
            MotionEvent.ACTION_DOWN -> Log.e(TAG,"onTouchEvent: ======ACTION_DOWN")
            MotionEvent.ACTION_UP -> Log.e(TAG,"onTouchEvent: ======ACTION_UP")
            MotionEvent.ACTION_CANCEL -> Log.e(TAG,"onTouchEvent: ======ACTION_CANCEL")
            else->Log.e(TAG,"onTouchEvent: ======ACTION_CANCEL")
        }
        return super.onTouchEvent(event)
    }
    companion object {
        private val TAG = "MyViewGroup"
    }
}