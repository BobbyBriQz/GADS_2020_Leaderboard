package com.bobby.gads2020leaderboard.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bobby.gads2020leaderboard.R
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
                leaderFragmentUI.background = ColorDrawable(Color.DKGRAY)
            }
            1 -> {
                //Set List for Skill IQ Leaders
                leaderFragmentUI.background = ColorDrawable(Color.LTGRAY)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}