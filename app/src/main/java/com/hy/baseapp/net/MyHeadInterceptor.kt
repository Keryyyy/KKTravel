package com.hy.baseapp.net

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.PhoneUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 自定义头部参数拦截器，传入heads
 */
class MyHeadInterceptor : Interceptor {

    //TODO 根据需要添加Header
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
//        val channel = if (BuildConfig.DEBUG){ "debug" } else { getChannelName() }

        val builder = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Cookie", "ky_auth=;sdk=${DeviceUtils.getSDKVersionCode()}")
            .addHeader("app-vrn", AppUtils.getAppVersionName())
            .addHeader("brand", "${android.os.Build.BRAND}_${android.os.Build.MODEL}_${android.os.Build.VERSION.RELEASE}")
//            .addHeader("chnl", channel)
            .addHeader("custom-vrn", AppUtils.getAppVersionCode().toString())
            .addHeader("ip", NetworkUtils.getIPAddress(true))
            .addHeader("isrealphone", PhoneUtils.isPhone().toString())
//            .addHeader("app-name","chayuan2")
//            .addHeader("os", ANDROID)
        return chain.proceed(builder.build())
    }

}