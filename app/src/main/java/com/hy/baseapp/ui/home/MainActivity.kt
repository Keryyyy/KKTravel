package com.hy.baseapp.ui.home

import android.os.Bundle
import com.hy.baseapp.base.BaseActivity
import com.hy.baseapp.databinding.ActivityMainBinding
import com.hy.baseapp.ui.home.viewmodel.HomeViewModel

class MainActivity : BaseActivity<HomeViewModel,ActivityMainBinding>() {


    override fun initView(savedInstanceState: Bundle?) {
        fullStatusBar()
    }

    override fun createObserver() {
        super.createObserver()
    }
}