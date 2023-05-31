package com.example.diary.usecase.diary

import android.database.Observable
import androidx.lifecycle.viewModelScope
import com.example.diary.model.entity.Diary
import com.example.diary.model.repository.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetLocalDiaryUseCase(private var repository: DiaryRepository) {
    operator fun invoke(): List<Diary> = repository.getAllTodo()
    fun delete(
        query: Diary
    ): Unit = repository.deleteTodo(query)
    fun update(
        query: Diary
    ): Unit = repository.updateTodo(query)
    fun insert(
        query: Diary
    ): Long = repository.insertTodo(query)
}