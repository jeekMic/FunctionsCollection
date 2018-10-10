package com.example.administrator.testactivity.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.example.administrator.testactivity.MainActivity

import com.example.administrator.testactivity.R

class EventActivity : AppCompatActivity() {
    val TAG:String="EventActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                Log.e(TAG,"=====onTouchEvent=====ACTION_DOWN")
//                return true
            }
            MotionEvent.ACTION_MOVE->{
                Log.e(TAG,"=====onTouchEvent=====ACTION_MOVE")
            }
            MotionEvent.ACTION_UP->{
                Log.e(TAG,"=====onTouchEvent=====ACTION_UP")
            }
            MotionEvent.ACTION_CANCEL->{
                Log.e(TAG,"=====onTouchEvent=====ACTION_CANCEL")
            }
            else->{
                Log.e(TAG,"=====onTouchEvent=====default")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN->{
                Log.e(TAG,"=====dispatchTouchEvent=====ACTION_DOWN")

//                return true
            }
            MotionEvent.ACTION_MOVE->{
                Log.e(TAG,"=====dispatchTouchEvent=====ACTION_MOVE")
            }
            MotionEvent.ACTION_UP->{
                Log.e(TAG,"=====dispatchTouchEvent=====ACTION_UP")
            }
            MotionEvent.ACTION_CANCEL->{
                Log.e(TAG,"=====dispatchTouchEvent=====ACTION_CANCEL")
            }
            else->{
                Log.e(TAG,"=====dispatchTouchEvent=====default")
            }
        }
        return false
    }

}
