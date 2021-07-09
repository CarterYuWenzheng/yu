package com.carter.yu.ui.main.home

import com.carter.baselibrary.base.BaseRepository
import com.carter.yu.bean.ArticleListBean
import com.carter.yu.http.ApiService
import com.carter.yu.http.RetrofitManager

/**
 * 首页
 */
class HomeRepo : BaseRepository() {

    private var page = 0


    /**
     * 获取置顶文章
     */
    suspend fun getTopArticles() = withIO {
        //请求置顶
        RetrofitManager.getApiService(ApiService::class.java)
            .getTopList()
            .data()
            .let {
                //对模型转换
                ArticleListBean.trans(it)
            }
    }

    /**
     * 请求第一页
     */
    suspend fun getArticles() = withIO {
        page = 0
        RetrofitManager.getApiService(ApiService::class.java)
            .getHomeList(page)
            .data()
            .datas?.let {
                ArticleListBean.trans(it)
            }?: mutableListOf()
    }

    /**
     * 请求第一页
     */
    suspend fun loadMoreArticles() = withIO {
        page++
        RetrofitManager.getApiService(ApiService::class.java)
            .getHomeList(page)
            .data()
            .datas?.let {
                ArticleListBean.trans(it)
            }?: mutableListOf()
    }


    /**
     * 获取banner
     */
    suspend fun getBanner() = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .getBanner()
            .data()
    }

    /**
     * 收藏
     */
    suspend fun collect(id: Int)  = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .collect(id)
            .data(Any::class.java)
            .let {
                id
            }
    }
    /**
     * 取消收藏
     */
    suspend fun unCollect(id: Int) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .unCollect(id)
            .data(Any::class.java)
            .let {
                id
            }
    }
}
