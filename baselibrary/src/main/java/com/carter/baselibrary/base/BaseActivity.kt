package com.carter.baselibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carter.baselibrary.utils.ColorUtils
import com.carter.baselibrary.utils.StatusUtils

abstract class BaseActivity : AppCompatActivity() {

    private var mActivityProvider : ViewModelProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayout()?.let { setContentView(it)}
        setStatusBarColor()
        setSystemInvadeBlack()
        initViewModel()
        initObserver()
        initBase(savedInstanceState)
    }

    /**
     * 入口
     */
    abstract fun initBase(savedInstanceState: Bundle?)

    /**
     * 获取布局
     */
    abstract fun getLayout() : Int?

    /**
     * 状态栏颜色
     */
    open fun setStatusBarColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#00FFFFFF"))
    }

    /**
     * 沉浸式状态
     */
    open fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this, true, true)
    }

    /**
     * 初始化viewModel
     */
    open fun initViewModel() {

    }

    /**
     * 注册观察者
     */
    open fun initObserver() {

    }

    protected fun <T : ViewModel?> getActivityViewModel(viewModelClass : Class<T>):T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider?.get(viewModelClass)
    }
}