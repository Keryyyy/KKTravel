package me.hy.jetpackmvvm.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import me.hy.jetpackmvvm.base.bean.ImageSize
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2020/12/18
 *     desc  :
 *
 * </pre>
 */
fun Bitmap.getImageSize(): ImageSize? {
    val imageSize = ImageSize()
    if (this.isRecycled) {
        return null
    }
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val byteTmp = baos.toByteArray()
    try {
        baos.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    val bitmapOptions = BitmapFactory.Options()
    bitmapOptions.inJustDecodeBounds = true
    BitmapFactory.decodeByteArray(byteTmp, 0, byteTmp.size, bitmapOptions)
    val outWidth = bitmapOptions.outWidth
    val outHeight = bitmapOptions.outHeight
    val maxWidth = 400
    val maxHeight = 400
    val minWidth = 150
    val minHeight = 150
    if (outWidth / maxWidth > outHeight / maxHeight) { //
        if (outWidth >= maxWidth) { //
            imageSize.width = (maxWidth)
            imageSize.height = (outHeight * maxWidth / outWidth)
        } else {
            imageSize.width = (outWidth)
            imageSize.height = (outHeight)
        }
        if (outHeight < minHeight) {
            imageSize.width = (minHeight)
            val width = outWidth * minHeight / outHeight
            if (width > maxWidth) {
                imageSize.width = (maxWidth)
            } else {
                imageSize.width = (width)
            }
        }
    } else {
        if (outHeight >= maxHeight) {
            imageSize.height = (maxHeight)
            imageSize.height = (outWidth * maxHeight / outHeight)
        } else {
            imageSize.height = (outHeight)
            imageSize.height = (outWidth)
        }
        if (outWidth < minWidth) {
            imageSize.width = (minWidth)
            val height = outHeight * minWidth / outWidth
            if (height > maxHeight) {
                imageSize.height = (maxHeight)
            } else {
                imageSize.height = (height)
            }
        }
    }
    return imageSize
}