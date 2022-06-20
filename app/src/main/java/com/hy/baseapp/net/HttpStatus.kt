package com.hy.baseapp.net

import androidx.annotation.Keep

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2019/08/02  11:05
 *     desc  : 后台返回的统一请求状态
 *
 *     @code 200:请求成功
 *           1000:金币不足 (示例)
 * </pre>
 */
@Keep
open class HttpStatus {
    var msg: String? = null
    var code: Int = 0
    var time:Float? = null
    fun isSuccess(): Boolean {
        //TODO code == 1000 根据业务修改
        return code == 200 || code == 1000
    }
}