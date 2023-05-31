package com.example.diary.model.repository

import com.example.diary.model.dao.DiaryDao
import com.example.diary.model.entity.Diary

class DiaryRepository(private val DiaryDAO: DiaryDao) {
    fun insertTodo(diary: Diary): Long {
        return DiaryDAO.insertDiary(diary)
    }

    fun deleteTodo(diary: Diary) {
        DiaryDAO.deleteDiary(diary)
    }

    fun updateTodo(diary: Diary) {
        DiaryDAO.updateDiary(diary)
    }

    fun getAllTodo(): List<Diary> {
        return DiaryDAO.getAllDiary()
    }
}