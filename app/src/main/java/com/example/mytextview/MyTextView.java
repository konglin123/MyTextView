package com.example.mytextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyTextView extends View {
    private String myText;
    private int myTextSize=10;
    private int myTextColor=Color.BLACK;
    private Paint paint;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyTextView);

        myText=typedArray.getString(R.styleable.MyTextView_my_text);
        myTextSize=typedArray.getDimensionPixelSize(R.styleable.MyTextView_my_textSize,myTextSize);
        myTextColor=typedArray.getColor(R.styleable.MyTextView_my_textColor,myTextColor);

        typedArray.recycle();

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(myTextSize);
        paint.setColor(myTextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);


        if(widthMode==MeasureSpec.AT_MOST){//wrap_content
            Rect rect = new Rect();
            paint.getTextBounds(myText,0,myText.length(), rect);
            width= rect.width()+getPaddingLeft()+getPaddingRight();

        }else if(widthMode==MeasureSpec.EXACTLY){//match_parent  xxxdp

        }else if(widthMode==MeasureSpec.UNSPECIFIED){
            //scrollview嵌套listview
        }

        if(heightMode==MeasureSpec.AT_MOST){//wrap_content
            Rect rect = new Rect();
            paint.getTextBounds(myText,0,myText.length(), rect);
            height= rect.height()+getPaddingTop()+getPaddingBottom();

        }else if(heightMode==MeasureSpec.EXACTLY){//match_parent  xxxdp

        }else if(heightMode==MeasureSpec.UNSPECIFIED){
            //scrollview嵌套listview
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int dy= (int) ((fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom);
        int baseline=getHeight()/2+dy;
        canvas.drawText(myText,getPaddingLeft(),baseline,paint);
    }
}
