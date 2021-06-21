package com.carter.baselibrary

import android.app.Application
import android.content.Context

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
    }

    companion object {
        private lateinit var baseApplication: BaseApplication
        fun getContext(): Context {
            return baseApplication
        }
    }

}