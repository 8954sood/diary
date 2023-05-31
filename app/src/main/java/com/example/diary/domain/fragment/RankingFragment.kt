package com.example.diary.domain.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diary.R

class RankingFragment : Fragment() { // 프래그먼트의 UI와 동작을 구현합니다.
    companion object {
        const val TAG : String = "로그"
        fun newInstance(): RankingFragment {
            return RankingFragment()
        }
    }
    // 메모리에 올라갔을 떄
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "RankingFragment - onCreate() Called")
    }
    // 프레그먼트를 안고 있는 액티비티에 붙었을 떄
    // 메인 액티비티에 결합될 떄(의존)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "RankingFragment - onAttach() Called")
    }
    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "RankingFragment - onCreateView() Called")
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        return view
    }

}