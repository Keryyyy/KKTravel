package me.hy.jetpackmvvm.base.activity

import android.view.View
import androidx.databinding.ViewDataBinding
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hy.jetpackmvvm.ext.inflateBindingWithGeneric

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: 包含ViewModel 和Databind ViewModelActivity基类，把ViewModel 和Databind注入进来了
 * 需要使用Databind的清继承它
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {

    override fun layoutId() = 0

    lateinit var mDataBind: DB

    /**
     * 创建DataBinding
     */
    override fun initDataBind(): View? {
        mDataBind = inflateBindingWithGeneric(layoutInflater)
        return mDataBind.root
    }
}