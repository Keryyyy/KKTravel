package com.hy.baseapp.base

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2020/10/20
 *     desc  : 全局配置
 *
 * </pre>
 */

//编译模式,控制日志开关等三方SDK初始化行为
//也可以使用 BuildConfig.DEBUG 根据编译环境自动控制
const val RELEASE_MODE = true

var BASE_NET_URL = if (RELEASE_MODE) "" else ""
const val BASE_OFFICIAL_URL = "" //官网
