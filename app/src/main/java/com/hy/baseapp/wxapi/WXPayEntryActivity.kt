/*
package com.hy.baseapp.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.blankj.utilcode.util.LogUtils
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.jingniao.beibei.base.WECHAT_APP_ID
import com.jingniao.beibei.base.eventViewModel
import com.jingniao.beibei.common.SERVER_WAIT_TIME
import com.jingniao.beibei.common.extensions.shortToast

*/
/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2020/12/29
 *     desc  : 微信支付回调
 *
 * </pre>
 *//*

class WXPayEntryActivity:Activity(),IWXAPIEventHandler {

    lateinit var api:IWXAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        api = WXAPIFactory.createWXAPI(this, WECHAT_APP_ID)
        api.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        api.handleIntent(intent, this)
    }

    override fun onResp(resp: BaseResp?) {
        if (resp?.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            when(resp.errCode){
                0 -> {
                    "支付成功".shortToast()
                    //通知个人信息页重新加载并刷新UserInfo
//                    LiveEventBus.get(EVENT_BUS_KEY_MINE_REFRESH).post(null)
                    Handler(Looper.getMainLooper()).postDelayed({
                        eventViewModel.paySuccess.value = true
                        finish()
                    }, SERVER_WAIT_TIME)
                }
                -1-> {
                    "配置错误".shortToast()
                    LogUtils.e("${resp.errCode} ${resp.errStr}")
                    finish()
                }
                -2 -> {
//                    "支付取消".shortToast()
                    finish()
                }
            }
        }
    }

    override fun onReq(p0: BaseReq?) {

    }
}*/
