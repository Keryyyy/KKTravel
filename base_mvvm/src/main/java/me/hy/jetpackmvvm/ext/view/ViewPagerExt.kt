package me.hy.jetpackmvvm.ext.view

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/25
 *     desc  :
 *
 * </pre>
 */

fun ViewPager.init(
    views: List<View>,
): ViewPager {
    this.offscreenPageLimit = views.size
    //设置适配器
    adapter = object : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return views.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = views[position]
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(views[position])
        }
    }
    return this
}