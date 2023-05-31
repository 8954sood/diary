package com.example.diary.model.dao

import androidx.room.*
import com.example.diary.model.entity.Diary

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiary(diary: Diary): Long

    @Delete
    fun deleteDiary(diary: Diary)

    @Update
    fun updateDiary(diary: Diary)

    @Query("SELECT * FROM Diary")
    fun getAllDiary(): List<Diary>
}