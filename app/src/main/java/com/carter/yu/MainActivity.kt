package com.carter.yu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.carter.baselibrary.utils.SPUtils
import com.carter.baselibrary.utils.StatusUtils
import com.carter.yu.base.BaseLoadingActivity
import com.carter.yu.constants.Constants
import com.carter.yu.ui.MainFragment

/**
 * 主页面
 * 1. 承载Fragment
 * 2. 音视频的观察者，接收到通知立即更新ViewModel，间接通知DataBinding更新view
 */
class MainActivity : BaseLoadingActivity() {

    private var playVM: PlayViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        changeTheme()
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel() {
        playVM = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun init(savedInstanceState: Bundle?) {
        TODO("PlayManager")
    }

    /**
     * 动态切换主题
     */
    private fun changeTheme() {
        val theme = SPUtils.getBoolean(Constants.SP_THEME_KEY,false)
        if (theme) {
            setTheme(R.style.AppTheme_Night)
        } else {
            setTheme(R.style.AppTheme)
        }
    }

    /**
     * 随主题修改沉浸式状态
     */
    override fun setSystemInvadeBlack() {
        val theme = SPUtils.getBoolean(Constants.SP_THEME_KEY,false)
        if (theme) {
            StatusUtils.setSystemStatus(this,true,false)
        } else {
            StatusUtils.setSystemStatus(this,true,true)
        }
    }

    /**
     * 返回键
     */
    override fun onBackPressed() {
        val mainHostFragment:Fragment? = supportFragmentManager.findFragmentById(R.id.host_fragment)
        val fragment = mainHostFragment?.childFragmentManager?.primaryNavigationFragment
        if (fragment is MainFragment) {
            //退出Activity但不销毁
            moveTaskToBack(false)
        } else {
            super.onBackPressed()
        }
    }
}
