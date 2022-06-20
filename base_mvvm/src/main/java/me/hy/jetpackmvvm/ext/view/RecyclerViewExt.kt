package me.hy.jetpackmvvm.ext.view

import androidx.recyclerview.widget.RecyclerView

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/25
 *     desc  :
 *
 * </pre>
 */


//绑定普通的Recyclerview
fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager?,
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true
): RecyclerView {
    layoutManger?.let {
        layoutManager = layoutManger
    }
    setHasFixedSize(true)
    adapter = bindAdapter
    isNestedScrollingEnabled = isScroll
    return this
}