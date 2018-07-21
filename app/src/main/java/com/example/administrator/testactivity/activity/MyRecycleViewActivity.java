package com.example.administrator.testactivity.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.testactivity.R;
import com.example.administrator.testactivity.adapter.RvAdapterMy;
import com.example.administrator.testactivity.custom.CustomDividerItem;
import com.example.administrator.testactivity.custom.MyItemTouchCallBack;
import com.example.administrator.testactivity.custom.NofityItem;
import com.example.administrator.testactivity.imple.ItemTouchMoveListener;
import com.example.administrator.testactivity.imple.RecycViewOnItemClicked;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyRecycleViewActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener, ItemTouchMoveListener{
    /**
     * Recycview是一款比listview更加强大的滑动控件
     * 但是刚开始使用RecycleView的时候可能会出现有些方便不清楚的情况
     * 这里我们需要深入的研究下
     */
    private RecyclerView rv_1;
    private List<String> names;
    private RvAdapterMy adapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager  gridLayoutManager;
    private RecycViewOnItemClicked listener;
    private ArrayAdapter arrayAdapter;
    private Spinner spinner;
    private ItemTouchHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycle_view);
        initView();//初始化view
        initData();//初始化数据
        initLogic();//初始化逻辑
    }

    private void initLogic() {
        rv_1.addItemDecoration(new CustomDividerItem(LinearLayoutManager.VERTICAL,10));
        adapter = new RvAdapterMy(this,names,listener);
        //线性布局,将VERTICAL换成HORIZONTAL就变成水平
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        gridLayoutManager = new GridLayoutManager(this,3);
        rv_1.setLayoutManager(linearLayoutManager);
        rv_1.setAdapter(adapter);

        arrayAdapter = ArrayAdapter.createFromResource(this,R.array.effects,android.R.layout.simple_expandable_list_item_1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);



        //动画效果
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        rv_1.setLayoutAnimation(animation);

        //左右删除拖拽的效果
        helper = new ItemTouchHelper(new MyItemTouchCallBack(this,names));
        helper.attachToRecyclerView(rv_1);

    }

    private void initData() {
        names = new ArrayList<>();
        for (int i=0;i<=100;i++){
            names.add("张三"+i);
        }
    }

    private void initView() {
        rv_1 = findViewById(R.id.rv_1);
        spinner = findViewById(R.id.sp_select);

        /**
         * 监听事件
         */
        listener = new RecycViewOnItemClicked() {
            @Override
            public void onitemClick(View view, int position) {
                Toast.makeText(MyRecycleViewActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        };
    }


    //spinner的点击事件
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        runLayoutAnimation(rv_1);
    }
    //没有选中任何选项
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(names, fromPosition, toPosition);
        adapter.notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        names.remove(position);
        adapter.notifyItemRemoved(position);
        return true;
    }

}
