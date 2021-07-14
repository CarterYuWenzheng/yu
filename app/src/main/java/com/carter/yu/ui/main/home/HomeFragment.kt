package com.carter.yu.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import cn.bingoogolapple.bgabanner.BGABanner
import com.carter.baselibrary.base.DataBindingConfig
import com.carter.baselibrary.common.loadUrl
import com.carter.baselibrary.common.setNoRepeatClick
import com.carter.baselibrary.common.smartConfig
import com.carter.baselibrary.common.smartDismiss
import com.carter.yu.BR
import com.carter.yu.R
import com.carter.yu.base.BaseLazyLoadingFragment
import com.carter.yu.common.ArticleAdapter
import com.carter.yu.utils.CacheUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseLazyLoadingFragment(), BGABanner.Adapter<ImageView?, String?>,
    BGABanner.Delegate<ImageView?, String?> {

    private var homeViewModel: HomeViewModel? = null
    private var bannerList: MutableList<BannerBean>? = null
    private val adapter by lazy { ArticleAdapter(mActivity) }


    override fun initViewModel() {
        homeViewModel = getActivityViewModel(HomeViewModel::class.java)
    }

    override fun observe() {
        //文章列表
        homeViewModel?.articleList?.observe(this, Observer {
            smartRefresh.smartDismiss()
            adapter.submitList(it)
            loadingTip?.dismiss()
        })
        //banner
        homeViewModel?.banner?.observe(this, Observer {
            bannerList = it
            initBanner()
        })
        //请求错误
        homeViewModel?.errorLiveData?.observe(this, Observer {
            smartRefresh.smartDismiss()
        })
    }

    override fun lazyInit() {
        initView()
        loadData()
    }

    override fun initView() {
        //关闭更新动画
        (rvHomeList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        smartRefresh.setOnRefreshListener {
            homeViewModel?.getBanner()
            homeViewModel?.getArticle()
        }
        //上拉加载
        smartRefresh.setOnLoadMoreListener {
            homeViewModel?.loadMoreArticle()
        }
        smartRefresh.smartConfig()
        adapter.apply {
            rvHomeList.adapter = this
            setOnItemClickListener { i, _ ->
                nav().navigate(
                    R.id.action_main_fragment_to_web_fragment,
                    this@HomeFragment.adapter.getBundle(i)
                )
            }
            setOnItemChildClickListener { i, view ->
                when (view.id) {
                    //收藏
                    R.id.ivCollect -> {
                        if (CacheUtil.isLogin()) {
                            this@HomeFragment.adapter.currentList[i].apply {
                                //已收藏取消收藏
                                if (collect) {
                                    homeViewModel?.unCollect(id)
                                } else {
                                    homeViewModel?.collect(id)
                                }
                            }
                        } else {
                            nav().navigate(R.id.action_main_fragment_to_login_fragment)
                        }
                    }
                }
            }
        }
        setNoRepeatClick(ivAdd) {
            when (it.id) {
//                R.id.ivAdd -> nav().navigate(R.id.action_main_fragment_to_publish_fragment)
            }
        }
    }

    override fun loadData() {
        //自动刷新
        homeViewModel?.getBanner()
        homeViewModel?.getArticle()
        loadingTip?.loading()
    }

    override fun onClick() {
        setNoRepeatClick(clSearch) {
            when (it.id) {
//                R.id.clSearch -> nav().navigate(R.id.action_main_fragment_to_search_fragment)
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_home

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_home, homeViewModel)
            .addBindingParam(BR.vm, homeViewModel)
    }

    /**
     * 填充banner
     */
    override fun fillBannerItem(
        banner: BGABanner?,
        itemView: ImageView?,
        model: String?,
        position: Int
    ) {
        itemView?.apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            loadUrl(mActivity, bannerList?.get(position)?.imagePath!!)
        }
    }

    /**
     * banner点击事件
     */
    override fun onBannerItemClick(
        banner: BGABanner?,
        itemView: ImageView?,
        model: String?,
        position: Int
    ) {
        nav().navigate(R.id.action_main_fragment_to_web_fragment, Bundle().apply {
            bannerList?.get(position)?.let {
                putString("loadUrl", it.url)
                putString("title", it.title)
                putInt("id", it.id)
            }
        })
    }

    /**
     * 初始化banner
     */
    private fun initBanner() {
        banner.apply {
            setAutoPlayAble(true)
            val views: MutableList<View> = ArrayList()
            bannerList?.forEach { _ ->
                views.add(ImageView(mActivity).apply {
                    setBackgroundResource(R.drawable.ripple_bg)
                })
            }
            setAdapter(this@HomeFragment)
            setDelegate(this@HomeFragment)
            setData(views)
        }
    }

}