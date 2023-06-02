package com.example.diary.domain.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary.R
import com.example.diary.databinding.FragmentHomeBinding
import com.example.diary.domain.activity.*
import com.example.diary.domain.adapter.HomeAdapter
import com.example.diary.model.db.DiaryDataBase
import com.example.diary.model.entity.Diary
import com.example.diary.model.repository.DiaryRepository
import com.example.diary.usecase.diary.GetLocalDiaryUseCase
import com.example.diary.usecase.factory.HomeViewModelFactory
import com.example.diary.viewModel.HomeViewModel


//import com.example.mvvm.domain.adapter.HomeAdapter
//import com.example.mvvm.viewModel.HomeFragmentViewModel

class HomeFragment(
    private val repository: DiaryRepository
) : Fragment(), HomeClickListener, DiaryClickListner, DeleteClickListner { // 프래그먼트의 UI와 동작을 구현합니다.

    companion object {
        const val TAG : String = "로그"
        fun newInstance(context: Context): HomeFragment {
            val database = DiaryDataBase.getInstance(context = context) // 실제 데이터베이스 클래스로 'DiaryDatabase'를 대체하세요
            val diaryDao = database?.DiaryO()
            return HomeFragment(repository = DiaryRepository(DiaryDAO = diaryDao!!))
        }
    }
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(GetLocalDiaryUseCase(repository))
    }
//    private lateinit var homeClickListener: HomeClickListener
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dialog: DiaryDialog // by lazy { DiaryDialog(context = requireContext(), this) }
//    private val viewModel: HomeFragmentViewModel by viewModels()
    // 메모리에 올라갔을 떄
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "HomeFragment - onCreate() Called")
    }
    // 프레그먼트를 안고 있는 액티비티에 붙었을 떄
    // 메인 액티비티에 결합될 떄(의존)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "HomeFragment - onAttach() Called")

    }
    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "HomeFragment - onCreateView() Called")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel.initDiary()
//        var ls = mutableListOf(
//            Diary(null, "ㅅㄱㄱ", "ㄱㄱ", 1685456999169L),
//            Diary(null, "ㅅㄱㄱ", "ㄱㄱ", System.currentTimeMillis()),
//            Diary(null, "ㅅㄱㄱ", "ㄱㄱ", System.currentTimeMillis())
//        )
        viewModel.diaryList.observe(viewLifecycleOwner) { diaries ->
            var ls = viewModel.diaryList
            // this로 넘겨줘야 클릭이 이쪽 이벤트로 넘어옴 (왜? 자신의 클릴리스너부모를 갖다주는거기에
            binding.homeRecycler.adapter = HomeAdapter(ls, this)
        }
        binding.homeRecycler.layoutManager = LinearLayoutManager(activity)



        // val view = inflater.inflate(R.layout.fragment_home, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: called")
//
//        viewModel.initDiary()
//        viewModel.diaryList.observe(viewLifecycleOwner) { diaries ->
//             다이어리 리스트 업데이트될 때 처리
//        }
    }
    override fun onEditBtnClick(diary: Diary, position: Int) {
        // rrequireContext 을 통해 context가 null이 아닐 수 있음을 알림
        dialog = DiaryDialog(context = requireContext(), this, diary)
        Log.d(TAG, "onEditBtnClick: called")
        dialog.show()
    }

    override fun onDeleteBtnClick(diary: Diary, position: Int) {
        //다이어로그 표시, 정말 삭제 질문, 그후 삭제
        Log.d(TAG, "onDeleteBtnClick: called")
        var deleteDialogs = DeleteDialog(context = requireContext(), diary = diary, listener = this)
        deleteDialogs.show()

    }

    override fun cancelBtnEvent() {
        Log.d(TAG, "cancelBtnEvent: called")
        dialog.dismiss()
    }
    override fun saveBtnEvent(diary: Diary) {
        Log.d(TAG, "saveBtnEvent: called")
        viewModel.updateTodo(diary)
        dialog.dismiss()
        viewModel.initDiary()
    }

    override fun DeleteBtnEvent(diary: Diary, position: Int) {
        viewModel.deleteTodo(diary, position)
        viewModel.initDiary()
    }


}