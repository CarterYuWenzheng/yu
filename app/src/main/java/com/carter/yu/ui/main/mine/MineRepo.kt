package com.carter.yu.ui.main.mine

import androidx.lifecycle.MutableLiveData
import com.carter.baselibrary.base.BaseRepository
import com.carter.baselibrary.http.ApiException
import com.carter.baselibrary.utils.SPUtils
import com.carter.yu.constants.Constants
import com.carter.yu.http.ApiService
import com.carter.yu.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MineRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    fun getInternal(internalLiveData: MutableLiveData<IntegralBean>) {
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java).getIntegral().data()
            },
            success = {
                SPUtils.setObject(Constants.INTEGRAL_INFO, it)
                internalLiveData.postValue(it)
            }
        )
    }

    suspend fun getInternal(): Flow<IntegralBean> {
        return flow {
            emit(
                RetrofitManager.getApiService(ApiService::class.java).getIntegral().data()
            )
        }.flowOn(Dispatchers.IO)
    }
}