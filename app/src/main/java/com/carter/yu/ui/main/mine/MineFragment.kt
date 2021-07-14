package com.carter.yu.ui.main.mine

import android.os.Bundle
import com.carter.baselibrary.base.DataBindingConfig
import com.carter.baselibrary.common.setNoRepeatClick
import com.carter.yu.BR
import com.carter.yu.R
import com.carter.yu.base.LazyFragment
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : LazyFragment() {

    private var mineVM: MineViewModel? = null

    override fun lazyInit() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_mine, mineVM).addBindingParam(BR.vm, mineVM)
    }

    override fun initViewModel() {
        mineVM = getFragmentViewModel(MineViewModel::class.java)
    }

    override fun observe() {
        super.observe()
    }

    override fun onClick() {
        setNoRepeatClick(
            ivHead,
            tvName,
            tvId,
            llHistory,
            llRanking,
            clIntegral,
            clCollect,
            clArticle,
            clWebsite,
            clSet
        ) {
            when (it.id) {
                //头像
                R.id.ivHead -> {
                }
                //用户名
                R.id.tvName -> {
                }
                //历史
                R.id.llHistory -> {
                }
                //排名
                R.id.llRanking -> {
                }
                //积分
                R.id.clIntegral -> {
                }
                //我的收藏
                R.id.clCollect -> {
                }
                //我的文章
                R.id.clArticle -> {
                }
                //官网
                R.id.clWebsite -> {
                }
                //设置
                R.id.clSet -> {
                    startActivity(FlutterActivity.withNewEngine().initialRoute("").build(mContext))
                }
            }
        }
    }
}