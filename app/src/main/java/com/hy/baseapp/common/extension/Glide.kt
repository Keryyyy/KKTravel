package com.hy.baseapp.common.extension

import android.content.Context
import android.net.Uri
import android.util.Size
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.hy.baseapp.R
import com.hy.baseapp.base.event.appInstance
import com.hy.baseapp.common.utils.MaskTransformation
import com.hy.baseapp.common.utils.RoundedCornerCenterCrop
import me.hy.jetpackmvvm.ext.util.dp2px
import java.io.File

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2020/11/4
 *     desc  : Glide加载图片扩展类
 *
 * </pre>
 */



fun ImageView.display(url: String) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .transition(DrawableTransitionOptions().crossFade(500))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.display(file: File) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(file)
        .thumbnail(0.5f)
        .apply(options)
        .transition(DrawableTransitionOptions().crossFade(500))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.displayNoCache(url: String) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .transition(DrawableTransitionOptions().crossFade(500))
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .into(this)
}

fun ImageView.displayBg(url: String) {
    val options = RequestOptions().apply {
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}


fun ImageView.displayThumbnail(url: String) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(url)
        .thumbnail(0.5f)
        .apply(options)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}


fun ImageView.displayOriginalSize(url: String) {
    val options = RequestOptions().apply {
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .override(Target.SIZE_ORIGINAL)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.displayOriginalSize(url: String,corners: Int) {
    val options = RequestOptions().apply {
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(url)
        .thumbnail(0.5f)
        .apply(options)
        .override(Target.SIZE_ORIGINAL)
        .transform(RoundedCorners(corners.dp2px()))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.displayWithCache(url: String) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(url)
        .thumbnail(0.5f)
        .apply(options)
        .transition(DrawableTransitionOptions().crossFade()).into(this)
}
fun ImageView.displayWithCorner(url: String, corners:Int=8) {
    Glide.with(appInstance)
        .load(url)
        .placeholder(R.drawable.glide_holder)
        .transform(RoundedCorners(corners.dp2px()))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transition(DrawableTransitionOptions().crossFade(500)).into(this)
}

fun ImageView.displayWithCorner(resourceID: Int, corners:Int=8) {
    Glide.with(appInstance)
        .load(resourceID)
        .placeholder(R.drawable.glide_holder)
        .transform(RoundedCorners(corners.dp2px()))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transition(DrawableTransitionOptions().crossFade(500)).into(this)
}

fun ImageView.displayWithCornerNoFade(resourceID: Int, corners:Int=8) {
    Glide.with(appInstance)
        .load(resourceID)
        .placeholder(R.drawable.glide_holder)
        .transform(RoundedCorners(corners.dp2px()))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

/**
 * 自定义各个圆角
 */
fun ImageView.displayWithGranularCorners(url: String,
                                  topLeft:Float=0f, topRight:Float=0f, bottomRight:Float=0f, bottomLeft:Float=0f) {
    Glide.with(appInstance)
        .load(url)
        .placeholder(R.drawable.glide_holder)
        .transform(GranularRoundedCorners(topLeft, topRight, bottomRight, bottomLeft))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transition(DrawableTransitionOptions().crossFade(500)).into(this)
}


fun ImageView.displayCornersWithCenterCrop(url: String, corners:Int = 8) {
    Glide.with(appInstance)
        .load(url)
//        .thumbnail(0.3f)
        .placeholder(R.drawable.glide_holder)
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transform(CenterCrop(),RoundedCorners(corners.dp2px()))
        .transition(DrawableTransitionOptions.withCrossFade(500))
        .into(this)
}

fun ImageView.displayCornersWithCenterCrop(url: String, corners:Int = 8,width: Int,height: Int) {
    Glide.with(appInstance)
        .load(url)
        .placeholder(R.drawable.glide_holder)
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transform(CenterCrop(),RoundedCorners(corners.dp2px()))
        .override(width.dp2px(),height.dp2px())
        .into(this)
}

fun ImageView.displayCornersWithFitCenter(url: String, corners:Int =8) {
    Glide.with(appInstance)
        .load(url)
        .placeholder(R.drawable.glide_holder)
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transform(FitCenter(),RoundedCorners(corners.dp2px()))
        .transition(DrawableTransitionOptions().crossFade()).into(this)
}

fun ImageView.displayCornersWithCenterCrop(@DrawableRes @RawRes resourceID: Int, corners:Int) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.NONE)
    }

    Glide.with(appInstance)
        .load(resourceID)
        .apply(options)
        .transform(CenterCrop(),RoundedCorners(corners.dp2px()))
        .transition(DrawableTransitionOptions().crossFade()).into(this)
}


fun ImageView.displayFileWithCorners(path: String, corners: Int) {
    Glide.with(appInstance)
        .load(File(path))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transform(RoundedCornerCenterCrop(corners.dp2px())).into(this)
}


fun ImageView.displayUriWithCorners(path: String, corners: Int) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        apply(RequestOptions.bitmapTransform(RoundedCorners(corners.dp2px())))
        fitCenter()
    }

    Glide.with(appInstance)
        .load(Uri.fromFile(File(path)))
        .centerCrop()
        .apply(options)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transition(DrawableTransitionOptions().crossFade()).into(this)
}

