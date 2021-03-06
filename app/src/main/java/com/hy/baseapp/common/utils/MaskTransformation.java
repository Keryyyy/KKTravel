package com.hy.baseapp.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

import java.security.MessageDigest;


/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2020/11/5
 *     desc  :
 *
 * </pre>
 */

public class MaskTransformation implements Transformation<Bitmap> {

    private static final Paint sMaskingPaint = new Paint();
    private final Context mContext;
    private final BitmapPool mBitmapPool;
    private final int mMaskId;

    static {
        sMaskingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//        sMaskingPaint.setAlpha(0);
    }

    /**
     * @param maskId If you change the mask file, please also rename the mask file, or Glide will get
     * the cache with the old mask. Because getId() return the same values if using the
     * same make file name. If you have a good idea please tell us, thanks.
     */
    public MaskTransformation(Context context, int maskId) {
        this(context, Glide.get(context).getBitmapPool(), maskId);
    }

    public MaskTransformation(Context context, BitmapPool pool, int maskId) {
        mBitmapPool = pool;
        mContext = context.getApplicationContext();
        mMaskId = maskId;
    }

    @NonNull
    @Override
    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();

        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap result = mBitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }

        Drawable mask = getMaskDrawable(mContext, mMaskId);

        Canvas canvas = new Canvas(result);
        mask.setBounds(0, 0, width, height);
        mask.draw(canvas);
        canvas.drawBitmap(source, 0, 0, sMaskingPaint);

        return BitmapResource.obtain(result, mBitmapPool);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable getMaskDrawable(Context context, int maskId) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.getDrawable(maskId);
        } else {
            drawable = context.getResources().getDrawable(maskId);
        }

        if (drawable == null) {
            throw new IllegalArgumentException("maskId is invalid");
        }

        return drawable;
    }
}