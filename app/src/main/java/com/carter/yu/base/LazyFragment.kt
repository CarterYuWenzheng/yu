package com.carter.yu.base

import android.os.Bundle
import com.carter.baselibrary.base.BaseFragment

/**
 * 基于AndroidX实现懒加载
 */
abstract class LazyFragment : BaseFragment() {

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()
}