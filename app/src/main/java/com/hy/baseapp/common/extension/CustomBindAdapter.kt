package com.hy.baseapp.common.extension

import android.os.SystemClock
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.ClickUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.hy.baseapp.R
import com.hy.baseapp.base.event.appInstance
import me.hy.jetpackmvvm.ext.util.dp2px
import me.hy.jetpackmvvm.ext.view.EXPAND_DEFAULT_SIZE
import me.hy.jetpackmvvm.ext.view.click
import me.hy.jetpackmvvm.ext.view.textString

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 自定义 BindingAdapter
 */
object CustomBindAdapter {

    @BindingAdapter(value = ["checkChange"])
    @JvmStatic
    fun checkChange(checkbox: CheckBox, listener: CompoundButton.OnCheckedChangeListener) {
        checkbox.setOnCheckedChangeListener(listener)
    }

    /**
     * 扩大点击范围
     */
//    @BindingAdapter(value = ["expandTouchArea"])
//    @JvmStatic
//    fun expand(view: View, expand: Boolean) {
//        if (expand) view.expandTouchArea()
//    }

    @BindingAdapter(value = ["showPwd"])
    @JvmStatic
    fun showPwd(view: EditText, boolean: Boolean) {
        if (boolean) {
            view.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            view.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        view.setSelection(view.textString().length)
    }

    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: String?) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }

    @BindingAdapter(value = ["imageUrlCorner"])
    @JvmStatic
    fun imageUrlCorner(view: ImageView, url: String?) {
        Glide.with(appInstance)
            .load(url)
            .placeholder(R.drawable.glide_holder)
            .transform(RoundedCorners(8.dp2px()))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions().crossFade(500)).into(view)
    }


    /**
     * 圆形头像
     */
    @BindingAdapter(value = ["headCircleImageUrl"])
    @JvmStatic
    fun headCircleImageUrl(view: ImageView, url: String?) {
        if (url == null) return
        val options = RequestOptions().apply {
            diskCacheStrategy(DiskCacheStrategy.DATA)
            transform(CircleCrop())
        }
        Glide.with(appInstance)
            .load(url)
            .apply(options)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .thumbnail()
            .into(view)
    }

    @BindingAdapter(value = ["thumbnailImageUrl"])
    @JvmStatic
    fun thumbnailImageUrl(view: ImageView, url: String?) {
        val options = RequestOptions().apply {
            diskCacheStrategy(DiskCacheStrategy.DATA)
            placeholder(R.drawable.glide_holder)
        }
        Glide.with(appInstance)
            .load(url)
            .thumbnail(0.5f)
            .apply(options)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(view)
    }


    @BindingAdapter(value = ["circleImageUrl"])
    @JvmStatic
    fun circleImageUrl(view: ImageView, url: String) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }

/*
    @BindingAdapter(value = ["colorCircleViewColor"])
    @JvmStatic
    fun colorCircleViewColor(view: MyColorCircleView, color: Int) {
        view.setView(color)
    }
*/

    @BindingAdapter(value = ["afterTextChanged"])
    @JvmStatic
    fun EditText.afterTextChanged(action: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                action(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

/*    @BindingAdapter(value = ["noRepeatClick"])
    @JvmStatic
    fun setOnClick(view: View, clickListener: () -> Unit) {
        val mHits = LongArray(2)
        view.setOnClickListener {
            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
            mHits[mHits.size - 1] = SystemClock.uptimeMillis()
            if (mHits[0] < SystemClock.uptimeMillis() - 500) {
                clickListener.invoke()
            }
        }
    }*/

    /**
     *  Sets the value of the progress bar so that 5 likes will fill it up.
     *
     *  Showcases Binding Adapters with multiple attributes. Note that this adapter is called
     *  whenever any of the attribute changes.
     */
    @BindingAdapter(value = ["bind_progressScaled", "bind_max"], requireAll = true)
    @JvmStatic
    fun setProgress(progressBar: ProgressBar, likes: Int, max: Int) {
        progressBar.progress = (likes * max / 5).coerceAtMost(max)
    }

    @BindingAdapter(value = ["noRepeatClick"])
    @JvmStatic
    fun setOnClick(view: View, clickListener: CustomBindClickListener?) {
        val mHits = LongArray(2)
        view.setOnClickListener {
            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
            mHits[mHits.size - 1] = SystemClock.uptimeMillis()
            if (mHits[0] < SystemClock.uptimeMillis() - 500) {
                clickListener?.onClick()
            }
        }
    }

    @BindingAdapter(value = ["throttleClick"])
    @JvmStatic
    fun setThrottleOnClick(view: View, clickListener: CustomBindClickListener?) {
        view.click{
            clickListener?.onClick()
        }
    }

    interface CustomBindClickListener {
        fun onClick()
    }

    /**
     * 扩大点击范围
     * 默认30
     */
    @BindingAdapter(value = ["expandTouchArea"])
    @JvmStatic
    fun expandTouchArea(view: View, expand: Boolean) {
        if (expand) {
            ClickUtils.expandClickArea(view, ::EXPAND_DEFAULT_SIZE.get())
        }
    }
}