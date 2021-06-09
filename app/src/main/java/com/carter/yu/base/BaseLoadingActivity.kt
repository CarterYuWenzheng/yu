package com.carter.yu.base

import android.os.Bundle
import android.widget.FrameLayout
import com.carter.baselibrary.base.BaseActivity
import com.carter.baselibrary.common.dip2px
import com.carter.baselibrary.utils.StatusUtils
import com.carter.baselibrary.view.LoadingView

abstract class BaseLoadingActivity : BaseActivity() {

    private var decorView: FrameLayout? = null
    var loadingView: LoadingView? = null

    override fun initBase(savedInstanceState: Bundle?){
        decorView = window.decorView as FrameLayout
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        val loadMarginTop = StatusUtils.getStatusBarHeight(this) + dip2px(this, 50f)
        val loadMarginBottom =  StatusUtils.getNavigationBarHeight(this)
        params.topMargin = loadMarginTop
        params.bottomMargin = loadMarginBottom
        loadingView = LoadingView(this)
        decorView?.addView(loadingView,params)
        init(savedInstanceState)
    }

    abstract fun init(savedInstanceState: Bundle?)
}