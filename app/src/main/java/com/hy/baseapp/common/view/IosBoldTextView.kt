package com.hy.baseapp.common.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2021/02/24
 *     desc  : 仿IOS的中粗效果
 *
 * </pre>
 */
class IosBoldTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
    androidx.appcompat.widget.AppCompatTextView(context,attrs, defStyleAttr) {
    override fun onDraw(canvas: Canvas?) {
        paint.apply {
            strokeWidth = 1.5f
            style = Paint.Style.FILL_AND_STROKE
        }
        super.onDraw(canvas)
    }
}