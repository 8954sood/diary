package com.example.diary.domain.activity

import com.example.diary.model.entity.Diary

interface DeleteClickListner {
    fun DeleteBtnEvent(diary: Diary, position: Int)
}