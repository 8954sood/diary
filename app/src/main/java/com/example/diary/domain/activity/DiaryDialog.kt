package com.example.diary.domain.activity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.diary.databinding.DialogDiaryBinding
import com.example.diary.model.entity.Diary
import com.example.diary.utiles.Utiles.Companion.TAG

class DiaryDialog(
    context: Context,
    private var clickListner: DiaryClickListner,
    private var diary: Diary
    ): Dialog(context) {
    private lateinit var binding: DialogDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: called")
        initSetting()
        initClickEvent()
    }
    fun initSetting() {
        with(binding) {
            editEditTitle.hint = diary.title
            editEditContent.hint = diary.content
        }
    }
    fun initClickEvent() {
        with(binding) {
            editEditSave.setOnClickListener {
                if (binding.editEditTitle.text.isEmpty()) {
                    Toast.makeText(context, "수정할 제목을 입력하세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (binding.editEditContent.text.isEmpty()) {
                    Toast.makeText(context, "수정할 내용을 입력하세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                clickListner.saveBtnEvent(Diary(
                    diary.id,
                    binding.editEditTitle.text.toString(),
                    binding.editEditContent.text.toString(),
                    diary.createAt
                ))
            }
            editEditCancel.setOnClickListener{ clickListner.cancelBtnEvent() }
        }
    }
}