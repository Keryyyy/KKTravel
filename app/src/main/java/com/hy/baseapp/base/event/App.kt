package com.hy.baseapp.base.event

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.hy.baseapp.BuildConfig
import com.hy.baseapp.R
import com.hy.baseapp.base.event.App.Companion.appViewModelInstance
import com.hy.baseapp.base.event.App.Companion.eventViewModelInstance
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import me.hy.jetpackmvvm.base.BaseApp
import me.jessyan.autosize.AutoSizeConfig

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/24
 *     desc  :
 *
 * </pre>
 */

//Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
val appViewModel: AppViewModel by lazy { appViewModelInstance }

//Application全局的ViewModel，用于发送全局通知操作
val eventViewModel: EventViewModel by lazy { eventViewModelInstance }

//ApplicationInstance
lateinit var appInstance:BaseApp

class App : BaseApp() {

    companion object {
        var mQnToken = ""
        var mQnPrefix = ""

        val appViewModelInstance: AppViewModel by lazy { appInstance.getAppViewModelProvider()[AppViewModel::class.java] }
        val eventViewModelInstance: EventViewModel by lazy { appInstance.getAppViewModelProvider()[EventViewModel::class.java] }
    }


    override fun onCreate() {
        super.onCreate()
        appInstance = this
        setConfig()
    }

    /**
     * 初始化各种框架,SDK,配置信息
     */
    private fun setConfig(){
        MMKV.initialize(this)
        AutoSizeConfig.getInstance().apply {
            isExcludeFontScale = true
            screenHeight = ScreenUtils.getScreenHeight()
        }

        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG)
            .setLogHeadSwitch(true)
            .setBorderSwitch(true)
            .setSingleTagSwitch(true)
            .setGlobalTag("LOG")
            .setConsoleSwitch(true)


    }

    private fun initSmartRefreshLayout() {
//        SmartRefreshLayout.setDefaultRefreshInitializer {_, layout ->
//            layout.setEnableLoadMore(true)
////                layout.setEnableLoadMoreWhenContentNotFull(false)
//        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(false)
            MaterialHeader(context).setColorSchemeResources(
                R.color.main_color,
                R.color.main_color,
                R.color.main_color
            )
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, layout ->
            layout.setEnableFooterFollowWhenNoMoreData(true)
            layout.setEnableFooterTranslationContent(true)
            layout.setFooterHeight(50f)
            layout.setFooterTriggerRate(0.6f)
//            NoStatusFooter.REFRESH_FOOTER_NOTHING = "- The End -"
//            NoStatusFooter(context).apply {
//                setAccentColorId(R.color.colorTextPrimary)
//                setTextTitleSize(16f)
            ClassicsFooter(appInstance)
//            }
        }
    }
}