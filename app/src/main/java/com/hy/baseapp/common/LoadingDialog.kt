package com.hy.baseapp.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.widget.TextView
import com.hy.baseapp.R


/**
 * 加载进度对话框
 */

 class LoadingDialog(context: Context)  : Dialog(context) {
    private var tv_text: TextView ?=null
    private var mCountDownTimer: CountDownTimer? = null

    init {
        /**设置对话框背景透明 */
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.loading)
        tv_text = findViewById(R.id.tv_text)
        setCanceledOnTouchOutside(false)
    }

    override fun create() {
//        val rotateAnimation = RotateAnimation(
//            0f,
//            360f,
//            Animation.RELATIVE_TO_SELF,
//            0.5f,
//            Animation.RELATIVE_TO_SELF,
//            0.5f
//        ).apply {
//            duration = 2000
//            repeatCount = -1
//            fillAfter = true
//            interpolator = AccelerateDecelerateInterpolator()
//        }
//        iv_loading.startAnimation(rotateAnimation)
        super.create()
    }

    /**
     * 为加载进度个对话框设置不同的提示消息
     *
     * @param message 给用户展示的提示信息
     * @return build模式设计，可以链式调用
     */
    fun setMessage(message: String): LoadingDialog {
        tv_text?.text = message
        return this
    }

    /**
     * 为加载框设置倒计时
     * @param seconds 倒计时，秒
     * @param listener 倒计时结束回调接口
     */
    fun setOnTimeOut(seconds: Int, listener: TimeOutListener) {
        mCountDownTimer = object : CountDownTimer(seconds * 1000.toLong(), 1000) {
            override fun onFinish() {
                listener.timeOut()
                mCountDownTimer = null
            }

            override fun onTick(millisUntilFinished: Long) {}
        }
        mCountDownTimer!!.start()
    }

    override fun dismiss() {
        super.dismiss()
        cancelTimeOut()
    }

    /**
     *  当Activity销毁或者成功记得释放
     */
    fun cancelTimeOut() {
        mCountDownTimer?.let {
            mCountDownTimer?.cancel()
        }
        mCountDownTimer = null
    }

    interface TimeOutListener{
        fun timeOut()
    }

}
