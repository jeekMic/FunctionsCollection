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

public class ProgressView extends View{
    /*扇形变换的步长,也就是角度*/
    private int sweepStep = 10;
    //外边框距离扇形的距离
    private int padding = 40;
    //边框的颜色
    private int circleColor = Color.GRAY;
    //扇形的颜色
    private int sweepColor = Color.BLUE;
    //起始的角度
    private int startAngle = 90;
    //外边框的粗细程度
    private int stroke = 20;
    //扫过的角度
    private int sweepAngle = 0;
    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 200;
    private RectF rectF;
    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);


    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        if(array!=null){
            sweepStep = array.getInteger(R.styleable.ProgressView_sweepStep,sweepStep);
            padding = array.getInteger(R.styleable.ProgressView_padding,padding);
            circleColor = array.getColor(R.styleable.ProgressView_circleColor,circleColor);
            sweepColor = array.getColor(R.styleable.ProgressView_sweepColor,sweepColor);
            startAngle = array.getInteger(R.styleable.ProgressView_startAngle,startAngle);
        }
        //最后必须执行recycle()进行释放资源
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint  = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        //绘制外层的圆框
        mPaint.setColor(circleColor);
        mPaint.setStrokeWidth(stroke);
        //设置为空心圆
        mPaint.setStyle(Paint.Style.STROKE);
        //开始绘制
        canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/2-15, mPaint);

        invalidate();//请求重新绘制view
        //绘制内部扇形
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(sweepColor);

        //RectF中的四个参数，分别对应内切圆的四个切点的坐标
        rectF =  new RectF(padding+stroke,padding+stroke, getWidth()-padding-stroke, getRight()-padding-stroke);
        canvas.drawArc(rectF,startAngle,sweepAngle,true,mPaint);
        sweepAngle += sweepStep;
        sweepAngle = sweepAngle>360?0:sweepAngle;
        drawText(canvas,String.valueOf((sweepAngle/360.0)*100));
        invalidate();//重新绘制view
    }

    private void drawText(Canvas canvas,String str) {
        if (str.length()>4){
            str = str.substring(0,4);
        }
        Paint paint  = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark,null));
        paint.setTextSize(100);


        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float descent = fontMetrics.descent;
        float ascent  = fontMetrics.ascent;
        float top  = fontMetrics.top;
        float bottom  = fontMetrics.bottom;
        float leading  = fontMetrics.leading;

        int baseline = (int)(rectF.centerY()-top/2-bottom/2);
        int textwidth = getTextWidth(paint,str+"%");
        //绘制文字 注意这里的x y 坐标是一个和左下角非常接近的坐标
        canvas.drawText(str+"%",getWidth()/2-textwidth/2,baseline,paint);
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
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int hmode = MeasureSpec.getMode(widthMeasureSpec);
        int wsize = MeasureSpec.getSize(widthMeasureSpec);
        int hsize = MeasureSpec.getSize(widthMeasureSpec);
        //判断高宽
        switch (wmode){
            case MeasureSpec.AT_MOST:  //layout=wrap_match
                //获取屏幕像素
                float density = getResources().getDisplayMetrics().density;
                wsize = (int)(DEFAULT_WIDTH*density);
                hsize = (int)(DEFAULT_HEIGHT*density);
                break;
            //当控件在xml中指定为match_parent或者有具体数值的宽高的时候
            case MeasureSpec.EXACTLY:
                wsize = hsize = Math.min(wsize,hsize);
                break;
            default:
                break;
        }
        //只要重写onMeasure()这个方法，就一定要调用这个方法
        setMeasuredDimension(wsize,hsize);
    }
}
