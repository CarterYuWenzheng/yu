package com.carter.yu.base

import android.content.Context
import android.view.ViewGroup
import com.carter.baselibrary.utils.StatusUtils
import com.carter.baselibrary.view.LoadingView

abstract class BaseLazyLoadingFragment : LazyFragment() {

    protected var gloding: LoadingView? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseLoadingActivity) {
            gloding = context.loadingView
        }
    }

    /**
     * 设置loadingView上下变局
     */
    protected fun setLoadingMargin(topMargin: Int, bottomMargin: Int) {
        val loadMarginTop = StatusUtils.getStatusBarHeight(mActivity) +topMargin
        val loadMarginBottom =  StatusUtils.getNavigationBarHeight(mActivity) + bottomMargin
        val params = gloding?.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = loadMarginTop
        params.bottomMargin = loadMarginBottom
        gloding?.layoutParams = params
    }
}