package com.carter.yu.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.carter.baselibrary.base.BaseFragment
import com.carter.baselibrary.base.DataBindingConfig
import com.carter.yu.BR
import com.carter.yu.PlayViewModel
import com.carter.yu.R

/**
 * 主Fragment
 */
class MainFragment : BaseFragment() {

    private var playViewModel: ViewModel? = null

    override fun initViewModel() {
        playViewModel = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun initBase(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_main, playViewModel).addBindingParam(
            BR.vm,
            playViewModel
        )
    }

}