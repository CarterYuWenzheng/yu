package com.carter.yu.ui.play.history

import androidx.room.*

@Dao
interface HistoryAudioDao {

    /**
     * 增加一条音频
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudio(audioBean: HistoryAudioBean)

    /**
     * 增加多条音频,除了List之外，也可以使用数组
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudios(audioBean: MutableList<HistoryAudioBean>)

    /**
     * 删除一条音频
     */
    @Delete
    fun deleteAudio(audioBean: HistoryAudioBean)

    /**
     * 删除多条音频
     */
    @Delete
    fun deleteAudios(audioBean: MutableList<HistoryAudioBean>)

    /**
     * 更新一条音频
     */
    @Update
    fun updateAudio(audioBean: HistoryAudioBean)

    /**
     * 更新多条音频
     */
    @Update
    fun updateAudios(audioBean: MutableList<HistoryAudioBean>)


    /**
     * 查询一个
     */
    @Query("SELECT * FROM history_audio WHERE id=:id")
    fun findAudioById(id: Long): HistoryAudioBean?

    /**
     * 返回所有的数据,结果为LiveData
     */
    @Query("SELECT * FROM history_audio")
    fun getAllAudios(): MutableList<HistoryAudioBean>?
}