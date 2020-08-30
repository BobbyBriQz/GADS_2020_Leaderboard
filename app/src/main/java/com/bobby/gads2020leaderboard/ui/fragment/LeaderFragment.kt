package com.bobby.gads2020leaderboard.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobby.gads2020leaderboard.R
import com.bobby.gads2020leaderboard.network.response.LearningLeader
import com.bobby.gads2020leaderboard.network.response.SkillIQLeader
import com.bobby.gads2020leaderboard.ui.adapter.LearningLeadersAdapter
import com.bobby.gads2020leaderboard.ui.adapter.SkillIQLeadersAdapter
import kotlinx.android.synthetic.main.fragment_leader.*

class LeaderFragment(): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leader, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (arguments!!.getInt("position")){

            0 -> {
                //Set List for Learning Leaders
                val list:ArrayList<LearningLeader> = arguments!!.getParcelableArrayList("list")!!
                val adapter = LearningLeadersAdapter(list)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(activity)
            }
            1 -> {
                //Set List for Skill IQ Leaders
                val list:ArrayList<SkillIQLeader> = arguments!!.getParcelableArrayList("list")!!
                val adapter = SkillIQLeadersAdapter(list)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(activity)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}