package com.example.capston;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Administrator on 2017/10/18.
 */

public class BitmapDescriptorFactory {
    public BitmapDescriptorFactory() {
    }

    //根据VIew转换为 Drawable
    public Drawable fromView(View view) {
        if(view == null) {
            return null;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.buildDrawingCache();
            Bitmap oldBitmap = view.getDrawingCache();
            Bitmap bitmap = createBitmap(oldBitmap);

            if(oldBitmap != null) {
                oldBitmap.recycle();
            }

            view.destroyDrawingCache();
            return createDrawble(bitmap,view.getContext());
        }
    }

    //根据bitmap转换为Drawable
    private Drawable createDrawble(Bitmap bitmap, Context context){
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        return drawable;
    }
    //根据创建Bitmap
    private Bitmap createBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
        return newBitmap;
    }
}
