package com.carter.yu

import android.content.Context
import androidx.multidex.MultiDex
import com.carter.baselibrary.BaseApplication
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

class MyApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        initSmartHead()
        MultiDex.install(this)
    }

    /**
     * 初始化加载刷新UI
     */
    private fun initSmartHead() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, _: RefreshLayout? ->
            ClassicsHeader(
                context
            )
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, _: RefreshLayout? ->
            ClassicsFooter(
                context
            )
        }
    }
}