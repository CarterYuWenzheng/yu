package com.carter.yu.ui.main.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.carter.baselibrary.base.DataBindingConfig
import com.carter.baselibrary.common.initFragment
import com.carter.yu.BR
import com.carter.yu.R
import com.carter.yu.base.LazyFragment
import com.carter.yu.common.TabNavigatorAdapter
import com.carter.yu.view.MagicIndicatorUtils
import kotlinx.android.synthetic.main.fragment_tab.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter

/**
 * 项目 & 公众号 公用
 */
class TabFragment : LazyFragment() {

    /**
     * fragment类型
     */
    private var type = 0

    private var tabViewModel: TabViewModel? = null

    override fun lazyInit() {
        arguments?.apply {
            type = getInt("type")
        }
        loadData()
    }

    override fun initViewModel() {
        tabViewModel = getFragmentViewModel(TabViewModel::class.java)
    }

    override fun initObserver() {
        tabViewModel?.tabLiveData?.observe(this, Observer {
            initViewPager(it)
        })
    }

    private fun initViewPager(tabList: MutableList<TabBean>) {
        vpArticleFragment.initFragment(childFragmentManager, arrayListOf<Fragment>().apply {
            tabList.forEach {
                add(ArticleListFragment().apply {
                    //想各个fragment传递信息
                    val bundle = Bundle()
                    bundle.putInt("type", type)
                    bundle.putInt("tabId", it.id)
                    bundle.putString("name", it.name)
                    arguments = bundle
                })
            }
        })
        //下划线绑定
        val commonNavigator = CommonNavigator(mActivity)
        commonNavigator.adapter = getCommonNavigatorAdapter(tabList)
        tabLayout.navigator = commonNavigator
        MagicIndicatorUtils.bindForViewPager(vpArticleFragment, tabLayout)
    }

    /**
     * 获取下划线根跟字适配器
     */
    private fun getCommonNavigatorAdapter(tabList: MutableList<TabBean>): CommonNavigatorAdapter {
        return TabNavigatorAdapter(mutableListOf<String>().apply {
            //将tab转换为String
            tabList.forEach {
                it.name?.let { it1 -> add(it1) }
            }
        }) {
            vpArticleFragment.currentItem = it
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tab
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_tab, tabViewModel).addBindingParam(
            BR.vm,
            tabViewModel
        )
    }

    private fun loadData() {
        tabViewModel?.getTab(type)
    }
}