package com.example.diary.domain.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.diary.R
import com.example.diary.model.db.DiaryDataBase
import com.example.diary.model.entity.Diary
import com.example.diary.model.repository.DiaryRepository
import com.example.diary.usecase.diary.GetLocalDiaryUseCase
import com.example.diary.usecase.factory.HomeViewModelFactory
import com.example.diary.viewModel.HomeViewModel
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
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(GetLocalDiaryUseCase(repository))
    }
    // 메모리에 올라갔을 떄
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "ProfileFragment - onCreate() Called")
    }
    // 프레그먼트를 안고 있는 액티비티에 붙었을 떄
    // 메인 액티비티에 결합될 떄(의존)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "ProfileFragment - onAttach() Called")
    }
    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "ProfileFragment - onCreateView() Called")
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        viewModel.insertDiary(Diary(null, "12321312", "내용입ㄴ디ㅏ", 1685456999169L))
        return view
    }

}