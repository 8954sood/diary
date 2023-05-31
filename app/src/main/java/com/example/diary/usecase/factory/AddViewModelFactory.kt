package com.example.diary.usecase.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diary.usecase.diary.GetLocalDiaryUseCase
import com.example.diary.viewModel.AddViewModel

class AddViewModelFactory(private val getDiaryUseCase: GetLocalDiaryUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(getDiaryUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}