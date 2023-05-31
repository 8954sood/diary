package com.example.diary.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diary.model.entity.Diary
import com.example.diary.model.repository.DiaryRepository
import com.example.diary.usecase.diary.GetLocalDiaryUseCase
import com.example.diary.utiles.Utiles.Companion.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel (
    private val getDiaryUseCase: GetLocalDiaryUseCase
): ViewModel() {

    val diaryList: MutableLiveData<List<Diary>> = MutableLiveData()
    var diary: Diary? = null
    fun insertDiary(diary: Diary) {
        Log.d(TAG, "insertDiary: called")
        viewModelScope.launch(Dispatchers.IO) {
            diary.id = getDiaryUseCase.insert(diary)
//            diaryList.add(diary)
        }
    }
    fun deleteTodo(diary: Diary, position: Int) {
        Log.d(TAG, "deleteTodo: called")
        viewModelScope.launch(Dispatchers.IO) {
            getDiaryUseCase.delete(diary)
        }
//        diaryList.removeAt(position)
    }

    fun updateTodo(diary: Diary) {
        Log.d(TAG, "updateTodo() called")
//        diaryList[diaryList.indexOf(diary)] = diary
        viewModelScope.launch(Dispatchers.IO) {
            getDiaryUseCase.update(diary)
        }
    }
    fun initDiary() {
        viewModelScope.launch(Dispatchers.IO) {
            val diaryies = getDiaryUseCase.invoke()
            diaryList.postValue(diaryies)
        }
    }
}