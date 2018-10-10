package com.example.administrator.testactivity.custom

import android.annotation.SuppressLint

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
@SuppressLint("AppCompatCustomView")
class MyView: TextView{

    constructor(context:Context):super(context){
        init()
    }

    constructor(context:Context, attrs:AttributeSet?):super(context,attrs){
        init()
    }
    constructor(context: Context,attrs: AttributeSet?,defStyleAttr:Int):super(context,attrs, defStyleAttr){
        init()
    }
    private fun init() {
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> Log.e(TAG, "dispatchTouchEvent: ======ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> Log.e(TAG, "dispatchTouchEvent: ======ACTION_MOVE")
            MotionEvent.ACTION_UP -> Log.e(TAG, "dispatchTouchEvent: ======ACTION_UP")
            MotionEvent.ACTION_CANCEL -> Log.e(TAG, "dispatchTouchEvent: ======ACTION_CANCEL")
            else -> Log.e(TAG,"onTouchEvent: ======default")
        }
        return super.dispatchTouchEvent(event)
    }

    override  fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> Log.e(TAG, "onTouchEvent: ======ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> Log.e(TAG, "onTouchEvent: ======ACTION_MOVE")
            MotionEvent.ACTION_UP -> Log.e(TAG, "onTouchEvent: ======ACTION_UP")
            MotionEvent.ACTION_CANCEL -> Log.e(TAG, "onTouchEvent: ======ACTION_CANCEL")
            else -> Log.e(TAG,"onTouchEvent: ======default")
        }
        return super.onTouchEvent(event)
    }
    companion object {
        private val TAG = "MyView"
    }
    //object用于修饰一个静态的类，而companion object用于修饰一个静态的方法
    // val 表示的是一个只读的数据 就像java里面的final这个变量 var表示的是可变变量
}
