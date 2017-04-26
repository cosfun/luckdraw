package com.cosfun.luckdraw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/26.
 */

public class LuckDraw extends View {
    private Bitmap mDrawable;
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
        canvas.drawBitmap(mDrawable,0,0,null);
    }
}
