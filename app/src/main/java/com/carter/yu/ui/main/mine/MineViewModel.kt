package com.carter.yu.ui.main.mine

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.carter.yu.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MineViewModel : BaseViewModel() {

    /**
     * 用户名
     */
    val username = ObservableField<String>().apply {
        set("请先登录")
    }

    /**
     * id
     */
    val id = ObservableField<String>().apply {
        set("---")
    }

    /**
     * 排名
     */
    val rank = ObservableField<String>().apply {
        set("0")
    }

    /**
     * 当前积分
     */
    val internal = ObservableField<String>().apply {
        set("0")
    }

    private val repo by lazy { MineRepo(viewModelScope, errorLiveData) }

    private val internalLiveData = MutableLiveData<IntegralBean>()

    fun getInternal() {
        repo.getInternal(internalLiveData)
    }

    fun getFlowInternal() {
        viewModelScope.launch {
            repo.getInternal()
                .catch {
                    handleError(it)
                }
                .collect {
                    internalLiveData.postValue(it)
                }
        }
    }
}