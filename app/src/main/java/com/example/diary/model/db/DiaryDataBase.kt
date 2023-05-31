package com.example.diary.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diary.model.entity.Diary
import com.example.diary.model.dao.DiaryDao

@Database(entities = [Diary::class], version = 1, exportSchema = false)
abstract class DiaryDataBase: RoomDatabase() {
    abstract fun DiaryO(): DiaryDao
    companion object {
        var INSTANCE: DiaryDataBase? = null
        fun getInstance(context: Context): DiaryDataBase? {
            if (INSTANCE == null) {
                synchronized(DiaryDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DiaryDataBase::class.java, "user.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}