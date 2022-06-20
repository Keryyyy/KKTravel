package me.hy.jetpackmvvm.ext

import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.TimeUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2020/11/6
 *     desc  :
 *
 * </pre>
 */


fun Long.getFriendlyTimeSecond(): String {
    val currentTimeSecondsDiffer = TimeUtils.getNowMills() / 1000 - this
    return when {
        currentTimeSecondsDiffer < 3600 -> {
            "刚刚"
        }
        currentTimeSecondsDiffer < 21600 -> {
            "${(currentTimeSecondsDiffer / 3600)}小时前"
        }
        TimeUtils.isToday(this * 1000) -> {
            "今天"
        }
        else -> {
            val time = TimeUtils.millis2String(this * 1000, "MM/dd")
            val sb = time.replace("/", "月")
            "${sb}日"
        }
    }
}

/**
 * 获取活跃时间
 */
fun Long.getFriendlyActiveTime(): String {
    val currentTimeSecondsDiffer = TimeUtils.getNowMills() / 1000 - this
    return when {
        currentTimeSecondsDiffer < 900 -> {
            "现在活跃"
        }
        currentTimeSecondsDiffer < 3600 -> {
            "刚刚活跃"
        }
        currentTimeSecondsDiffer < 21600 -> {
            "${(currentTimeSecondsDiffer / 3600)}小时前"
        }
        TimeUtils.isToday(this * 1000) -> {
            "今天活跃"
        }
        TimeUtils.getTimeSpan(TimeUtils.getNowMills(), (this * 1000), TimeConstants.DAY) < 7 -> {
            val currentTime = (TimeUtils.getNowMills() - this * 1000).toDouble() / TimeConstants.DAY
            val ceilTime = ceil(currentTime)
            "${ceilTime.toInt()}天内活跃"
        }
        TimeUtils.getTimeSpan(TimeUtils.getNowMills(), (this * 1000), TimeConstants.DAY) < 30 -> {
            "7天前活跃"
        }
        else -> {
            "不活跃"
        }
    }
}


fun Long.isVip() = System.currentTimeMillis() < this * 1000


fun Long.getFriendlyTimeSeconds(): String {
    val stringDate = TimeUtils.millis2String(this)
    val friendlyTime = TimeUtils.getFriendlyTimeSpanByNow(stringDate)
    return if (friendlyTime.contains(" ")) {
        "刚刚"
    } else {
        friendlyTime
    }
}

fun Long.getFriendlyTimeMills(): String {
    val stringDate = TimeUtils.millis2String(this * 1000)
    val friendlyTime = TimeUtils.getFriendlyTimeSpanByNow(stringDate)
    return if (friendlyTime.contains(" ")) {
        "刚刚"
    } else {
        friendlyTime
    }
}

fun Long.formatUTC(): String {
    val strPattern = "yyyy-MM-dd HH:mm:ss"
    val sdf = SimpleDateFormat(strPattern, Locale.CHINA)
    sdf.applyPattern(strPattern)
    return sdf.format(this)
}

fun Long.formatUTCYMD(): String {
    val strPattern = "yyyy-MM-dd"
    val sdf = SimpleDateFormat(strPattern, Locale.CHINA)
    sdf.applyPattern(strPattern)
    return sdf.format(this * 1000)
}
