package com.example.administrator.testactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.example.administrator.testactivity.activity.EventActivity;
import com.example.administrator.testactivity.activity.JavaScriptActivity;
import com.example.administrator.testactivity.activity.MyCustomViewActivity;
import com.example.administrator.testactivity.activity.MyDownloadThread;
import com.example.administrator.testactivity.activity.MyRecycleViewActivity;
import com.example.administrator.testactivity.activity.RxJavaActivity;
import com.example.administrator.testactivity.adapter.RvAdapter;
import com.example.administrator.testactivity.imple.RecycViewOnItemClicked;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hb
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView  rv_main;
    private List<String>  functions;
    private RvAdapter mRvAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecycViewOnItemClicked itemClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化view
        initData();//初始化数据
        initlogic();//初始化逻辑
    }

    /**
     * 初始化相关的逻辑
     */
    private void initlogic() {
        mRvAdapter = new RvAdapter(functions,this, itemClicked);

        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_main.setLayoutManager(linearLayoutManager);
        rv_main.setAdapter(mRvAdapter);
    }

    /**
     * 初始相关数据
     */
    private void initData() {
        functions = new ArrayList<>();
        functions.add("recycleview的相关使用");
        functions.add("自定义View的逐渐剖析");
        functions.add("断点续传");
        functions.add("混合开发");
        functions.add("RxJava");
        functions.add("事件传递");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
        functions.add("未知的相关使用");
    }

    /**
     * 初始view
     */
    private void initView() {
        rv_main = findViewById(R.id.rv_main);

        itemClicked = new RecycViewOnItemClicked() {
        @Override
        public void onitemClick(View view, int position)
        {
            //点击事件
            Toast.makeText(MainActivity.this, "您点击了"+position, Toast.LENGTH_SHORT).show();
            switch (position){
                case 0:
                   startActivity(new Intent(MainActivity.this, MyRecycleViewActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, MyCustomViewActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, MyDownloadThread.class));
                    break;
                case 3:
                    startActivity(new Intent(MainActivity.this, JavaScriptActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(MainActivity.this, EventActivity.class));
                    break;
                default:
                    break;
            }
        }

        };
    }
}
