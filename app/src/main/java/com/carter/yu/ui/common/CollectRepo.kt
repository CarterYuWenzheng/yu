package com.carter.yu.ui.common

import com.carter.baselibrary.base.BaseRepository
import com.carter.yu.http.ApiService
import com.carter.yu.http.RetrofitManager

class CollectRepo: BaseRepository() {
    /**
     * 收藏
     */
    suspend fun collect(id: Int)  = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .collect(id)
            .data(Any::class.java)
            .let {
                id
            }
    }
    /**
     * 取消收藏
     */
    suspend fun unCollect(id: Int) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .unCollect(id)
            .data(Any::class.java)
            .let {
                id
            }
    }
}