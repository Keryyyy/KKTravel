@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package me.hy.jetpackmvvm.ext.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * 关于ViewPager2的扩展
 * overScrollMode相关扩展主要是为了解决ViewPager2自带的方法没有效果的问题
 *
 * @author Qu Yunshuo
 * @since 2021/8/1 4:02 下午
 */

/**
 * 设置ViewPager2的过度滚动模式为绝不允许用户过度滚动此视图
 * @receiver ViewPager2
 */
@kotlin.internal.InlineOnly
inline fun ViewPager2.setOverScrollModeToNever() {
    val childView: View = this.getChildAt(0)
    if (childView is RecyclerView) {
        childView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}

/**
 * 设置ViewPager2的过度滚动模式为始终允许用户过度滚动此视图，前提是它是可以滚动的视图
 * @receiver ViewPager2
 */
@kotlin.internal.InlineOnly
inline fun ViewPager2.setOverScrollModeToAlways() {
    val childView: View = this.getChildAt(0)
    if (childView is RecyclerView) {
        childView.overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
    }
}

/**
 * 设置ViewPager2的过度滚动模式为仅当内容大到足以有意义地滚动时，才允许用户过度滚动此视图，前提是它是可以滚动的视图。
 * @receiver ViewPager2
 */
@kotlin.internal.InlineOnly
inline fun ViewPager2.setOverScrollModeToIfContentScrolls() {
    val childView: View = this.getChildAt(0)
    if (childView is RecyclerView) {
        childView.overScrollMode = RecyclerView.OVER_SCROLL_IF_CONTENT_SCROLLS
    }
}



fun ViewPager2.init(
    fragment: Fragment,
    fragments: ArrayList<Fragment>,
    isUserInputEnabled: Boolean = true
): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = isUserInputEnabled
    //设置适配器
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
    return this
}


fun ViewPager2.init(
    activity: AppCompatActivity,
    fragments: ArrayList<Fragment>,
    isUserInputEnabled: Boolean = true
): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = isUserInputEnabled
    this.offscreenPageLimit = fragments.size

    //设置适配器
    adapter = object : FragmentStateAdapter(activity.supportFragmentManager,activity.lifecycle) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
    return this
}

fun ViewPager2.init(
    activity: AppCompatActivity,
    fragments: MutableList<Fragment>,
    isUserInputEnabled: Boolean = true
): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = isUserInputEnabled
    this.offscreenPageLimit = fragments.size

    //设置适配器
    adapter = object : FragmentStateAdapter(activity.supportFragmentManager,activity.lifecycle) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
    return this
}
