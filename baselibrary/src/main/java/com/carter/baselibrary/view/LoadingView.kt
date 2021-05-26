package com.carter.baselibrary.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.loading_view.view.*

/**
 * 加载视图
 */
class LoadingView : RelativeLayout {

    constructor(context: Context?) : super(context) {
        initView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    private fun initView(context: Context?) {
        visibility = GONE
    }

    /**
     * 网络重连点击事件
     */
    fun setReloadListener(reload: (View) -> Unit) {
        ll_internetError.setOnClickListener {
            reload.invoke(it)
        }
    }

    /**
     * 显示空白页
     */
    fun showEmpty() {
        visibility = VISIBLE
        ll_internetError.visibility = GONE
        ll_empty.visibility = VISIBLE
        indicator_view.visibility = GONE
        indicator_view.hide()
    }

    /**
     * 显示网络错误
     */
    fun showInternetError() {
        visibility = VISIBLE
        ll_internetError.visibility = VISIBLE
        ll_empty.visibility = GONE
        indicator_view.visibility = GONE
        indicator_view.hide()
    }

    /**
     * 显示loading
     */
    fun showLoading() {
        visibility = VISIBLE
        ll_internetError.visibility = GONE
        ll_empty.visibility = GONE
        indicator_view.visibility = VISIBLE
        indicator_view.show()
    }

    /**
     * 隐藏LoadingView
     */
    fun dismiss() {
        indicator_view.hide()
        visibility = GONE
    }
}