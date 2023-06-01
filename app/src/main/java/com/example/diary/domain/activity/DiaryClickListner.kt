package com.example.diary.domain.activity

import com.example.diary.model.entity.Diary

interface DiaryClickListner {
    fun cancelBtnEvent()
    fun saveBtnEvent(diary: Diary)
}