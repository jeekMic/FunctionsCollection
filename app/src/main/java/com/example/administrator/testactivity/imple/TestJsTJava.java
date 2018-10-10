package com.example.administrator.testactivity.imple;

import android.util.Log;
import android.webkit.JavascriptInterface;
public class TestJsTJava {
        private MessageSend send;
        private String TAG = "TestJsTJava";

    public TestJsTJava(MessageSend send) {
        this.send = send;
    }

    @JavascriptInterface
        public  void  setValue(String str){
        //注意这里是在子线程里面进行接收的，所以如果这里写在activity中也是不能直接操作UI的
            Log.d(TAG, "setValue: "+str);
            send.getStrings("setValue: "+str);
        }
}
