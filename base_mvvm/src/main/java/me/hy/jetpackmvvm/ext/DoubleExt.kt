package me.hy.jetpackmvvm.ext

import java.text.DecimalFormat

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2021/1/23
 *     desc  :
 *
 * </pre>
 */
/**
 * 不保留小数
 */
fun Double.format0Dot():String = DecimalFormat("#").format(this)

/**
 * 保留一位小数
 */
fun Double.format1Dot():String = DecimalFormat("#.#").format(this)

/**
 * 保留两位小数
 */
fun Double.formatTo2Dot():String = DecimalFormat("#.##").format(this)

