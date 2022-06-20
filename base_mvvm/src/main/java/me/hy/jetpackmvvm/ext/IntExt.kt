/*
 * Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package me.hy.jetpackmvvm.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.TimeUtils
import java.math.BigDecimal
import java.text.DecimalFormat
import kotlin.math.roundToInt


/**
 * 后台来的是秒
 * 所以需要 X1000 转换一下
 */
fun Int.seconds2StringDate(pattern:String = "yyyy-MM-dd"): String{
    val longTime = this.toLong() * 1000
    return TimeUtils.millis2String(longTime,pattern)
}
/**
 * 后台来的是秒
 * 所以需要 X1000 转换一下
 */
fun Long.seconds2StringDate(pattern:String = "yyyy-MM-dd"): String{
    val longTime = this * 1000
    return TimeUtils.millis2String(longTime,pattern)
}


/**
 * 解析xml布局
 *
 * @param parent 父布局
 * @param attachToRoot 是否依附到父布局
 */
fun Int.inflate(parent: ViewGroup, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(parent.context).inflate(this, parent, attachToRoot)
}

/**
 * 获取转换后的时间样式。
 *
 * @return 处理后的时间样式，示例：06:50
 */
fun Int.conversionVideoDuration(): String {
    val minute = 1 * 60
    val hour = 60 * minute
    val day = 24 * hour

    return when {
        this < day -> {
            String.format("%02d:%02d", this / minute, this % 60)
        }
        else -> {
            String.format("%02d:%02d:%02d", this / hour, (this % hour) / minute, this % 60)
        }
    }
}


/**
 * 数字转成中文的 类似1.3W单位
 */
fun Int.int2ChineseNumber(show0:Boolean = false): String {
    var str = ""
    str = when {
        this <= 0 -> {
            if (show0) "0" else ""
        }
        this < 10000 -> {
            this.toString()
        }
        else -> {
            val d = this.toDouble()
            //将数字转换成万
            val num = d / 10000
            val b = BigDecimal(num)
            //四舍五入保留小数点后一位
            val f1: Double = b.setScale(1, BigDecimal.ROUND_HALF_UP).toDouble()
            f1.toString() + "w"
        }
    }
    return str
}


/**
 * Android 音乐播放器应用里，读出的音乐时长为 long 类型以毫秒数为单位，例如：将 234736 转化为分钟和秒应为 03:55 （包含四舍五入）
 * @param duration 音乐时长
 * @return
 */
fun Int.timeParse(): String? {
    var time: String? = ""
    val minute = this / 60000
    val seconds = this % 60000
    val second = (seconds.toFloat()).roundToInt().toLong()
    if (minute < 10) {
        time += "0"
    }
    time += "$minute:"
    if (second < 10) {
        time += "0"
    }
    time += second
    return time
}

/**
 * 将秒转化为 HH:mm:ss 的格式
 *
 * @param time 秒
 * @return
 */
val decimalFormat: DecimalFormat by lazy { DecimalFormat("00") }
fun Int.formatHourTime():String {
    val hh= decimalFormat.format (this / 3600)
    val mm = decimalFormat.format (this % 3600 / 60)
    val ss = decimalFormat.format (this % 60)
    return "$hh:$mm:$ss"
}


/**
 * 将秒转化为 mm:ss 的格式
 *
 * @param time 秒
 * @return
 */
val decimalFormatMinute: DecimalFormat by lazy { DecimalFormat("00") }
fun Int.formatMinuteTime():String {
    val mm = decimalFormatMinute.format (this % 3600 / 60)
    val ss = decimalFormatMinute.format (this % 60)
    return "$mm:$ss"
}