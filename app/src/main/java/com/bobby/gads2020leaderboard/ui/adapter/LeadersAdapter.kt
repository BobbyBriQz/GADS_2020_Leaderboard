package com.bobby.gads2020leaderboard.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bobby.gads2020leaderboard.ui.fragment.LeaderFragment

class LeadersAdapter(activity: AppCompatActivity):FragmentStateAdapter(activity){
    private val itemsCount = 2
    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return LeaderFragment().also {
            val bundle = Bundle()
            bundle.putInt("position", position)
            it.arguments = bundle
        }
    }
}
