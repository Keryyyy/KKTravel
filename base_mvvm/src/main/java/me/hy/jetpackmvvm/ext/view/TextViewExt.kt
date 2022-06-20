package me.hy.jetpackmvvm.ext.view

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2021/1/13
 *     desc  :
 *
 * </pre>
 */


/**
 * 设置渐变色(从左到右)
 */
fun TextView.setGradient(startColor: String, endColor: String) {
    val shader = LinearGradient(
        0f, 0f, paint.textSize * text.length, 0f,
        Color.parseColor(startColor), Color.parseColor(endColor), Shader.TileMode.CLAMP
    )
    this.paint.shader = shader
}

/**
 * 设置渐变色(从上到下)
 */
fun TextView.setGradientHeight(startColor: String, endColor: String) {
    val shader = LinearGradient(
        0f, 0f, 0f, this.height.toFloat(),
        Color.parseColor(startColor), Color.parseColor(endColor), Shader.TileMode.CLAMP
    )
    this.paint.shader = shader
}

/**
 * 添加下划线
 */
fun TextView.addUnderLine() {
    this.paint.flags = Paint.UNDERLINE_TEXT_FLAG
}

/**
 * 添加删除线
 */
fun TextView.addStrikeThroughLine() {
    this.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
}


/**
 * 设置TextView图标
 *
 * @param drawable     图标
 * @param iconWidth    图标宽dp：默认自动根据图标大小
 * @param iconHeight   图标高dp：默认自动根据图标大小
 * @param direction    图标方向，0左 1上 2右 3下 默认图标位于左侧0
 */
fun TextView.setDrawable(
    drawable: Drawable?,
    iconWidth: Float? = null,
    iconHeight: Float? = null,
    direction: Int = 0,
    leftPadding:Int? = 0,
    rightPadding:Int? = 0
    ) {
    if (iconWidth != null && iconHeight != null) {
        //第一个0是距左边距离，第二个0是距上边距离，iconWidth、iconHeight 分别是长宽
        drawable?.setBounds(leftPadding!!, rightPadding!!, ConvertUtils.dp2px(iconWidth), ConvertUtils.dp2px(iconHeight))
    }
    when (direction) {
        0 -> setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        1 -> setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
        2 -> setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        3 -> setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable)
        else -> throw NoSuchMethodError()
    }
}

/**
 * 设置TextView图标
 *
 * @param lDrawable     左边图标
 * @param rDrawable     右边图标
 * @param lIconWidth    图标宽dp：默认自动根据图标大小
 * @param lIconHeight   图标高dp：默认自动根据图标大小
 * @param rIconWidth    图标宽dp：默认自动根据图标大小
 * @param rIconHeight   图标高dp：默认自动根据图标大小
 */
fun TextView.setDrawables(
    lDrawable: Drawable?,
    rDrawable: Drawable?,
    lIconWidth: Float? = null,
    lIconHeight: Float? = null,
    rIconWidth: Float? = null,
    rIconHeight: Float? = null
) {
    if (lIconWidth != null && lIconHeight != null) {
        lDrawable?.setBounds(0, 0, ConvertUtils.dp2px(lIconWidth), ConvertUtils.dp2px(lIconHeight))
    }
    if (rIconWidth != null && rIconHeight != null) {
        rDrawable?.setBounds(0, 0, ConvertUtils.dp2px(rIconWidth), ConvertUtils.dp2px(rIconHeight))
    }
    setCompoundDrawables(lDrawable, null, rDrawable, null)
}