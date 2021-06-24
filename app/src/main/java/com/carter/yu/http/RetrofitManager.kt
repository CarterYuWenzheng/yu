package com.carter.yu.http

/**
 * 初始化Retrofit，维护多个ApiService单例对象
 */
class RetrofitManager {
    companion object {
        /**
         * 存储ApiService
         */
        private val map = mutableMapOf<Class<*>, Any>()

        /**
         * Retrofit单例
         */
        private val retrofit = RetrofitFactory.factory()

        fun <T : Any> getApiService(apiClass: Class<T>): T {
            return getService(apiClass)
        }

        private fun <T : Any> getService(apiClass: Class<T>): T {
            //重入锁单例避免多线程安全问题
            return if (map[apiClass] == null) {
                synchronized(RetrofitManager::class.java) {
                    val t = retrofit.create(apiClass)
                    if (map[apiClass] == null) {
                        map[apiClass] = t
                    }
                    t
                }
            } else {
                map[apiClass] as T
            }
        }
    }
}