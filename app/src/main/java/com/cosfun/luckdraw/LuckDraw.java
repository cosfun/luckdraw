package com.cosfun.luckdraw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/4/26.
 */

public class LuckDraw extends View {
    private Bitmap mDrawable;
    private int mheight=0;
    private int mwidth=0;
    public LuckDraw(Context context) {
        super(context);
    }

    public LuckDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initParms(context,attrs);
    }

    public LuckDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParms(context,attrs);
    }
    private void initParms(Context context,AttributeSet attrs){
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LuckDraw,0,0);
        int n=typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int arr = typedArray.getIndex(i);
            switch (arr){
                case R.styleable.LuckDraw_src:
                    mDrawable=BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(arr,0));
                    break;
            }
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(getBitmap(mDrawable,mwidth,mheight),0,0,null);
        canvas.save();
        canvas.rotate(30);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mheight=h;
        mwidth=w;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int ws = MeasureSpec.getSize(widthMeasureSpec);
        int hs = MeasureSpec.getSize(heightMeasureSpec);
        int width=0;
        int height=0;
        if(wMode==MeasureSpec.EXACTLY){
            mwidth=ws;
            width=ws;
        }else{
            width = mDrawable.getWidth();
        }
        if(hMode==MeasureSpec.EXACTLY){
            mheight=hs;
            height=hs;
        }else{
            height=mDrawable.getHeight();
        }
        setMeasuredDimension(width,height);
    }
    private Bitmap getBitmap(Bitmap mp,int weight,int height){
        Matrix matrix = new Matrix();
        float scale = (float) weight/mp.getWidth();
        float scale2 = (float) height/mp.getHeight() ;
        matrix.postScale(scale, scale2);
        return Bitmap.createBitmap(mp, 0, 0, mp.getWidth(), mp.getHeight(), matrix, true);
    }
}
