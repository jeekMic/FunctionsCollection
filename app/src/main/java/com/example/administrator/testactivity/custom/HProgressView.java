package com.example.administrator.testactivity.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.testactivity.R;

public class HProgressView extends View {
    private int background_color;
    private int progressColor;
    private Paint mPaint;
    private Paint pre_Paint;
    private int default_background_color = Color.GRAY;
    private int default_progress_color = Color.GREEN;
    private int WRAP_WIDTH = 200;
    private int WRAP_HEIGHT = 30;
    private float progress_current = 20 ;

    public HProgressView(Context context) {
        this(context,null);
    }

    public HProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HProgressView);
        background_color = array.getColor(R.styleable.HProgressView_background_Color,default_background_color);
        progressColor = array.getColor(R.styleable.HProgressView_progress_Color,default_progress_color);
        //回收资源
        array.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int w_size= MeasureSpec.getSize(widthMeasureSpec);
        int h_size= MeasureSpec.getSize(heightMeasureSpec);

        //判断宽高 at_most是相当于wrap
        if (w_mode==MeasureSpec.AT_MOST&&h_mode==MeasureSpec.AT_MOST){
            setMeasuredDimension(WRAP_WIDTH,WRAP_HEIGHT);
        }else if (w_mode==MeasureSpec.AT_MOST){
            setMeasuredDimension(WRAP_WIDTH,h_size);
        }else if (h_mode==MeasureSpec.AT_MOST){
            setMeasuredDimension(w_size,WRAP_HEIGHT);
        }else {
            setMeasuredDimension(w_size/3,h_size/3);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制背景的圆角矩形
        draw_background(canvas);
        //绘制进度文本信息
        draw_progress_text(canvas);
        //刷新
        invalidate();
    }

    private void draw_progress_text(Canvas canvas) {
        pre_Paint = new Paint();
        pre_Paint.setAntiAlias(true);
        pre_Paint.setColor(Color.WHITE);
        pre_Paint.setTextSize(25.0f);
        String text = (int)progress_current+"%";

        int textWidth = getTextWidth(pre_Paint,text);
        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+pre_Paint.getTextSize()/4,pre_Paint);

    }
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }
    private void draw_background(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(background_color);
        //设置个新的长方形
        RectF oval3 = new RectF(10, 10, getWidth()-10, getHeight()-10);
        //第二个参数是x半径，第三个参数是y半径
        canvas.drawRoundRect(oval3, getHeight()/2, getHeight()/2, mPaint);

        mPaint.setColor(progressColor);
        float progress = ((getWidth()-20)/100)*progress_current;
        RectF ovals = new RectF(10, 10, getHeight()+progress, getHeight()-10);
        if (progress_current>100){
            progress_current = 0;
        }
        canvas.drawRoundRect(ovals,getHeight()/2,getHeight()/2, mPaint);
    }
    public void  setProgress(float progress){
        this.progress_current = progress;
    }
}
