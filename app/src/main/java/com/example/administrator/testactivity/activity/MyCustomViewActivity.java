package com.example.administrator.testactivity.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.administrator.testactivity.R;
import com.example.administrator.testactivity.custom.HPaintView;
import com.example.administrator.testactivity.fragments.Framnet_1;
import com.example.administrator.testactivity.fragments.Framnet_2;
import com.example.administrator.testactivity.fragments.Framnet_3;
import com.example.administrator.testactivity.fragments.Framnet_4;

public class MyCustomViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button  bt_1, bt_2, bt_3, bt_4;
    private FrameLayout fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_view);
        initView();
        changeFragment(new Framnet_1());
        initlogic();
    }

    /**
     * 初始化数据界面
     */
    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content,fragment);
        transaction.commit();

    }

    /**
     * 初始化逻辑程序
     */
    private void initlogic() {
        bt_1.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_2.setOnClickListener(this);
    }

    /**
     * 初始化view
     */
    private void initView() {
        bt_1 = findViewById(R.id.bt_1);
        bt_4 = findViewById(R.id.bt_4);
        bt_3 = findViewById(R.id.bt_3);
        bt_2 = findViewById(R.id.bt_2);
        fl = findViewById(R.id.fl_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                changeFragment(new Framnet_1());
                break;
            case R.id.bt_2:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                changeFragment(new Framnet_2());
                break;
            case R.id.bt_3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                changeFragment(new Framnet_3());
                break;
            case R.id.bt_4:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                changeFragment(new Framnet_4());
                break;
            default:
                Toast.makeText(this, "none", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
