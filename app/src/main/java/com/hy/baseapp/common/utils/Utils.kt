package com.hy.baseapp.common.utils

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.webkit.WebView
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.AppUtils
import com.hy.baseapp.base.event.App
import com.hy.baseapp.base.event.appInstance
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.math.roundToInt

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/25
 *     desc  :
 *
 * </pre>
 */

/**
 * 协程计时器
 */
fun tickerFlow(period: Long, initialDelay: Long = 0L) = flow {
    delay(initialDelay)
    while (true) {
        emit(Unit)
        delay(period)
    }
}

/**
 * 协程倒计时
 */
fun countDownCoroutines(total:Int,onTick:(Int)->Unit,onFinish:()->Unit,
                        scope: CoroutineScope = GlobalScope
): Job {
    return flow{
        for (i in total downTo 0){
            emit(i)
            delay(1000)
        }
    }.flowOn(Dispatchers.Default)
        .onCompletion { onFinish.invoke() }
        .onEach { onTick.invoke(it) }
        .flowOn(Dispatchers.Main)
        .launchIn(scope)
}

// 获取渠道工具函数
fun getChannelName(): String {
    var channelName: String? = null
    try {
        val packageManager = appInstance.packageManager
        if (packageManager != null) {
            //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是activity标签中，所以用ApplicationInfo
            val applicationInfo = packageManager.getApplicationInfo(
                appInstance.packageName,
                PackageManager.GET_META_DATA
            )
            if (applicationInfo.metaData != null) {
                channelName = applicationInfo.metaData["UMENG_CHANNEL"].toString()
            }
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    if (TextUtils.isEmpty(channelName)) {
        channelName = "Unknown"
    }
    return channelName?:"null"
}


/**
 * 读取本地Json文件
 */
fun getJsonFromAssets(context: Context, fileName:String): String {
    val stringBuilder = StringBuilder()
    try {
        val assetManager = context.assets
        val bf = BufferedReader(
            InputStreamReader(
                assetManager.open(fileName)
            )
        )
        var line: String?
        while (bf.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return stringBuilder.toString()
}


/**
 * 修复当某个WebView为单独进程时
 * 与自身进程WebView冲突报错
 * P以后WebView不允许共用目录
 */
fun fixWebViewDataDirectoryBug() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val processName: String = Application.getProcessName()
        val packageName = AppUtils.getAppPackageName()
        if (packageName != processName) {
            WebView.setDataDirectorySuffix(processName)
        }
    }
}