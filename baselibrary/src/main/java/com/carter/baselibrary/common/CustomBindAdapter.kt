package com.carter.baselibrary.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * 自定义DataBinding适配器
 */
object CustomBindAdapter {

    /**
     * 加载资源图片
     */
    @BindingAdapter(value = ["imgSrc"])
    @JvmStatic
    fun imgSrc(view: ImageView, id: Int) {
        view.setImageResource(id)
    }

    /**
     * 加载图片
     */
    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: String) {
        view.loadUrl(view.context.applicationContext,url)
    }

    /**
     * 加载本地圆形图片
     */
    @BindingAdapter(value = ["imgUriCircle"])
    @JvmStatic
    fun imgUriCircle(view: ImageView, albumId: Long) {
        if (albumId == -1L){
            view.setImageResource(0)
            return
        }
        view.loadCircle(view.context.applicationContext, albumById(albumId))
    }

    /**
     * 加载网络圆角图片
     */
    @BindingAdapter(value = ["imgUrlRadius"])
    @JvmStatic
    fun imgUrlRadiusCircle(view: ImageView, url: String) {
        view.loadRadius(view.context.applicationContext, url)
    }

    @BindingAdapter(value = ["visible"])
    @JvmStatic
    fun visible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

}