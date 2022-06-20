package me.hy.jetpackmvvm.ext.view

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import com.blankj.utilcode.util.ClickUtils
import org.jetbrains.annotations.NotNull

//View扩大点击事件范围默认大小
const val EXPAND_DEFAULT_SIZE = 30

/**
 * 设置view显示
 */
fun View.visible() {
    visibility = View.VISIBLE
}


/**
 * 设置view占位隐藏
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * 根据条件设置view显示隐藏 为true 显示，为false 隐藏
 */
fun View.visibleOrGone(flag:Boolean) {
    visibility = if(flag){
        View.VISIBLE
    }else{
        View.GONE
    }
}

/**
 * 根据条件设置view显示隐藏 为true 显示，为false 隐藏
 */
fun View.visibleOrInvisible(flag:Boolean) {
    visibility = if(flag){
        View.VISIBLE
    }else{
        View.INVISIBLE
    }
}

/**
 * 设置view隐藏
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * 显示view，带有渐显动画效果。
 *
 * @param duration 毫秒，动画持续时长，默认500毫秒。
 */
fun View?.visibleAlphaAnimation(duration: Long = 500L) {
    this?.visibility = View.VISIBLE
    this?.startAnimation(AlphaAnimation(0f, 1f).apply {
        this.duration = duration
        fillAfter = true
    })
}

/**
 * 隐藏view，带有渐隐动画效果。
 *
 * @param duration 毫秒，动画持续时长，默认500毫秒。
 */
fun View?.goneAlphaAnimation(duration: Long = 500L) {
    this?.visibility = View.GONE
    this?.startAnimation(AlphaAnimation(1f, 0f).apply {
        this.duration = duration
        fillAfter = true
    })
}

/**
 * 占位隐藏view，带有渐隐动画效果。
 *
 * @param duration 毫秒，动画持续时长，默认500毫秒。
 */
fun View?.invisibleAlphaAnimation(duration: Long = 500L) {
    this?.visibility = View.INVISIBLE
    this?.startAnimation(AlphaAnimation(1f, 0f).apply {
        this.duration = duration
        fillAfter = true
    })
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

/**
 * 获取当前View在屏幕上的位置信息
 *
 * @return
 */
fun View.getRectOnScreen(): RectF {
    // ---宽高---
    val width = this.measuredWidth
    val height = this.measuredHeight
    // ---location---
    // 获取在屏幕上的位置
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    return RectF(
        location[0].toFloat(),
        location[1].toFloat(),
        (width + location[0]).toFloat(),
        (height + location[1]).toFloat()
    )
}

/**
 * 当View部分可见或者全部不可见时返回false
 * @link https://www.jianshu.com/p/b1494e263900
 */
fun View.isOnScreen(): Boolean {
    val rect = Rect()
    this.getGlobalVisibleRect(rect)
    return rect.top == 0
}

/**
 * 检测View是否被可见,被遮挡
 */
fun View.isShade(): Boolean {

    if (this.visibility != View.VISIBLE) {
        return true
    }

    var currentView = this as View

    val currentViewRect = Rect()
    val isCoverd = currentView.getGlobalVisibleRect(currentViewRect)
    // 如果在屏幕外肯定是不可见的
    if(!isCoverd){
        return true
    }

    if (currentViewRect.width() * currentViewRect.height() <= this.measuredHeight * this.measuredWidth / 2) {
        // 如果移出屏幕的面积大于 50% 则认为被遮罩了
        return true
    }
    // 记录下被移出屏幕外的面积
    val outScreenArea = this.measuredHeight * this.measuredWidth - currentViewRect.width() * currentViewRect.height()
    // 循环查找父布局及兄弟布局
    while (currentView.parent is ViewGroup) {
        val currentParent = currentView.parent as ViewGroup

        if (currentParent.visibility != View.VISIBLE)
            return true

        val start = indexOfViewInParent(currentView, currentParent)
        for (i in start + 1 until currentParent.childCount) {
            val otherView = currentParent.getChildAt(i)
            // 这里主要是为了排除 invisible 属性标记的 view
            if (otherView.visibility != View.VISIBLE) {
                break
            }
            val viewRect = Rect()
            this.getGlobalVisibleRect(viewRect)
            val otherViewRect = Rect()
            otherView.getGlobalVisibleRect(otherViewRect)
            // 这个方法用来检测两个区域是否重叠，并且如果重叠的话
            // 就将当前 Rect 修改为重叠的区域
            if (otherViewRect.intersect(viewRect)) {
                if ((outScreenArea + otherViewRect.width() * otherViewRect.height())
                    >= viewRect.width() * viewRect.height() / 2)
                // 表示相交区域 + 屏幕外的区域 大于 50% 则也认为被遮罩了
                    return true
            }
        }
        currentView = currentParent
    }
    return false
}

private fun indexOfViewInParent(view: View, parent: ViewGroup): Int {
    var index = 0
    // 查找出应该从第几个子 view 开始 参考事件分发机制从用户可以见到的最上层开始分发，
    // 最上层的子 view，index 值总是更大
    while (index < parent.childCount) {
        if (parent.getChildAt(index) == view)
            break
        index++
    }
    return index
}

//扩大View点击范围，默认30px
fun View.expandTouchArea() {
    ClickUtils.expandClickArea(this,::EXPAND_DEFAULT_SIZE.get())
}

//恢复点击区域
fun View.restoreViewTouchDelegate() {
    (this.parent as View).post {
        val bounds = Rect()
        bounds.setEmpty()
        val touchDelegate = TouchDelegate(bounds, this)
        if (this.parent is View) {
            (this.parent as View).touchDelegate = touchDelegate
        }
    }
}

/**
 * 点击后对视图缩放
 */
fun View.applyPressedViewScale(){
    ClickUtils.applyPressedViewScale(this)
}

/**
 * 点击后对背景加深
 */
fun View.applyPressedViewDark() {
    ClickUtils.applyPressedBgDark(this)
}



/**
 * 将view转为bitmap
 */
@Deprecated("use View.drawToBitmap()")
fun View.toBitmap(scale: Float = 1f, config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap? {
    if (this is ImageView) {
        if (drawable is BitmapDrawable) return (drawable as BitmapDrawable).bitmap
    }
    this.clearFocus()
    val bitmap = createBitmapSafely(
        (width * scale).toInt(),
        (height * scale).toInt(),
        config,
        1
    )
    if (bitmap != null) {
        Canvas().run {
            setBitmap(bitmap)
            save()
            drawColor(Color.WHITE)
            scale(scale, scale)
            this@toBitmap.draw(this)
            restore()
            setBitmap(null)
        }
    }
    return bitmap
}

fun createBitmapSafely(width: Int, height: Int, config: Bitmap.Config, retryCount: Int): Bitmap? {
    try {
        return Bitmap.createBitmap(width, height, config)
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
        if (retryCount > 0) {
            System.gc()
            return createBitmapSafely(width, height, config, retryCount - 1)
        }
        return null
    }
}





fun Any?.notNull(notNullAction:(value:Any) ->Unit,nullAction1:() ->Unit){
    if(this!=null){
        notNullAction.invoke(this)
    }else{
        nullAction1.invoke()
    }
}
