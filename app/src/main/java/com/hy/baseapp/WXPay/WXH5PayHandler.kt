/*
package com.hy.baseapp.WXPay

import android.content.Intent
import android.net.Uri
import android.webkit.WebView

import java.util.*

*/
/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/03/08
 *     desc  :
 *
 * </pre>
 *//*

class WXH5PayHandler {

    private val REDIRECT_URL = "redirect_url"

    */
/**
     * 发起h5支付的url
     *//*

    private var h5Url:String = ""
    */
/**
     * 唤起微信app支付页的scheme协议url
     *//*

    private var launchUrl:String = ""
    */
/**
     * 回跳页面url<br/>
     * 如，您希望用户支付完成后跳转至https://xxx<br/>
     * 看下官方文档怎么说: <br/>
     * 由于设置redirect_url后,回跳指定页面的操作可能发生在：
     * 1,微信支付中间页调起微信收银台后超过5秒
     * 2,用户点击“取消支付“或支付完成后点“完成”按钮。因此无法保证页面回跳时，支付流程已结束，所以商户设置的redirect_url地址不能自动执行查单操作，应让用户去点击按钮触发查单操作。
     *//*

    private var redirectUrl:String = ""

    */
/*-------------------- 步骤1：拿到h5支付链接，并在原WebView页面打开 --------------------*//*


    */
/**
     * 是否是微信h5支付的链接
     *//*

    companion object{
        fun isWXH5Pay(url:String):Boolean {
            if (url.isEmpty()) {
                return false;
            }
            return url.lowercase(Locale.getDefault()).startsWith("https://wx.tenpay.com");
        }
    }

    */
/**
     * 方案1: 推荐,直接return false, 调用{@link android.webkit.WebViewClient#shouldOverrideUrlLoading(WebView, String)}默认处理
     * <p>
     * 调用前请先调用{@link #isWXH5Pay(String)}判断是否是微信h5支付
     *
     * @param url
     * @return
     *//*

    fun pay(url:String):Boolean {
        h5Url = url
        redirectUrl = getRedirectUrl(url)?:""
        return false
    }


    */
/**
     * 方案2: 不推荐, 调用{@link WebView#loadUrl(String)}, 同时return true.<br/>
     * 但这样会丢失掉{@param url}的请求头参数, 如必需的referer, 这个时候要求调用{@link WebView#loadUrl(String, Map)}
     * <p>
     * 调用前请先调用{@link #isWXH5Pay(String)}判断是否是微信h5支付
     *
     * @param webView
     * @param url
     * @param headers 自定义的header, 其中必须包含微信H5支付所必需的referer
     * @return
     *//*

    private fun pay(webView: WebView, url:String, headers:Map<String, String>):Boolean {
        h5Url = url
        redirectUrl = getRedirectUrl(url) ?:""
        webView.loadUrl(url, headers)
        return true
    }

    private fun getRedirectUrl(url:String):String? {
        return try {
            val uri = Uri.parse(url)
            uri.getQueryParameter(REDIRECT_URL)
        } catch (e:Exception) {
            ""
        }
    }

    */
/*-------------------- 步骤2：拿到唤起微信的scheme链接，并唤起微信app的支付页 --------------------*//*


    */
/**
     * 是否将要唤起微信h5支付页面
     *
     * @param url 微信的scheme(weixin)开头的url: weixin://wap/pay?xxx
     * @return
     *//*

    fun isWXLaunchUrl(url:String):Boolean {
        if (url.isEmpty()) {
            return false
        }
        return url.lowercase(Locale.getDefault()).startsWith("weixin://");
    }

    */
/**
     * 调用{@link #h5Url}后会重定向到微信的scheme url去唤起微信app的h5支付页面
     * 调用前请先调用{@link #isWXLaunchUrl(String)}判断是否是微信的scheme url
     *
     * @param url
     * @return
     *//*

    fun launchWX(webView: WebView, url:String):Boolean {
        launchUrl = url
        return try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            webView.context.startActivity(intent)
            true
        } catch (e:Exception) {
            // catch掉的话就内部打开
            false
        }
    }

    */
/*-------------------- 步骤3：等待微信回跳redirect_url， 并在原WebView页面打开 --------------------*//*


    */
/**
     * 是否是微信h5支付的回跳url<br/>
     * 调用{@link #pay(String)}的时候从<a href="https://wx.tenpay.com/xxx?redirect_url=xxx">https://wx.tenpay.com/xxx?redirect_url=xxx<a/>的参数中解析出来了<br/>
     * 这里直接equals
     *
     * @param url
     * @return
     *//*

    fun isRedirectUrl(url:String):Boolean{
        if (url.isEmpty()) {
            return false
        }
        return url.equals(redirectUrl,true)
    }

    */
/**
     * 回跳页面url， 在{@link android.webkit.WebViewClient#shouldOverrideUrlLoading(WebView, String)}中调用
     *
     * @see #redirectUrl
     *//*

    fun redirect():Boolean {
        // 原页面打开
        return false
    }
}*/
