package com.example.diary.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "diary")
data class Diary(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title: String,
    var content: String,
    var createAt: Long
)
