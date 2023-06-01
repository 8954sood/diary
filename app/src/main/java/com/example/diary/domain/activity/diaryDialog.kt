package com.example.diary.domain.activity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.diary.databinding.DialogDiaryBinding
import com.example.diary.domain.adapter.HomeAdapter
import com.example.diary.utiles.Utiles.Companion.TAG

class diaryDialog(context: Context): Dialog(context) {
    private lateinit var binding: DialogDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: called")
    }
}