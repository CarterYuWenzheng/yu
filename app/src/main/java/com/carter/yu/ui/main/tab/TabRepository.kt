package com.carter.yu.ui.main.tab

import com.carter.baselibrary.base.BaseRepository
import com.carter.yu.constants.Constants
import com.carter.yu.http.ApiService
import com.carter.yu.http.RetrofitFactory
import com.carter.yu.http.RetrofitManager

class TabRepository : BaseRepository() {

    suspend fun getLiveData(type: Int) = withIO {
        if (type == Constants.PROJECT_TYPE) {
            RetrofitManager.getApiService(ApiService::class.java).getProjectTabList().data()
        } else {
            RetrofitManager.getApiService(ApiService::class.java).getAccountTabList().data()
        }
    }
}