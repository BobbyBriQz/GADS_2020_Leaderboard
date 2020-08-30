package com.bobby.gads2020leaderboard.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bobby.gads2020leaderboard.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_submit.*

class SubmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        backBtn.setOnClickListener {
            finish()
        }

        submitBtn.setOnClickListener {
            Toast.makeText(this, "Submit button clicked", Toast.LENGTH_SHORT).show()
        }
    }
}