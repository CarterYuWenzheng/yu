package com.carter.yu.http

import com.carter.baselibrary.BaseApplication.Companion.getContext
import com.carter.baselibrary.http.HttpLoggingInterceptor
import com.carter.yu.constants.ApiConstants
import com.carter.yu.constants.Constants
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * Retrofit工厂类
 */
object RetrofitFactory {

    private val builder: OkHttpClient.Builder
        get() {
            return OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MICROSECONDS)
                .connectTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.MICROSECONDS)
                .addInterceptor(getLogInterceptor())
                .cookieJar(getCookie())
                .cache(getCache())
        }

    private fun getLogInterceptor(): Interceptor {
        return HttpLoggingInterceptor("OkHttp").apply {
            setPrintLevel(HttpLoggingInterceptor.Level.BODY)
            setColorLevel(Level.INFO)
        }
    }

    private fun getCookie(): ClearableCookieJar {
        return PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(getContext()))
    }

    private fun getCache(): Cache {
        return Cache(File(getContext().cacheDir, "cache"), 1024 * 1024 * 100)
    }

    fun factory(): Retrofit {
        val okHttpClient = builder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.BASE_URL)
            .build()
    }

}