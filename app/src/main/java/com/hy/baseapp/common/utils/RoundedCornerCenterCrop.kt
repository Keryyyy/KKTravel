package com.hy.baseapp.common.utils

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2021/04/01
 *     desc  : 解决Glide设置CenterCrop后圆角不生效的问题
 *     先裁剪，再设置圆角
 *
 * </pre>
 */
class RoundedCornerCenterCrop(val radius: Int = 0) : BitmapTransformation() {
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight)
        return TransformationUtils.roundedCorners(pool, bitmap, radius)
    }
}