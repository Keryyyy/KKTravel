package com.hy.baseapp.net

import com.hy.baseapp.common.bean.ApiResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2019/06/27  14:24
 *     desc  :
 *
 * </pre>
 */
interface ApiService {

    //示例
    @POST("/baidu.com")
    suspend fun postTest(@Body body: RequestBody): ApiResponse<Any>

}