package com.example.administrator.testactivity.custom;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.administrator.testactivity.imple.ItemTouchMoveListener;

import java.util.Collections;
import java.util.List;

public class MyItemTouchCallBack extends ItemTouchHelper.Callback {
    private ItemTouchMoveListener touchlistener;
    private List<String>  list;

    public MyItemTouchCallBack(ItemTouchMoveListener touchlistener, List<String> list) {
        this.touchlistener = touchlistener;
        this.list = list;

    }

    //CallBack回调监听时先调用，用来判断当前是什么动作 上下还是左右
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int flag = makeMovementFlags(dragFlag,swipeFlag);
        return flag;
    }

    //当移动的时候回调的拖拽方法
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType()!= target.getItemViewType()){
            return false;
        }
        touchlistener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }
    //当侧滑的时候调用这个方法
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        touchlistener.onItemRemove(viewHolder.getAdapterPosition());
    }

}
