package me.hy.jetpackmvvm.ext.view

import android.util.Size
import android.view.ViewGroup
import android.widget.ImageView
import com.blankj.utilcode.util.ScreenUtils
import me.hy.jetpackmvvm.ext.util.dp2px

/**
 * 还原图片自适应大小
 */
fun ImageView.restoreImageViewDefaultSize() {
    val imgLP = this.layoutParams
    imgLP.width = ViewGroup.LayoutParams.WRAP_CONTENT
    imgLP.height = ViewGroup.LayoutParams.WRAP_CONTENT
    this.layoutParams = imgLP
}

/**
 * 等比缩放
 */
fun ImageView.resizeImageView(margin:Int=0){
    val maxHeight = 250.dp2px()
    val screenWidth = ScreenUtils.getScreenWidth()
    val imgLP = this.layoutParams
    val maxWidth = screenWidth - margin
    val minRatio = (maxWidth.toFloat() / this.width.toFloat()).coerceAtMost((maxHeight.toFloat() / this.height.toFloat()))
    imgLP.width = (this.width * minRatio).toInt()
    imgLP.height = (this.height * minRatio).toInt()
    this.layoutParams = imgLP
}

///**
// * 等比缩放
// * @param maxWidthRatio 最大宽度占屏幕宽度多少
// */
//fun ImageView.resizeImageView(maxWidthRatio:Float,imageContent:ImageAttachment): Size {
//    val maxHeight = 250.dp2px()
//    val screenWidth = ScreenUtils.getScreenWidth()
//    val imgLP = this.layoutParams
//    val maxWidth = screenWidth * maxWidthRatio
//    val minRatio = (maxWidth.toFloat() / imageContent.width.toFloat()).coerceAtMost((maxHeight.toFloat() / imageContent.height.toFloat()))
//    imgLP.width = (imageContent.width * minRatio).toInt()
//    imgLP.height = (imageContent.height * minRatio).toInt()
//    this.layoutParams = imgLP
//    return Size(imgLP.width,imgLP.height)
//}
