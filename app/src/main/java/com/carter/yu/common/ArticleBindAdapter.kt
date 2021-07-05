package com.carter.yu.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.carter.yu.R

/**
 * 文章列表dataBinding适配器
 */
object ArticleBindAdapter {

    /**
     * 加载图片,做高斯模糊处理
     */
    @BindingAdapter(value = ["articleCollect"])
    @JvmStatic
    fun imgPlayBlur(view: ImageView, collect: Boolean) {
        if (collect) {
            view.setImageResource(R.mipmap._collect)
        } else {
            view.setImageResource(R.mipmap.un_collect)
        }
    }

}