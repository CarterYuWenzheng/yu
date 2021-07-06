package com.carter.yu.ui.main.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carter.yu.base.BaseViewModel

class TabViewModel : BaseViewModel() {

    private val repo by lazy { TabRepository() }

    private val _tabLiveData = MutableLiveData<MutableList<TabBean>>()
    val tabLiveData: LiveData<MutableList<TabBean>> = _tabLiveData

    fun getTab(type: Int) {
        launch {
            _tabLiveData.value = repo.getLiveData(type)
        }
    }
}