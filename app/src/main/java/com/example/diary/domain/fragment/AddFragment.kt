package com.example.diary.domain.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.diary.R
import com.example.diary.databinding.FragmentAddBinding
import com.example.diary.domain.activity.HomeClickListener
import com.example.diary.model.db.DiaryDataBase
import com.example.diary.model.entity.Diary
import com.example.diary.model.repository.DiaryRepository
import com.example.diary.usecase.diary.GetLocalDiaryUseCase
import com.example.diary.usecase.factory.AddViewModelFactory
import com.example.diary.viewModel.AddViewModel

class AddFragment(private val repository: DiaryRepository
) : Fragment() { // 프래그먼트의 UI와 동작을 구현합니다.
    companion object {
        const val TAG : String = "로그"
        fun newInstance(context: Context): AddFragment {
            val database = DiaryDataBase.getInstance(context = context) // 실제 데이터베이스 클래스로 'DiaryDatabase'를 대체하세요
            val diaryDao = database?.DiaryO()
            return AddFragment(repository = DiaryRepository(DiaryDAO = diaryDao!!))
        }
    }
    private lateinit var binding: FragmentAddBinding
    private val viewModel: AddViewModel by viewModels {
        AddViewModelFactory(GetLocalDiaryUseCase(repository))
    }
    // 메모리에 올라갔을 떄
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "AddFragment - onCreate() Called")
    }
    // 프레그먼트를 안고 있는 액티비티에 붙었을 떄
    // 메인 액티비티에 결합될 떄(의존)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "AddFragment - onAttach() Called")

    }
    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "AddFragment - onCreateView() Called")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
//        viewModel.insertDiary(Diary(null, "12321312", "내용입ㄴ디ㅏ", 1685456999169L))
        diarySave()
        return binding.root
    }
    private fun diarySave() {
        binding.addSaveButton.setOnClickListener {
            if (binding.addEditTitle.text.isEmpty()) {
                Toast.makeText(context, "제목이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.addEditContent.text.isEmpty()) {
                Toast.makeText(context, "내용이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.insertDiary(Diary(
                id = null,
                title = binding.addEditTitle.text.toString(),
                content = binding.addEditContent.text.toString(),
                createAt = System.currentTimeMillis())
            )
            Log.d(TAG, "diarySave: 세이브 성공")
            

        }
    }

}