package com.carter.yu.ui.play.collect

import androidx.room.*

/**
 * 音频-收藏数据库增删改查
 */
@Dao
interface CollectAudioDao {

    /**
     * 增加一条音频
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudio(audioBean: CollectAudioBean)

    /**
     * 增加多条音频,除了List之外，也可以使用数组
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudios(audioBean: List<CollectAudioBean>)

    /**
     * 删除一条音频
     */
    @Delete
    fun deleteAudio(audioBean: CollectAudioBean)

    /**
     * 删除多条音频
     */
    @Delete
    fun deleteAudios(audioBean: List<CollectAudioBean>)

    /**
     * 更新一条音频
     */
    @Update
    fun updateAudio(audioBean: CollectAudioBean)

    /**
     * 更新多条音频
     */
    @Update
    fun updateAudios(audioBean: List<CollectAudioBean>)


    /**
     * 查询一个
     */
    @Query("SELECT * FROM collect_audio WHERE id=:id")
    fun findAudioById(id: Long): CollectAudioBean?


    /**
     * 返回所有的数据,结果为LiveData
     */
    @Query("SELECT * FROM collect_audio")
    fun getAllAudios(): MutableList<CollectAudioBean>?
}