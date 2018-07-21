package com.example.administrator.testactivity.custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


public class CustomDividerItem extends RecyclerView.ItemDecoration{
    private int dividerHeight;
    //处于水平状态
    private static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    // 处于垂直状态
    private static final int VERTICAL = LinearLayoutManager.VERTICAL;
    //方向
    private int orientation;
    // 边距大小
    private final int decoration;


    public CustomDividerItem(int orientation, int decoration) {
        this.orientation = orientation;
        this.decoration = decoration;
    }

    /**
     * 这个方法每个item都会调用一次
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //整个recycleview最后一个item的position, getItemCout数目比实际的item个数多一个
        final int lastPosition = state.getItemCount() - 1;
        //获取当前需要布局的item
        final int current = parent.getChildLayoutPosition(view);
        Log.e("0000", "0000---->" + current);
        Log.e("0000", "0000state.getItemCount()---->" + state.getItemCount());
        Log.e("0000", "0000getTargetScrollPosition---->" + state.getTargetScrollPosition());
        Log.e("0000", "0000state---->" + state.toString());
        if(current == -1) {
            return;
        }
        if (layoutManager instanceof LinearLayoutManager && !(layoutManager instanceof GridLayoutManager)){
            //垂直
            if (orientation == LinearLayoutManager.VERTICAL){
                    outRect.set(decoration,decoration,decoration,decoration);

            }else{//水平
                if (current == lastPosition){
                    outRect.set(0,0,0,0);
                }else{
                    outRect.set(0,0,decoration,0);
                }
            }
        }

    }
}