fun ImageView.displayUriWithCorners(path: String, corners: Int,width: Int,height: Int) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        apply(RequestOptions.bitmapTransform(RoundedCorners(corners.dp2px())))
        fitCenter()
    }

    Glide.with(appInstance)
        .load(Uri.fromFile(File(path)))
        .centerCrop()
        .override(width,height)
        .apply(options)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transition(DrawableTransitionOptions().crossFade()).into(this)
}


fun ImageView.displayCircleTransition(url: String) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        transform(CircleCrop())
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transition(DrawableTransitionOptions().crossFade())
        .thumbnail()
        .into(this)
}

fun ImageView.displayCircleTransition(url: String, width: Int, height: Int) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        transform(CircleCrop())
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .override(width.dp2px(),height)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transition(DrawableTransitionOptions().crossFade())
        .thumbnail()
        .into(this)
}


fun ImageView.displayCircle(url: String) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        transform(CircleCrop())
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .thumbnail()
        .transition(DrawableTransitionOptions().crossFade(500))
        .into(this)
}



fun ImageView.displayCircle(url: String, width:Int, height:Int,thumbnail:Float=0.8f) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        transform(CircleCrop())
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .override(width.dp2px(),height.dp2px())
        .thumbnail(thumbnail)
        .transition(DrawableTransitionOptions().crossFade(500))
        .priority(Priority.IMMEDIATE)
        .into(this)
}


fun ImageView.displayCircle(url: String,thumbnail:Float=0.8f) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        transform(CircleCrop())
    }
    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .thumbnail(thumbnail)
        .dontAnimate()
        .priority(Priority.IMMEDIATE)
        .into(this)
}



fun ImageView.displayFileWithCircle(path: String) {
    Glide.with(appInstance)
        .load(path)
        .placeholder(R.drawable.glide_holder)
        .thumbnail(0.5f)
        .transform(CircleCrop())
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}


fun ImageView.displayFileWithCircle(file: File) {
    Glide.with(appInstance)
        .load(file)
        .thumbnail(0.5f)
        .transform(CircleCrop())
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.displayFile(path: String) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        fitCenter()
    }
    Glide.with(appInstance)
        .load(File(path))
        .apply(options)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .thumbnail(0.5f)
        .into(this)
}

fun ImageView.displayFile(file: File) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
    }
    Glide.with(appInstance)
        .load(file)
        .apply(options)
        .thumbnail(0.5f)
        .into(this)
}

fun ImageView.displayFileWithCorner(path: String,corners: Int= 8) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
    }
    Glide.with(appInstance)
        .load(File(path))
        .apply(options)
        .thumbnail(0.5f)
        .transform(RoundedCorners(corners.dp2px()))
        .into(this)
}


