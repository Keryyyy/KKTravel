@file:OptIn(ExperimentalContracts::class)
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package me.hy.jetpackmvvm.ext.util

import android.util.Patterns
import com.google.gson.Gson
import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/5/15
 *     desc  :
 * </pre>
 */

@SinceKotlin("1.3")
@kotlin.internal.InlineOnly
inline fun String?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }

    return this != null && !this.trim().equals("null", true) && this.trim().isNotEmpty()
}

@kotlin.internal.InlineOnly
inline fun String.isValidPhone(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.PHONE.matcher(this).matches()
}

/**
 * format Phone number
 *
 * Example:
 *
 * ```
 * val phontNumberStr = "044 668 18 00"
 * phontNumberStr.formatPhoneNumber("CH")
 * ```
 */
//@kotlin.internal.InlineOnly
//inline fun String.formatPhoneNumber(region: String): String? {
//    val phoneNumberUtil = PhoneNumberUtil.getInstance()
//    val number = phoneNumberUtil.parse(this, region)
//    if (!phoneNumberUtil.isValidNumber(number))
//        return null
//    return phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
//}

@kotlin.internal.InlineOnly
inline fun String.isValidEmail(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

@kotlin.internal.InlineOnly
inline fun String.isIPAddress(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.IP_ADDRESS.matcher(this).matches()
}

@kotlin.internal.InlineOnly
inline fun String.isDomainName(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.DOMAIN_NAME.matcher(this).matches()
}

@kotlin.internal.InlineOnly
inline fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

/**
 * 是否为座机号
 */
@kotlin.internal.InlineOnly
inline fun String?.isTel(): Boolean {
    return this?.let {
        val matcher1 = Pattern.matches("^0(10|2[0-5|789]|[3-9]\\d{2})\\d{7,8}\$", this)
        val matcher2 = Pattern.matches("^0(10|2[0-5|789]|[3-9]\\d{2})-\\d{7,8}$", this)
        val matcher3 = Pattern.matches("^400\\d{7,8}$", this)
        val matcher4 = Pattern.matches("^400-\\d{7,8}$", this)
        val matcher5 = Pattern.matches("^800\\d{7,8}$", this)
        val matcher6 = Pattern.matches("^800-\\d{7,8}$", this)
        return matcher1 || matcher2 || matcher3 || matcher4 || matcher5 || matcher6
    }?:let {
        false
    }
}

/**
 * 是否为邮箱号
 */
@kotlin.internal.InlineOnly
inline fun String?.isEmail(): Boolean {
    return this?.let {
        Pattern.matches(this, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$")
    }?:let {
        false
    }
}

/**
 * 将对象转为JSON字符串
 */
@kotlin.internal.InlineOnly
inline fun Any?.toJson():String{
    return Gson().toJson(this)
}

/**
 * 价格格式是否正确(不可为0)
 */
@kotlin.internal.InlineOnly
inline fun String.isPrice(): Boolean {
    if (this.contains(Regex("^\\+?(?!0+(\\.00?)?\$)\\d+(\\.\\d\\d?)?\$"))
        && checkDecimalFirstNotZero(this)
    ) {
        return true
    }
    return false
}

/**
 * 检查首位数字是否为0，如 001.1 则不合法
 */
@kotlin.internal.InlineOnly
inline fun checkDecimalFirstNotZero(price: String): Boolean {
    if (price.contains(".")) { //如果包含小数
        val sub = price.substringBefore(".") //取出小数前面的数字
        return if (sub.length >= 2 && sub.first().toString() != "0") { //当小数前面的数字长度大于2并且第一位不为0则符合规则
            true
        } else sub.length < 2 //如果.前面的数字长度小于2也则符合规则
    } else if (price.first().toString() != "0") {
        return true //不包含小数且第一位不为0则符合规则
    }
    return false
}

/**
 * 价格格式是否正确(可为0)
 */
@kotlin.internal.InlineOnly
inline fun String.isPriceWithout0(): Boolean {
    if (this.contains(Regex("^[0-9]*\$"))
        && checkDecimalFirstNot0Include0(this)
    ) {
        return true
    }
    return false
}

/**
 * 检查首位数字是否为0，如 001.1 则不合法，若只为0也合法
 */
@kotlin.internal.InlineOnly
inline fun checkDecimalFirstNot0Include0(price: String): Boolean {
    if (price.contains(".")) { //如果包含小数
        val sub = price.substringBefore(".") //取出小数前面的数字
        return if (sub.length >= 2 && sub.first().toString() != "0") {
            true //当小数前面的数字长度大于2并且第一位不为0则符合规则
        } else sub.length < 2 //如果.前面的数字长度小于2也则符合规则
    } else if (price.length > 1) {
        if (price.first().toString() != "0") {
            return true //不包含小数且第一位不为0则符合规则
        }
    } else {
        //只有一位数字时都符合规则
        return true
    }
    return false
}

/**
 * 格式化成131****0123
 * 隐藏中间四位
 */
fun String.replace2Hint():String {
    val sb = StringBuilder()
    if (this.length < 2){
//        ToastUtils.showShort("账号不能小于两位")
        return this
    }
    val startStr = this.substring(0,2)
//    val endStr = this.reversed().substring(0,2) //需要倒序
    val endStr = this.substring(this.length - 2,this.length) //不需要倒序
    sb.append(startStr)
    sb.append("****")
    sb.append(endStr)
    return sb.toString()
}

/**
 * 微信号 6-20位
 */
@kotlin.internal.InlineOnly
inline fun String.isWechat():Boolean = this.length in 6..20

/**
 * 字符串不为空
 */
@kotlin.internal.InlineOnly
inline fun CharSequence?.isNotNullOrEmpty(no:String.()->Unit, nullOrEmptyOrBlank:String.()->Unit = {}) {
    if (this.isNullOrEmpty() || this.isNullOrBlank()) nullOrEmptyOrBlank.invoke(this.toString()) else no.invoke(this.toString())
}
