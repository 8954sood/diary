package com.example.diary.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diary.model.entity.Diary
import com.example.diary.usecase.diary.GetLocalDiaryUseCase
import com.example.diary.utiles.Utiles.Companion.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryDialogViewModel(
    private val getDiaryUseCase: GetLocalDiaryUseCase
): ViewModel() {
    fun updateDiary(diary: Diary) {
        Log.d(TAG, "updateDiary: called")
        viewModelScope.launch(Dispatchers.IO) {
            getDiaryUseCase.update(diary)
        }
    }
}