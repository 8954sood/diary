package com.example.diary.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diary.model.entity.Diary
import com.example.diary.usecase.diary.GetLocalDiaryUseCase
import com.example.diary.utiles.Utiles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(
    private val getDiaryUseCase: GetLocalDiaryUseCase
): ViewModel() {

    fun insertDiary(diary: Diary) {
        Log.d(Utiles.TAG, "insertDiary: called")
        viewModelScope.launch(Dispatchers.IO) {
            diary.id = getDiaryUseCase.insert(diary)
//            diaryList.add(diary)
        }
    }

}