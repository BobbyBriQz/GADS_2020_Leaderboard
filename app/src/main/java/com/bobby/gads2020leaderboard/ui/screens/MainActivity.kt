package com.bobby.gads2020leaderboard.ui.screens

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bobby.gads2020leaderboard.R
import com.bobby.gads2020leaderboard.ui.adapter.LeadersAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_leader.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToSubmitBtn.setOnClickListener {
            Intent(this, SubmitActivity::class.java).also {
                startActivity(it)
            }
        }

        val adapter = LeadersAdapter(this)
        leadersViewPager.adapter = adapter

        TabLayoutMediator(mainTabLayout, leadersViewPager){tab, position->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.learning_leaders)
                }
                1 -> {
                    tab.text = getString(R.string.skill_iq_leaders)
                }
            }

        }.attach()
    }
}