/*
package com.jingniao.beibei.WXPay

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.*
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.activity_common_web.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.jingniao.beibei.base.BASE_NET_URL
import com.jingniao.beibei.base.BaseActivityV2
import com.jingniao.beibei.base.WECHAT_PAY_H5_REDIRECT_URL
import com.jingniao.beibei.common.extensions.getStringByDefault
import com.jingniao.beibei.databinding.ActivityWechatH5Binding
import com.jingniao.beibei.ui.shop.viewmodel.ShopViewModel

*/
/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/03/08
 *     desc  : 微信h5支付
 *
 * </pre>
 *//*

class WXH5PayActivity:BaseActivityV2<ShopViewModel,ActivityWechatH5Binding>() {

    private var mUrl:String = ""

    override fun initView(savedInstanceState: Bundle?) {
        immersionBar()
        mUrl = getStringByDefault()

        initWebView()

        // 指定申请微信 H5 支付时填写的域名，
        val map = HashMap<String, String>()
        map["Referer"] = BASE_NET_URL
        mDataBind.webView.loadUrl(mUrl,map)

    }


    override fun createObserver() {
        mDataBind.ivBack.clicks().subscribe { onBackPressed() }
    }

    private fun initWebView() {
        mDataBind.webView.settings.apply {
            javaScriptEnabled = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN //把html中的内容放大webview等宽的一列中
            builtInZoomControls = false
            setSupportZoom(false)
            defaultTextEncodingName = "utf-8"
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_DEFAULT
            databaseEnabled = true
            loadsImagesAutomatically = true
            setAppCacheEnabled(true)
            allowFileAccess = true
//            savePassword = true
//        webSettings.userAgentString = "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        mDataBind.webView.webViewClient = XWebViewClient()
    }



    inner class XWebViewClient: WebViewClient(){
        private var mWXH5PayHandler: WXH5PayHandler? = null

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            LogUtils.d("pageStarted")
            super.onPageStarted(view, url, favicon)
        }

        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
//            handleLoading(view,url?:"")
            if (url.isEmpty()) {
                return true
            }

            var uri:Uri? = null
            try {
                uri = Uri.parse(url)
            } catch (e:Exception) {
                e.printStackTrace()
            }
            if (uri == null) {
                return true
            }

            if (!URLUtil.isNetworkUrl(url)) {
                //  处理微信h5支付2
                if (mWXH5PayHandler != null && mWXH5PayHandler!!.isWXLaunchUrl(url)) {
                    mWXH5PayHandler!!.launchWX(view, url)
                } else {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        view.context?.startActivity(intent)
                    } catch (e:Exception) {
                        e.printStackTrace()
                    }
                }
                return true
            }

            when {
                WXH5PayHandler.isWXH5Pay(url?:"") -> {
                    // 处理微信h5支付1
                    mWXH5PayHandler = WXH5PayHandler()
                    return mWXH5PayHandler!!.pay(url?:"")
                }
                mWXH5PayHandler != null -> {
                    // 处理微信h5支付3
                    if (mWXH5PayHandler!!.isRedirectUrl(url)) {
                        val result = mWXH5PayHandler!!.redirect()
                        mWXH5PayHandler = null
                        return result
                    }
                    mWXH5PayHandler = null
                }
                url == WECHAT_PAY_H5_REDIRECT_URL -> {
//                    "支付完成，请核对支付结果".shortToast()
                    appViewModel.refreshUserInfo()
                    lifecycleScope.launch {
                        delay(2000)
                        finish()
                    }
                }
            }
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun shouldOverrideUrlLoading(
            view: WebView,
            request: WebResourceRequest
        ): Boolean {
            val url = request.url.toString()
            if (url.isEmpty()) {
                return true
            }

            var uri:Uri? = null
            try {
                uri = Uri.parse(url)
            } catch (e:Exception) {
                e.printStackTrace()
            }
            if (uri == null) {
                return true
            }

            if (!URLUtil.isNetworkUrl(url)) {
                //  处理微信h5支付2
                if (mWXH5PayHandler != null && mWXH5PayHandler!!.isWXLaunchUrl(url)) {
                    mWXH5PayHandler!!.launchWX(view, url)
                } else {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        view.context?.startActivity(intent)
                    } catch (e:Exception) {
                        e.printStackTrace()
                    }
                }
                return true
            }

            when {
                WXH5PayHandler.isWXH5Pay(url) -> {
                    // 处理微信h5支付1
                    mWXH5PayHandler = WXH5PayHandler()
                    return mWXH5PayHandler!!.pay(url)
                }
                mWXH5PayHandler != null -> {
                    // 处理微信h5支付3
                    if (mWXH5PayHandler!!.isRedirectUrl(url)) {
                        val result = mWXH5PayHandler!!.redirect()
                        mWXH5PayHandler = null
                        return result
                    }
                    mWXH5PayHandler = null
                }
                url == WECHAT_PAY_H5_REDIRECT_URL -> {
//                    "支付完成，请核对支付结果".shortToast()
                    appViewModel.refreshUserInfo()
                    lifecycleScope.launch {
                        delay(2000)
                        finish()
                    }
                }
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
    }


    override fun onDestroy() {
        web?.let {
            val parent = web?.parent
            if (parent!=null){
                (parent as ViewGroup).removeAllViews()
            }
            it.stopLoading()
            it.settings.javaScriptEnabled = false
            it.clearHistory()
            it.clearAnimation()
            it.removeAllViews()
            it.destroy()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        // webView.canGoBack()等同于webView.canGoBackOrForward(-1)
        // webView.goBack()等同于webView.goBackOrForward(-1)
        // back history
        var index = -1; // -1表示回退history上一页
        var url:String
        val history: WebBackForwardList = mDataBind.webView.copyBackForwardList()
        while (mDataBind.webView.canGoBackOrForward(index)) {
            url = history.getItemAtIndex(history.currentIndex + index).url
            if (URLUtil.isNetworkUrl(url) && !WXH5PayHandler.isWXH5Pay(url)) {
                mDataBind.webView.goBackOrForward(index)
                return
            }
            index--
        }
        finish()
        super.onBackPressed()
    }


}*/
