package com.hy.baseapp.common.utils

import android.app.Activity
import android.os.Build
import com.blankj.utilcode.util.StringUtils
import java.lang.reflect.InvocationTargetException

/**
 * <pre>
 *
 * author: Hy
 * time  : 2021/03/26
 * desc  : 刘海判断
 *
</pre> *
 */
object NotchUtils {
    /**
     * 是否有刘海屏
     *
     * @return
     */
    fun hasNotchInScreen(activity: Activity): Boolean {
        // android  P 以上有标准 API 来判断是否有刘海屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val decorView = activity.window.decorView
            val windowInsets = decorView.rootWindowInsets
            if (windowInsets != null) {
                val displayCutout = windowInsets.displayCutout
                if (displayCutout != null) {
                    val rects = displayCutout.boundingRects
                    //通过判断是否存在rects来确定是否刘海屏手机
                    if (rects != null && rects.size > 0) {
                        return true
                    }
                }
            }
        }
        // 通过其他方式判断是否有刘海屏  目前官方提供有开发文档的就 小米，vivo，华为（荣耀），oppo
        val manufacturer = Build.MANUFACTURER
        return if (StringUtils.isEmpty(manufacturer)) {
            false
        } else if (manufacturer.equals("HUAWEI", ignoreCase = true)) {
            hasNotchHw(activity)
        } else if (manufacturer.equals("xiaomi", ignoreCase = true)) {
            hasNotchXiaoMi(activity)
        } else if (manufacturer.equals("oppo", ignoreCase = true)) {
            hasNotchOPPO(activity)
        } else if (manufacturer.equals("vivo", ignoreCase = true)) {
            hasNotchVIVO()
        } else {
            false
        }
    }

    /**
     * 判断vivo是否有刘海屏
     * https://swsdl.vivo.com.cn/appstore/developer/uploadfile/20180328/20180328152252602.pdf
     * @return
     */
    private fun hasNotchVIVO(): Boolean {
        return try {
            val c = Class.forName("android.util.FtFeature")
            val get = c.getMethod("isFeatureSupport", Int::class.javaPrimitiveType)
            get.invoke(c, 0x20) as Boolean
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 判断oppo是否有刘海屏
     * https://open.oppomobile.com/wiki/doc#id=10159
     * @param activity
     * @return
     */
    private fun hasNotchOPPO(activity: Activity): Boolean {
        return activity.packageManager.hasSystemFeature("com.oppo.feature.screen.heteromorphism")
    }

    /**
     * 判断xiaomi是否有刘海屏
     * https://dev.mi.com/console/doc/detail?pId=1293
     *
     * @param activity
     * @return
     */
    private fun hasNotchXiaoMi(activity: Activity): Boolean {
        return getInt("ro.miui.notch", activity) == 1
    }

    /**
     * 小米刘海屏判断.
     *
     * @return 0 if it is not notch ; return 1 means notch
     * @throws if the key exceeds 32 characters
     */
    fun getInt(key: String?, activity: Activity): Int {
        var result = 0
        try {
            val classLoader = activity.classLoader
            val SystemProperties = classLoader.loadClass("android.os.SystemProperties")
            //参数类型
            val paramTypes = arrayOfNulls<Class<*>?>(2)
            paramTypes[0] = String::class.java
            paramTypes[1] = Int::class.javaPrimitiveType
            val getInt = SystemProperties.getMethod("getInt", *paramTypes)
            //参数
            val params = arrayOfNulls<Any>(2)
            params[0] = key
            params[1] = 0
            result = getInt.invoke(SystemProperties, *params) as Int
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 判断华为是否有刘海屏
     * https://devcenter-test.huawei.com/consumer/cn/devservice/doc/50114
     *
     * @param activity
     * @return
     */
    private fun hasNotchHw(activity: Activity): Boolean {
        return try {
            val cl = activity.classLoader
            val HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("hasNotchInScreen")
            get.invoke(HwNotchSizeUtil) as Boolean
        } catch (e: Exception) {
            false
        }
    }
}