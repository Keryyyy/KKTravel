package me.hy.jetpackmvvm.ext.util

import android.graphics.Color
import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/26
 *     desc  :
 *
 * </pre>
 */

fun shortToast(content:String,gravity: Int = Gravity.CENTER){
    ToastUtils.make()
        .setBgColor(Color.parseColor("#FF000000"))
        .setTextColor(Color.parseColor("#FFFFFF"))
        .setGravity(gravity,0,100)
        .setDurationIsLong(false)
        .show(content)
}

fun longToast(content:String){
    ToastUtils.make()
        .setBgColor(Color.parseColor("#FF000000"))
        .setTextColor(Color.parseColor("#FFFFFF"))
        .setGravity(Gravity.CENTER,0,100)
        .setDurationIsLong(true)
        .show(content)
}