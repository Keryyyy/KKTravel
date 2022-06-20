package com.hy.baseapp.common.extension

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.StringUtils

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/27
 *     desc  : 杂七杂八通用的一些扩展类
 *
 * </pre>
 */

fun getResStr(@StringRes id: Int): String = StringUtils.getString(id)
fun getResColor(@ColorRes id: Int): Int = ColorUtils.getColor(id)
