package com.carter.yu.base

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.carter.baselibrary.base.BaseFragment
import com.carter.baselibrary.utils.StatusUtils
import com.carter.baselibrary.view.LoadingView

abstract class BaseLoadingFragment : BaseFragment(){

    private var mLoadingView:LoadingView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseLoadingActivity){
            mLoadingView = context.loadingView
        }
    }

    protected fun setLoadingMargin(topMargin: Int, bottomMargin: Int) {
        val loadMarginTop = StatusUtils.getStatusBarHeight(mActivity) +topMargin
        val loadMarginBottom =  StatusUtils.getNavigationBarHeight(mActivity) + bottomMargin
        val params = mLoadingView?.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = loadMarginTop
        params.bottomMargin = loadMarginBottom
        mLoadingView?.layoutParams = params
    }

}