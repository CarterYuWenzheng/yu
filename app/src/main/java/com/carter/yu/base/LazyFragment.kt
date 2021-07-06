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
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    override fun initBase(savedInstanceState: Bundle?) {
    }

    abstract fun lazyInit()
}