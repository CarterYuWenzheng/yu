package com.carter.baselibrary.play

/**
 * 播放状态,需要注入到播放控制器中,用于播放状态的回调
 */
interface IPlayerStatus {

    /**
     * 缓冲更新
     */
    fun onBufferingUpdate(percent: Int)

    /**
     * 播放结束
     */
    fun onComplete()

}