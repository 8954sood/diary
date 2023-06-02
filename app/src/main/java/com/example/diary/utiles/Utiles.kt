package com.example.diary.utiles

import android.icu.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Utiles {
    companion object {
        const val TAG = "로그"
        fun getCurrentDate(currentTimeMillis: Long): String {
            val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
            return dateFormat.format(currentTimeMillis)
        }
        fun timeDisplay(createTime: Long): String? {
            val currentTime = System.currentTimeMillis()
            val difference = currentTime - createTime

            val oneDayInMillis = 24 * 60 * 60 * 1000 // 1일의 밀리초 단위
            val oneHourInMillis = 60 * 60 * 1000 // 1시간의 밀리초 단위
            val oneMinuteInMillis = 60 * 1000 // 1분의 밀리초 단위
            val oneSecondInMillis = 1000 // 1초의 밀리초 단위

            val displayText = when {
                difference >= oneDayInMillis -> {
                    // 하루 이상일 때 날짜로 표시
//                val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(createTime)
//                formattedDate
                    var days = TimeUnit.MILLISECONDS.toDays(difference)
                    "$days 일 전"
                }
                difference >= oneHourInMillis -> {
                    // 1시간 이상일 때 시간으로 표시
                    val hours = TimeUnit.MILLISECONDS.toHours(difference)
                    "$hours 시간 전"
                }
                difference >= oneMinuteInMillis -> {
                    // 1분 이상일 때 분으로 표시
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(difference)
                    "$minutes 분 전"
                }
                else -> {
                    // 1분 미만일 때 초로 표시
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(difference)
                    "$seconds 초 전"
                }
            }
            return displayText
        }
    }
}