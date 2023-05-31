package com.example.diary.domain.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.domain.fragment.HomeFragment.Companion.TAG
import com.example.diary.model.entity.Diary
import com.example.diary.utiles.Utiles.Companion.timeDisplay
import com.example.diary.databinding.ItemHomeBinding
import java.util.*
import java.util.concurrent.TimeUnit


class HomeAdapter(
    private val diaryList: MutableLiveData<List<Diary>>
    ): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val diaryList = diaryList.value
        if (diaryList != null && position < diaryList.size) {
            val diary = diaryList[position]
            holder.setTitle(diary.title!!)
            Log.d(TAG, "onBindViewHolder: ${diary.createAt}")
            holder.setCrateAt(timeDisplay(diary.createAt))
            holder.initItemClickListener(diary, position)
        }
//        if (diary != null && position < diary.size)
//            diary = diary[position]
//            holder.setTitle(diary.title!!)
//            Log.d(TAG, "onBindViewHolder: ${diary.createAt}")
//            holder.setCrateAt(timeDisplay(diary.createAt))
//            holder.initItemClickListener(diary, position)
    }

    override fun getItemCount(): Int {
        return diaryList.value?.size ?: 0
    }
    inner class HomeViewHolder(private var binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun setTitle(title: String) {
            binding.checkbox.text = title
        }
        fun setCrateAt(createAt: String?) {
            binding.createAt.text = createAt.toString()
        }
        fun initItemClickListener(diary: Diary, position: Int) {
            with (binding) {
                checkbox.setOnClickListener {
                    Log.d(TAG, "initItemClickListener: 오픈")
                }
                btnEdit.setOnClickListener {
                    Log.d(TAG, "initItemClickListener: 히히")
                }
            }
            

        }

    }


}