package com.carter.yu

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carter.baselibrary.common.stringForTime
import com.carter.yu.db.AppDataBase
import com.carter.yu.play.PlayList
import com.carter.yu.play.PlayerManager
import com.carter.yu.play.bean.AudioBean
import com.carter.yu.play.bean.ProgressBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayViewModel:ViewModel() {

    /**
     * 歌名
     */
    val songName = ObservableField<String>("暂无播放")

    /**
     * 歌手
     */
    val singer = ObservableField<String>("")

    /**
     * 专辑图片
     */
    val albumPic = ObservableField<Long>()

    /**
     * 播放状态
     */
    val playStatus = ObservableField<Int>()

    /**
     * 图片播放模式
     */
    val playModePic = ObservableField<Int>(R.mipmap.play_order)

    /**
     * 列表图片播放模式(夜间模式)
     */
    val listPlayModePic = ObservableField<Int>(R.mipmap.play_order_gray)

    /**
     * 文字播放模式
     */
    val playModeText = ObservableField<String>("列表循环")

    /**
     * 总播放时长-文本
     */
    val maxDuration = ObservableField<String>("00:00")

    /**
     * 当前播放时长-文本
     */
    val currentDuration = ObservableField<String>("00:00")

    /**
     * 总长度
     */
    val maxProgress = ObservableField<Int>()

    /**
     * 播放进度
     */
    val playProgress = ObservableField<Int>()

    /**
     * 播放进度
     */
    val collect = ObservableField<Boolean>()

    /**
     * 重置
     */
    fun reset() {
        songName.set("")
        singer.set("")
        albumPic.set(-1)
        playStatus.set(PlayerManager.PAUSE)
        maxDuration.set("00:00")
        currentDuration.set("00:00")
        maxProgress.set(0)
        playProgress.set(0)
        collect.set(false)
    }

    /**
     * 更换音频
     */
    fun setAudioBean(audioBean: AudioBean) {
        songName.set(audioBean.name)
        singer.set(audioBean.singer)
        maxDuration.set(stringForTime(audioBean.duration))
        maxProgress.set(audioBean.duration)
        albumPic.set(audioBean.albumId)
        viewModelScope.launch {
            val bean = withContext(Dispatchers.IO) {
                AppDataBase.getInstance()
                    .collectDao()
                    .findAudioById(audioBean.id)
            }
            collect.set(bean != null)
        }
    }

    /**
     * 更换进度
     */
    fun setProgress(progressBean: ProgressBean) {
        currentDuration.set(stringForTime(progressBean.currentDuration))
        playProgress.set(progressBean.currentDuration)
    }

    /**
     * 更换模式
     */
    fun setPlayMode(playMode: Int) {
        when (playMode) {
            PlayList.PlayMode.ORDER_PLAY_MODE -> {
                playModePic.set(R.mipmap.play_order)
                listPlayModePic.set(R.mipmap.play_order_gray)
                playModeText.set("列表循环")
            }
            PlayList.PlayMode.SINGLE_PLAY_MODE -> {
                playModePic.set(R.mipmap.play_single)
                listPlayModePic.set(R.mipmap.play_single_gray)
                playModeText.set("单曲循环")
            }
            PlayList.PlayMode.RANDOM_PLAY_MODE -> {
                playModePic.set(R.mipmap.play_random)
                listPlayModePic.set(R.mipmap.play_random_gray)
                playModeText.set("随机播放")
            }
        }
    }
}