package com.carter.yu

import android.content.Context
import androidx.multidex.MultiDex
import com.carter.baselibrary.BaseApplication
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MyApplication : BaseApplication() {

    private var  fe:FlutterEngine? =null

    override fun onCreate() {
        super.onCreate()
        initSmartHead()
        MultiDex.install(this)

        //Flutter引擎
        fe = FlutterEngine(this)
        fe?.navigationChannel?.setInitialRoute("page2")
        fe?.dartExecutor?.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        FlutterEngineCache.getInstance().put("cache_engine",fe)
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

    override fun onTerminate() {
        super.onTerminate()
        fe?.destroy()
    }
}