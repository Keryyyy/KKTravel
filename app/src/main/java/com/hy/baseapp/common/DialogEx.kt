package com.hy.baseapp.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import com.afollestad.materialdialogs.MaterialDialog
import com.hy.baseapp.base.event.App


@SuppressLint("StaticFieldLeak")
private var mLoadingDialog: LoadingDialog?=null

/**
 * 显示消息弹窗
 * @param message 显示对话框的内容 必填项
 * @param title 显示对话框的标题 默认 温馨提示
 * @param positiveButtonText 确定按钮文字 默认确定
 * @param positiveAction 点击确定按钮触发的方法 默认空方法
 * @param negativeButtonText 取消按钮文字 默认空 不为空时显示该按钮
 * @param negativeAction 点击取消按钮触发的方法 默认空方法
 *
 */

fun AppCompatActivity.showDialogMessage(
    message: String,
    title: String = "温馨提示",
    positiveButtonText: String = "确定",
    positiveAction: () -> Unit = {},
    negativeButtonText: String = "",
    negativeAction: () -> Unit = {}
) {
    if (!this.isFinishing) {
        MaterialDialog(this)
            .cornerRadius(8f)
            .cancelOnTouchOutside(false)
            .show {
                title(text = title)
                message(text = message)
                positiveButton(text = positiveButtonText) {
                    positiveAction.invoke()
                }
                if (negativeButtonText.isNotEmpty()) {
                    negativeButton(text = negativeButtonText) {
                        negativeAction.invoke()
                    }
                }
            }
    }
}

/**
 * @param message 显示对话框的内容 必填项
 * @param title 显示对话框的标题 默认 温馨提示
 * @param positiveButtonText 确定按钮文字 默认确定
 * @param positiveAction 点击确定按钮触发的方法 默认空方法
 * @param negativeButtonText 取消按钮文字 默认空 不为空时显示该按钮
 * @param negativeAction 点击取消按钮触发的方法 默认空方法
 */
fun Fragment.showDialogMessage(
    message: String,
    title: String = "温馨提示",
    positiveButtonText: String = "确定",
    positiveAction: () -> Unit = {},
    negativeButtonText: String = "",
    negativeAction: () -> Unit = {}
) {
    activity?.let {
        if (!it.isFinishing) {
            MaterialDialog(it)
                .cancelOnTouchOutside(false)
                .cornerRadius(8f)
                .show {
                    title(text = title)
                    message(text = message)
                    positiveButton(text = positiveButtonText) {
                        positiveAction.invoke()
                    }
                    if (negativeButtonText.isNotEmpty()) {
                        negativeButton(text = negativeButtonText) {
                            negativeAction.invoke()
                        }
                    }
                }
        }
    }
}

fun Activity.showLoadingDialogEx(){
    if (!this.isFinishing){
        if (mLoadingDialog==null){
            mLoadingDialog = LoadingDialog(this)
        }
        mLoadingDialog?.let {
            if (!it.isShowing){
                try {
                    mLoadingDialog?.show()
                }catch (e:Exception){

                }
            }
        }
    }
}

fun Fragment.showLoadingDialogEx(){
    activity?.let {
        if (!it.isFinishing){
            if (mLoadingDialog==null){
                mLoadingDialog = LoadingDialog(it)
            }
            mLoadingDialog?.show()
        }
    }
}

/**
 * 关闭等待框
 */
fun Activity.dismissLoadingExt() {
    mLoadingDialog?.dismiss()
    mLoadingDialog = null
}

/**
 * 关闭等待框
 */
fun Fragment.dismissLoadingExt() {
    mLoadingDialog?.dismiss()
    mLoadingDialog = null
}
