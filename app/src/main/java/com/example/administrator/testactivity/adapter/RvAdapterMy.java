package com.example.administrator.testactivity.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.testactivity.R;
import com.example.administrator.testactivity.custom.NofityItem;
import com.example.administrator.testactivity.imple.RecycViewOnItemClicked;

import java.util.List;

public class RvAdapterMy extends RecyclerView.Adapter<RvAdapterMy.ViewHolder> implements View.OnClickListener,NofityItem {
    private Context mContext;
    private List<String>  list;
    private RecycViewOnItemClicked  listen;

    public RvAdapterMy(Context mContext, List<String> list, RecycViewOnItemClicked listen) {
        this.mContext = mContext;
        this.list = list;
        this.listen = listen;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv1_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_1.setText(list.get(position));
        holder.iv_1.setImageResource(R.mipmap.ic_launcher);
        //给每一个view打一个标签
        holder.itemView.setTag(position);
        //itemview就是每一个item的布局layout
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * RecycleView没有具体的点击事件，需要我们自己定义接口
     * @param v
     */
    @Override
    public void onClick(View v) {
        listen.onitemClick(v, (Integer) v.getTag());
    }

    @Override
    public void change_move(int form_position, int toPosition) {

        notifyItemMoved(form_position, toPosition);
    }

    @Override
    public void change_remove(int position) {

        notifyItemRemoved(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_1;
        ImageView iv_1;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_item_1);
            iv_1 = itemView.findViewById(R.id.iv_item_1);
        }
    }
}
