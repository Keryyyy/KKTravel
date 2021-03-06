package com.hy.baseapp.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

/**
 * <pre>
 *
 * author: Hy
 * time  : 2020/12/23
 * desc  : 自动滚动RecyclerView
 *
</pre> *
 */
class AutoPollRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(
    context, attrs
) {
    var autoPollTask: AutoPollTask = AutoPollTask(this)
    private var running //标示是否正在自动轮询
            = false
    private var canRun //标示是否可以自动轮询,可在不需要的是否置false
            = false

    class AutoPollTask(reference: AutoPollRecyclerView) : Runnable {
        private val mReference: WeakReference<AutoPollRecyclerView> = WeakReference(reference)
        override fun run() {
            val recyclerView = mReference.get()
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.scrollBy(2, 2)
                recyclerView.postDelayed(recyclerView.autoPollTask, TIME_AUTO_POLL)
            }
        }
    }

    //开启:如果正在运行,先停止->再开启
    fun start() {
        if (running) stop()
        canRun = true
        running = true
        postDelayed(autoPollTask, TIME_AUTO_POLL)
    }

    fun stop() {
        running = false
        removeCallbacks(autoPollTask)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> if (running) stop()
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> if (canRun) start()
        }
        //return  false，注释掉onTouchEvent()方法里面的stop和start方法，则列表自动滚动且不可触摸
        return super.onTouchEvent(e)
    }

    companion object {
        private const val TIME_AUTO_POLL: Long = 16
    }

}