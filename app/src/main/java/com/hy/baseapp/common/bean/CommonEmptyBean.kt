package com.hy.baseapp.common.bean

import androidx.annotation.Keep
import me.hy.jetpackmvvm.network.AppException

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2021/04/14
 *     desc  :
 *
 * </pre>
 */

@Keep
class CommonEmptyPackedBean(val packedType:PackedType, val isSuccess:Boolean = true, val message:String = "",
                            val exception: AppException?=null, val apiResponse: ApiResponse<Any>?=null)

@Keep
enum class PackedType{
    DEFAULT,
    /**
     * 点赞
     */
    POINT
}