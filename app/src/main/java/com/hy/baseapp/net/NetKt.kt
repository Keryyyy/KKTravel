package com.hy.baseapp.net

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2021/08/13
 *     desc  : 放业务地方的网络工具类
 *
 * </pre>
 */


/**
 * @body 需要请求的body参数
 * @timeMills 时间戳
 * @return MD5加密后的值
 */
//fun getSignature(jsonObject: JSONObject?, timeMills: String): String {
//    val sb = StringBuilder()
//    var str2 = ""
//    val constStr = "LVCHA-APP${AppUtils.getAppVersionName()}"
//    val map = TreeMap<String, String>()
//    sb.append("_timestamp=${timeMills}")
//    if (jsonObject == null) {
//        str2 = "$constStr${sb}"
//    } else {
//        val iterator = jsonObject.keys()
//        while (iterator.hasNext()) {
//            val key = iterator.next()
//            val value = jsonObject.getString(key)
//            map[key] = value
//        }
//        map.forEach { (t, u) ->
//            sb.append("&${t}=${u}")
//        }
//        str2 = "$constStr${sb}"
//    }
//    return EncryptUtils.encryptMD5ToString(str2).lowercase(Locale.CHINA)
//}
//
//}

//fun getSignatureV2(timeMills: String): String {
//    val accid = appViewModel._userInfo.value?.accid ?: ""
//    val token = appViewModel._userInfo.value?.token ?: ""
//    val channel = getChannelName()
//    val sb = "accid=${accid}&token=${token}&_timestamp=${timeMills}&chnl=${channel}"
//    return EncryptUtils.encryptMD5ToString("lvcha${sb}app")
//}
//
//fun JSONObject.getSign() {
//    val timeStamp = (TimeUtils.getNowMills() / 1000).toString()
//    val signature = getSignatureV2(timeStamp)
//    this.put("_timestamp", timeStamp)
//    this.put("_signature", signature)
//}
//
//fun JSONObject.getSignAnd2RequestBody(): RequestBody {
//    val timeStamp = (TimeUtils.getNowMills() / 1000).toString()
//    val signature = getSignatureV2(timeStamp)
//    this.put("_timestamp", timeStamp)
//    this.put("_signature", signature)
//    return this.toString().toRequestBody("application/json".toMediaTypeOrNull())
//}
//
//fun JSONObject.getPackParams(): RequestBody {
//    this.put("accid", appViewModel._userInfo.value?.accid)
//    this.put("token", appViewModel._userInfo.value?.token)
//    val timeStamp = (TimeUtils.getNowMills() / 1000).toString()
//    val signature = getSignatureV2(timeStamp)
//    this.put("_timestamp", timeStamp)
//    this.put("_signature", signature)
//    return this.toString().toRequestBody("application/json".toMediaTypeOrNull())
//}
//
//fun getDefaultNetParams(): RequestBody {
//    val jsonObject = JSONObject()
//        .apply {
//            this.put("accid", appViewModel._userInfo.value?.accid)
//            this.put("token", appViewModel._userInfo.value?.token)
//            val timeStamp = (TimeUtils.getNowMills() / 1000).toString()
//            val signature = getSignatureV2(timeStamp)
//            this.put("_timestamp", timeStamp)
//            this.put("_signature", signature)
//        }
//    return jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
//}