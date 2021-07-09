package com.carter.yu.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.carter.baselibrary.BaseApplication
import com.carter.baselibrary.utils.toast
import com.carter.yu.ui.play.collect.CollectAudioBean
import com.carter.yu.ui.play.collect.CollectAudioDao
import com.carter.yu.ui.play.history.HistoryAudioBean
import com.carter.yu.ui.play.history.HistoryAudioDao

@Database(
    entities = [HistoryAudioBean::class, CollectAudioBean::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    /**
     * 获取HistoryAudioDao
     */
    abstract fun historyDao(): HistoryAudioDao

    /**
     * 获取CollectAudioBean
     */
    abstract fun collectDao(): CollectAudioDao


    companion object{
        @Volatile
        private var instance:AppDataBase? = null

        fun getInstance():AppDataBase{
            return instance?: synchronized(this){
                instance?:buildDataBase(BaseApplication.getContext())
                    .also {
                        instance = it
                    }
            }
        }

        private fun buildDataBase(context: Context):AppDataBase{
            return Room
                .databaseBuilder(context,AppDataBase::class.java,"yu-database")
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .build()
        }
    }

}