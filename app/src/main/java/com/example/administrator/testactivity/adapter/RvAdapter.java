package com.example.administrator.testactivity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.testactivity.R;
import com.example.administrator.testactivity.imple.RecycViewOnItemClicked;
import com.example.administrator.testactivity.utils.Utils;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> implements View.OnClickListener {
    private List<String>  funcs;
    private Context mContext;
    private RecycViewOnItemClicked itemClicked;

    public RvAdapter(List<String> funcs, Context mContext, RecycViewOnItemClicked itemClicked) {
        this.funcs = funcs;
        this.mContext = mContext;
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * false表示不依附
         */
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_main_item,parent,false);
        view.setOnClickListener(this);
        ViewHolder  holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_main.setText(funcs.get(position));
        holder.tv_main.setBackgroundColor(Color.parseColor(Utils.getRandomColor()));
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return funcs.size();
    }

    @Override
    public void onClick(View v) {
        itemClicked.onitemClick(v, (Integer) v.getTag());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView  tv_main;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_main = itemView.findViewById(R.id.tv_main_item);
        }
    }
}
