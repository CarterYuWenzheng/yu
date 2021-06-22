package com.carter.yu.view

import android.animation.ValueAnimator
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.carter.baselibrary.common.albumById
import com.carter.baselibrary.common.clickNoRepeat
import com.carter.baselibrary.common.dip2px
import com.carter.baselibrary.common.loadCircle
import com.carter.baselibrary.utils.ScreenUtils
import com.carter.yu.R
import kotlinx.android.synthetic.main.play_float_layout.view.*
import kotlin.math.abs

/**
 * 自定义view
 * description：悬浮播放按钮
 */
class FloatPlayLayout : LinearLayout {

    /**
     * 是否展开
     */
    private var isOpen = false

    /**
     * content宽度
     */
    private val contentWidth = dip2px(context, 180f)


    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    private fun initView(context: Context) {
        View.inflate(context, R.layout.play_float_layout, this)
        //居中显示
        gravity = Gravity.CENTER
        onClick()
    }

    /**
     * 点击事件
     */
    private fun onClick() {
        //点击音乐图片，收缩状态展开动画
        ivMusicPic.clickNoRepeat {
            if (!isOpen) {
                startAnim()
                ivMusicPic.isEnabled = false
                isOpen = true
            }
        }

        //点击收起按钮，收缩
        ivShrink.clickNoRepeat {
            if (isOpen) {
                startAnim()
                ivMusicPic.isEnabled = true
                isOpen = false
            }
        }
    }

    /**
     * 开启动画
     */
    private fun startAnim() {
        val animator = if (isOpen) {
            ValueAnimator.ofInt(contentWidth, 0)
        } else {
            ValueAnimator.ofInt(0, contentWidth)
        }
        //动画时长，尽量与防点击抖动间隔相同
        animator.duration = 249
        animator.addUpdateListener {
            val value = it.animatedValue as Int
            //动态设置宽度
            val params = llContent.layoutParams as MarginLayoutParams
            params.width = value
            llContent.layoutParams = params
        }
        animator.start()
    }

    private var moveY = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                moveY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(ev.y - moveY) > 100) {
                    return true
                }
            }
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                var offsetY = translationY + (event.y - moveY)
                //最小值
                if (offsetY < -top + dip2px(context, 100f)) {
                    offsetY = -top.toFloat() + dip2px(context, 100f)
                }
                //最大值
                if (offsetY > ScreenUtils.getScreenHeight(context) - top - dip2px(context, 150f)) {
                    offsetY =
                        ScreenUtils.getScreenHeight(context) - top.toFloat() - dip2px(context, 150f)
                }
                translationY = offsetY
                return true
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    /**
     * 点击播放
     */
    fun playClick(onClick: (View) -> Unit) {
        ivPlaying.clickNoRepeat {
            onClick.invoke(it)
        }
    }

    /**
     * 点击悬浮窗
     */
    fun rootClick(onClick: (View) -> Unit) {
        root.clickNoRepeat {
            onClick.invoke(it)
        }
    }

    /**
     * 设置播放状态
     */
    fun setImgPlaying(isPlaying: Boolean?) {
        isPlaying?.apply {
            ivPlaying.isSelected = this
        }
    }

    /**
     * 设置歌名
     */
    fun setSongName(songName: String?) {
        if (TextUtils.isEmpty(songName)) {
            tvSongName.text = "暂无播放"
        } else {
            tvSongName.text = songName
        }
        songName?.apply {
        }
    }

    /**
     * 设置专辑图片
     */
    fun setAlbumPic(albumId: Long?) {
        //收到重置
        if (albumId == -1L) {
            ivMusicPic.setImageResource(R.drawable.svg_music_not)
            return
        }
        albumId?.apply {
            ivMusicPic.loadCircle(context.applicationContext, albumById(this))
        }
    }
}