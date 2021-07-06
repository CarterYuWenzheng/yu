package com.carter.yu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.carter.baselibrary.base.BaseFragment
import com.carter.baselibrary.base.DataBindingConfig
import com.carter.baselibrary.common.doSelected
import com.carter.baselibrary.common.initFragment
import com.carter.yu.BR
import com.carter.yu.PlayViewModel
import com.carter.yu.R
import com.carter.yu.constants.Constants
import com.carter.yu.ui.main.home.HomeFragment
import com.carter.yu.ui.main.mine.MineFragment
import com.carter.yu.ui.main.square.SquareFragment
import com.carter.yu.ui.main.tab.TabFragment
import kotlinx.android.synthetic.main.fragment_main.*

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
        //初始化viewpager2
        vpHome.initFragment(childFragmentManager, fragmentList).run {
            //全部缓存,避免切换回重新加载
            offscreenPageLimit = fragmentList.size
        }

        vpHome.doSelected {
            btnNav.menu.getItem(it).isChecked = true
        }
        //初始化底部导航栏
        btnNav.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> vpHome.setCurrentItem(0, false)
                    R.id.menu_project -> vpHome.setCurrentItem(1, false)
                    R.id.menu_square -> vpHome.setCurrentItem(2, false)
                    R.id.menu_official_account -> vpHome.setCurrentItem(3, false)
                    R.id.menu_mine -> vpHome.setCurrentItem(4, false)
                }
                // 这里注意返回true,否则点击失效
                true
            }
        }
    }

    override fun initOnClick() {
        floatLayout.playClick {

        }
        floatLayout.rootClick {

        }
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