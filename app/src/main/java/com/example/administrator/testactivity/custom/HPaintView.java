package com.example.administrator.testactivity.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class HPaintView extends View{
    private Paint  circle;
    private Paint  rectcircle;
    public HPaintView(Context context) {
        super(context);
    }

    public HPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public HPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 这个重写的方法里面是对view的绘制,Canvas中文意思是画布，在这里可以绘制一些基本的图画
     * drawArc 绘制弧
     * drawBitMap 绘制位图
     * drawCircle 绘制圆形
     * drawOval 绘制椭圆
     * drawPath 绘制路径
     * drawPoint 绘制点
     * drawPoints 绘制多个点
     * drawRect 绘制矩形
     * drawRoundRect绘制圆角矩形
     * drawText 绘制字符串
     * drawTextOnPath 沿着路径绘制字符串
     * @param canvas
     *
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void drawText(Canvas canvas) {
        Paint paint  = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark,null));
        paint.setTextSize(100);
        //绘制文字 注意这里的x y 坐标是一个和左下角非常接近的坐标
        canvas.drawText("jEh",80,550,paint);


    }

    private void drawStyle(Canvas canvas) {

        Paint paint = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark,null));
        RectF rel = new RectF(100, 100, 300, 300);
        //实心圆弧 true表示使用圆心，而false表示不使用圆心
        canvas.drawArc(rel,0,270,true,paint);

        //设置空心style 同理，这的flase表示不使用圆心，而true表示的是使用圆心
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        RectF rel2 = new RectF(100, 700, 300, 900);
        canvas.drawArc(rel2,0, 270,false,paint);
    }

    private void drawCicleRect(Canvas canvas) {
        Paint paint  = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light,null));
        //绘制圆角矩形 注意参数问题
        canvas.drawRoundRect(100,900,300,1100,30,30,paint);

        //设置空心的style
        paint.setStyle(Paint.Style.STROKE);
        //设置空心边框的官渡
        paint.setStrokeWidth(20);
        canvas.drawRoundRect(400,900,600,1100,30,30,paint);
    }

    public void drawRect(Canvas canvas){
        rectcircle = new Paint();
        rectcircle.setAntiAlias(true);
        rectcircle.setColor(getResources().getColor(android.R.color.holo_green_light,null));
        /**
         *绘制矩形,这几个参数分别表示的是
         * 矩形的左边距离屏幕左边的距离
         * 矩形上边距离屏幕上边的距离
         * 矩形右边距离屏幕左边的距离
         * 矩形下边距离屏幕上边的距离
         * 从上的描述中可以得出这样的规律
         * right-left = 矩形的宽度
         * bottom-top = 矩形的高度
         * 最后一个参数是画笔
         */
        canvas.drawRect(600,100,1000,400,rectcircle);
        rectcircle.setStyle(Paint.Style.STROKE);
        rectcircle.setStrokeWidth(20);
        canvas.drawRect(600,450,1000,750,rectcircle);
        canvas.drawRect(650,500,950,700,rectcircle);
        canvas.drawRect(670,520,930,680,rectcircle);

    }

    public void drawCicle(Canvas canvas){
        circle = new Paint();
        //去掉锯齿
        circle.setAntiAlias(true);
        //设置颜色
        circle.setColor(getResources().getColor(android.R.color.holo_blue_light,null));
        //绘制普通圆形
        canvas.drawCircle(200,200,100, circle);

        //设置画出来的风格 这个设置成空心的
        circle.setStyle(Paint.Style.STROKE);
        //设置空心边框的官渡
        circle.setStrokeWidth(20);
        //绘制空心圆
        canvas.drawCircle(200,500,90,circle);

    }
}
