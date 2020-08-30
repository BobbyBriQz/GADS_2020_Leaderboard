package com.bobby.gads2020leaderboard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobby.gads2020leaderboard.R
import com.bobby.gads2020leaderboard.network.response.SkillIQLeader
import kotlinx.android.synthetic.main.item_skill_iq.view.countryTV
import kotlinx.android.synthetic.main.item_skill_iq.view.nameTV
import kotlinx.android.synthetic.main.item_skill_iq.view.*
import java.lang.StringBuilder

class SkillIQLeadersAdapter(

    val skillIQLeaders: List<SkillIQLeader>
) : RecyclerView.Adapter<SkillIQLeadersAdapter.SkillIQLeaderVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillIQLeaderVH {
        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.item_skill_iq, parent, false)

        return SkillIQLeaderVH(itemView)
    }

    override fun onBindViewHolder(holder: SkillIQLeaderVH, position: Int) {
        val skillIQLeader = skillIQLeaders[position]
        holder.bind(skillIQLeader)
    }

    override fun getItemCount() = skillIQLeaders.size


    inner class SkillIQLeaderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.nameTV
        private val hours = itemView.countTV
        private val country = itemView.countryTV


        fun bind(skillIQLeader: SkillIQLeader){
            name.text = skillIQLeader.name
            hours.text = StringBuilder(skillIQLeader.score.toString()).append(" skill IQ Score, ")
            country.text = skillIQLeader.country
        }
    }

}