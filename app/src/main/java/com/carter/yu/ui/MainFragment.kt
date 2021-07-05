package com.carter.yu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.carter.baselibrary.base.BaseFragment
import com.carter.baselibrary.base.DataBindingConfig
import com.carter.yu.BR
import com.carter.yu.PlayViewModel
import com.carter.yu.R
import com.carter.yu.constants.Constants
import com.carter.yu.ui.main.home.HomeFragment
import com.carter.yu.ui.main.mine.MineFragment
import com.carter.yu.ui.main.square.SquareFragment
import com.carter.yu.ui.main.tab.TabFragment

/**
 * 主Fragment
 */
class MainFragment : BaseFragment() {

    private var fragmentList = arrayListOf<Fragment>()

    /**
     * 首页
     */
    private val homeFragment by lazy { HomeFragment() }

    /**
     * 项目
     */
    private val projectFragment by lazy {
        TabFragment().apply {
            arguments = Bundle().apply {
                putInt("type", Constants.PROJECT_TYPE)
            }
        }
    }

    /**
     * 广场
     */
    private val squareFragment by lazy { SquareFragment() }

    /**
     * 公众号
     */
    private val wxChatFragment by lazy {
        TabFragment().apply {
            arguments = Bundle().apply {
                putInt("type", Constants.ACCOUNT_TYPE)
            }
        }
    }

    /**
     * 我
     */
    private val mineFragment by lazy { MineFragment() }

    private var playViewModel: ViewModel? = null

    init {
        fragmentList.apply {
            add(homeFragment)
            add(projectFragment)
            add(squareFragment)
            add(wxChatFragment)
            add(mineFragment)
        }
    }

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