fun ImageView.displayFileFitCenter(path: String,corners: Int = 8) {
    Glide.with(appInstance)
        .load(File(path))
        .thumbnail(0.5f)
        .transform(FitCenter(),RoundedCorners(corners.dp2px()))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.displayFileFitCenter(path: String,size:Size,corners: Int = 8) {
    Glide.with(appInstance)
        .load(File(path))
        .transform(FitCenter(),RoundedCorners(corners.dp2px()))
        .override(size.width,size.height)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.displayFileCenterCrop(path: String,corners: Int = 8.dp2px()) {
    Glide.with(appInstance)
        .load(File(path))
        .transform(RoundedCornerCenterCrop(corners))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.displayWithCircle(@RawRes @DrawableRes resId: Int) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        transform(CircleCrop())
        fitCenter()
    }
    Glide.with(appInstance)
        .load(resId)
        .apply(options)
        .transition(DrawableTransitionOptions().crossFade())
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .thumbnail()
        .into(this)
}

fun ImageView.display(@DrawableRes @RawRes resourceID: Int) {
    Glide.with(appInstance)
        .load(resourceID)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.display(@DrawableRes @RawRes resourceID: Int,width: Int,height: Int) {
    Glide.with(appInstance)
        .load(resourceID)
        .fitCenter()
        .override(width,height)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}

fun ImageView.displayResourceCorner(@DrawableRes @RawRes resourceID: Int,corners: Int=8) {
    Glide.with(appInstance)
        .load(resourceID)
        .fitCenter()
        .transform(RoundedCorners(corners))
        .into(this)
}

/**
 * CenterCrop & Corner
 */
fun ImageView.displayResourceCenterCropCorner(@DrawableRes @RawRes resourceID: Int,corners: Int) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
    }

    Glide.with(appInstance)
        .load(resourceID)
        .apply(options)
        .transform(CenterCrop(),RoundedCorners(corners.dp2px()))
        .into(this)
}
/**
 * fitCenter & Corner
 */
fun ImageView.displayResourceFitCenterCorner(@DrawableRes @RawRes resourceID: Int,corners: Int) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
    }

    Glide.with(appInstance)
        .load(resourceID)
        .apply(options)
        .transform(FitCenter(),RoundedCorners(corners.dp2px()))
        .into(this)
}

//添加蒙版
fun ImageView.loadWithMask(
    context: Context,
    @DrawableRes @RawRes resourceID: Int,
    @DrawableRes @RawRes maskID: Int
) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        fitCenter()
    }

    Glide.with(appInstance)
        .load(resourceID)
        .transform(MaskTransformation(context, maskID))
        .apply(options)
        .into(this)
}

fun ImageView.loadWithMask(
    context: Context,
    url: String,
    @DrawableRes @RawRes maskID: Int
) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        fitCenter()
    }

    Glide.with(appInstance)
        .load(url)
        .transform(MaskTransformation(context, maskID))
        .apply(options)
        .into(this)
}



fun ImageView.displayWithMask(
    url: String,
    maskID: Int,
    corners: Int
) {
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
    }

    Glide.with(appInstance)
        .load(url)
        .apply(options)
        .transform(CenterCrop(),RoundedCorners(corners.dp2px()),
            MaskTransformation(
                appInstance,
                maskID
            )
        )
        .into(this)
}

//高斯模糊
fun ImageView.displayBlur(@DrawableRes @RawRes resourceID: Int){
    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        placeholder(R.drawable.glide_holder)
    }
    Glide.with(appInstance)
        .load(resourceID)
        .thumbnail(0.5f)
        .apply(options)
//        .transition(BlurHelper)
        .into(this)
}

fun ImageView.displayVideoCoverImage(url:String,corners: Int = 8){
    Glide.with(appInstance)
        .setDefaultRequestOptions(
            RequestOptions()
                .frame(1000000)
        )
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transform(CenterCrop(), RoundedCorners(corners.dp2px()))
        .transition(DrawableTransitionOptions().crossFade(500))
        .into(this)
}



fun ImageView.clearGlide(){
    Glide.with(appInstance)
        .clear(this)
}

