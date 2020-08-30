package com.bobby.gads2020leaderboard.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bobby.gads2020leaderboard.R
import com.bobby.gads2020leaderboard.network.APIService
import com.bobby.gads2020leaderboard.network.NetworkClient
import com.bobby.gads2020leaderboard.network.response.LearningLeader
import com.bobby.gads2020leaderboard.network.response.SkillIQLeader
import com.bobby.gads2020leaderboard.ui.adapter.LeadersAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var mLearningLeaders: ArrayList<LearningLeader> = arrayListOf()
    private var mSkillIQLeaders: ArrayList<SkillIQLeader> = arrayListOf()

    private var learningLeaderIsRetrieved = false
    private var skillIQLeaderIsRetrieved = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        getLeaders()
    }

    private fun initUI() {
        goToSubmitBtn.setOnClickListener {
            Intent(this, SubmitActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun setUpViewPagerAndAdapter() {

        val adapter = LeadersAdapter(mLearningLeaders, mSkillIQLeaders, this)
        viewPager.adapter = adapter

        TabLayoutMediator(mainTabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.learning_leaders)
                }
                1 -> {
                    tab.text = getString(R.string.skill_iq_leaders)
                }
            }

        }.attach()

        loadingIcon.visibility = View.INVISIBLE
        viewPager.visibility = View.VISIBLE
    }

    private fun getLeaders() {
        getLearningLeaders()
        getSkillIQLeaders()
    }

    private fun getSkillIQLeaders() {

        GlobalScope.launch(Dispatchers.IO){

            try {
                val api = NetworkClient().getInstance(getLeaders = true, submitProject = false)!!.create(APIService::class.java)
                val response = api.getSkillIQLeaders()

                if (response.isSuccessful){

                    val skillIQLeaders : List<SkillIQLeader>  = response.body()!!
                    mSkillIQLeaders = skillIQLeaders as ArrayList<SkillIQLeader>
                    skillIQLeaderIsRetrieved = true
                    if (learningLeaderIsRetrieved){
                        //set up viewPager and adapter
                        withContext(Dispatchers.Main){
                            setUpViewPagerAndAdapter()
                        }
                    }
                }else{
                    withContext(Dispatchers.Main){
                        showToast("Retrieving Skill IQ Leaders Failed!")
                    }
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    showToast(e.message!!)
                }
            }
        }
    }

    private fun getLearningLeaders() {
        GlobalScope.launch(Dispatchers.IO){

            try {
                val api = NetworkClient().getInstance(getLeaders = true, submitProject = false)!!.create(APIService::class.java)
                val response = api.getLearningLeaders()

                if (response.isSuccessful){
                    val learningLeaders : List<LearningLeader>  = response.body()!!
                    mLearningLeaders = learningLeaders as ArrayList<LearningLeader>
                    learningLeaderIsRetrieved = true
                    if (skillIQLeaderIsRetrieved){
                        //set up viewPager and adapter
                        withContext(Dispatchers.Main){
                            setUpViewPagerAndAdapter()
                        }
                    }

                }else{
                    withContext(Dispatchers.Main){
                        showToast("Retrieving Learning Leaders Failed!")
                    }
                }

            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    showToast(e.message!!)
                }
            }
        }
    }



    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

}