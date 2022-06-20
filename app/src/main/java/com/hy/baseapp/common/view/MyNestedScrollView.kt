package com.hy.baseapp.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView
import kotlin.math.abs

/**
 * 不拦截水平滑动事件
 */
open class MyNestedScrollView(context: Context, attrs: AttributeSet?) : NestedScrollView(context, attrs) {

    private var cantScrollRegion: Int = 0
    private var mDownY: Float = 0f
    private var mDownX: Float = 0f

    private var canScroll = true

    fun setCanScroll(b:Boolean){
        canScroll = b
    }

    fun canScroll(): Boolean {
        return canScroll
    }

    fun setCantScrollRegion(height:Int){
        cantScrollRegion = height
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if(canScroll) {
            try {
                return super.onTouchEvent(ev)
            } catch (ex: IllegalArgumentException) {
                ex.printStackTrace()
            }
        }
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {//优化滑动（例：内部嵌套viewpager时，左右滑动不好相应问题）
        if (canScroll){
            val x = ev.x
            val y = ev.y
            when (ev.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    mDownX = x
                    mDownY = y
                }
                MotionEvent.ACTION_MOVE -> {
                    if (mDownY < cantScrollRegion){
                        return false
                    }else {
                        val deltaX: Float = abs(x - mDownX)
                        val deltaY: Float = abs(y - mDownY)
                        // 这里是够拦截的判断依据是左右滑动
                        if (deltaX < deltaY) {
                            return false
                        }
                    }
                }
            }
            try {
                return super.onInterceptTouchEvent(ev)
            } catch (ex: IllegalArgumentException) {
                ex.printStackTrace()
            }
        }
        return false
    }
}