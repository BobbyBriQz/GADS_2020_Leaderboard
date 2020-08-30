package com.bobby.gads2020leaderboard.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bobby.gads2020leaderboard.network.response.LearningLeader
import com.bobby.gads2020leaderboard.network.response.SkillIQLeader
import com.bobby.gads2020leaderboard.ui.fragment.LeaderFragment
import kotlinx.android.synthetic.main.fragment_leader.*

class LeadersAdapter(
    val learningLeaders :ArrayList<LearningLeader>,
    val skillIQLeaders :ArrayList<SkillIQLeader>,
    activity: AppCompatActivity):FragmentStateAdapter(activity){
    private val itemsCount = 2

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return LeaderFragment().also {
            val bundle = Bundle()
            bundle.putInt("position", position)
            when (position){

                0 -> {
                    bundle.putParcelableArrayList("list", learningLeaders)
                }
                1 -> {
                    bundle.putParcelableArrayList("list", skillIQLeaders)
                }
            }
            it.arguments = bundle
        }
    }
}
