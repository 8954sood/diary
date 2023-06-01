package com.example.diary.domain.activity

import com.example.diary.model.entity.Diary

interface HomeClickListener {
    fun onEditBtnClick(diary: Diary, position: Int)
}