package com.example.diary.domain.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.diary.R
import com.example.diary.databinding.ActivityMainBinding
import com.example.diary.domain.fragment.HomeFragment
import com.example.diary.domain.fragment.AddFragment
import com.example.diary.domain.fragment.RankingFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity() : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var rankingFragment: RankingFragment
    private lateinit var addFragment: AddFragment
    // 화면이 메모리에 올라갔을 떄
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 레이아웃과 연결
        setContentView(binding.root)
        Log.d("로그", "MainActivity - onCreate() Called")
        binding.bottomNav.setOnItemSelectedListener(this)

        homeFragment = HomeFragment.newInstance(this)
        supportFragmentManager.beginTransaction().add(R.id.content_frame, homeFragment).commit()


        // Initialize ViewModel and bind data to the UI components
//        val viewModel = MainActivityViewModel()
//        binding.TextView.text = viewModel.getData()
    }
//    private fun test() {
//        val recyclerViewNews = binding.
//        val largeNews = DummyData.getDummyData(this)
//        val newsAdapter = NewsAdapter(largeNews)
//
//        recyclerViewNews.adapter = newsAdapter
//        recyclerViewNews.layoutManager = LinearLayoutManager(this)
//        recyclerViewNews.setHasFixedSize(true)
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("로그", "MainActivity - onNavigationItemSelected() Called")
        when (item.itemId) {
            R.id.menu_home -> {
                Log.d("로그", "MainActivity - 홈버튼 클릭")
                homeFragment = HomeFragment.newInstance(this)
                supportFragmentManager.beginTransaction().replace(R.id.content_frame, homeFragment).commit()
            }
            R.id.menu_ranking -> {
                Log.d("로그", "MainActivity - 랭킹버튼 클릭")
                rankingFragment = RankingFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.content_frame, rankingFragment).commit()
            }
            R.id.menu_profile -> {
                Log.d("로그", "MainActivity - 프로필버튼 클릭")
                addFragment = AddFragment.newInstance(this)
                supportFragmentManager.beginTransaction().replace(R.id.content_frame, addFragment).commit()

            }
        }
        return true
    }
}
