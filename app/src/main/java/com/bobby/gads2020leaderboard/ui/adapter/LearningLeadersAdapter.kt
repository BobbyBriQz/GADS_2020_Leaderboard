package com.bobby.gads2020leaderboard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobby.gads2020leaderboard.R
import com.bobby.gads2020leaderboard.network.response.LearningLeader
import kotlinx.android.synthetic.main.item_learning.view.*
import java.lang.StringBuilder

class LearningLeadersAdapter(

    val learningLeaders: List<LearningLeader>
) : RecyclerView.Adapter<LearningLeadersAdapter.LearningLeaderVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningLeaderVH {
        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.item_learning, parent, false)

        return LearningLeaderVH(itemView)
    }

    override fun onBindViewHolder(holder: LearningLeaderVH, position: Int) {
        val learningLeader = learningLeaders[position]
        holder.bind(learningLeader)
    }

    override fun getItemCount() = learningLeaders.size


    inner class LearningLeaderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.nameTV
        private val hours = itemView.countTV
        private val country = itemView.countryTV


        fun bind(learningLeader: LearningLeader){
            name.text = learningLeader.name
            hours.text = StringBuilder(learningLeader.hours.toString()).append(" learning hours, ")
            country.text = learningLeader.country
        }
    }

}