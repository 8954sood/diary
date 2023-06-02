package com.example.diary.domain.activity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.diary.databinding.DialogDeleteBinding
import com.example.diary.model.entity.Diary
import com.example.diary.utiles.Utiles.Companion.TAG

class DeleteDialog(
    context: Context,
    private var listener: DeleteClickListner,
    private var diary: Diary

): Dialog(context) {
    private lateinit var binding: DialogDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: called")
        initClickEvent()

    }

    private fun initClickEvent() {
        with(binding) {
            DeleteYes.setOnClickListener {
                listener.DeleteBtnEvent(diary, diary.id!!.toInt())
                dismiss()
            }
            DeleteCancel.setOnClickListener { dismiss() }
        }
    }
}