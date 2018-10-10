package com.example.administrator.testactivity.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.testactivity.R;
import com.example.administrator.testactivity.imple.MessageSend;
import com.example.administrator.testactivity.imple.TestJsTJava;

@SuppressLint("SetJavaScriptEnabled")
public class JavaScriptActivity extends AppCompatActivity implements MessageSend,View.OnClickListener{
    private WebView webView;
    private TextView tv_info;
    private WebSettings setting;
    private Button  bt_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_script);
        initView();
        initlogic();
    }

    private void initlogic() {
        setting = webView.getSettings();
        //开启js和java之间的互动
        setting.setJavaScriptEnabled(true);
        //允许js的弹窗
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("file:///android_asset/test1.html");
        webView.addJavascriptInterface(new TestJsTJava(this),"android");
        //可以在android上弹出浏览器上的对话框
        webView.setWebChromeClient(new WebChromeClient());
    }

    private void initView() {
        webView = findViewById(R.id.wv_view);
        tv_info = findViewById(R.id.tv_info);
        bt_send = findViewById(R.id.bt_send);
        bt_send.setOnClickListener(this);
    }

    @Override
    public void getStrings(String str) {
        tv_info.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_send:
                webView.loadUrl("javascript:toast()");
                break;
            default:
                    break;
        }
    }
}
