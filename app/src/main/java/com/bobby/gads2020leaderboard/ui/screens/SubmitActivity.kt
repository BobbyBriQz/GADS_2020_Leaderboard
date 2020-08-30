package com.bobby.gads2020leaderboard.ui.screens

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bobby.gads2020leaderboard.R
import com.bobby.gads2020leaderboard.network.APIService
import com.bobby.gads2020leaderboard.network.NetworkClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.dialog_affirmation.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        backBtn.setOnClickListener {
            finish()
        }

        submitBtn.setOnClickListener {
            if (validationPassed()){
                showAffirmationDialog()
            }
        }
    }

    private fun showAffirmationDialog() {
        val view: View = View.inflate(this, R.layout.dialog_affirmation, null)

        val affirmDialog = AlertDialog.Builder(this)
            .setView(view)
            .show()
        affirmDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        view.affirmationBtn.setOnClickListener {
            performSubmission()
            affirmDialog.dismiss()
        }

        view.cancelBtn.setOnClickListener {
            affirmDialog.dismiss()
        }
    }

    private fun performSubmission() {
        submitLoadingIcon.visibility = View.VISIBLE
        val firstName = firstNameET.text.toString()
        val lastName = lastNameET.text.toString()
        val email = emailET.text.toString()
        val githubLink = linkET.text.toString()

        GlobalScope.launch(Dispatchers.Main){
            try {
                val api = NetworkClient().getInstance(getLeaders = false,submitProject = true)!!.create(APIService::class.java)
                val response = api.submitProject(
                    firstName = firstName, lastName = lastName, email = email, githubLink = githubLink
                )
                if (response.isSuccessful){
                    withContext(Dispatchers.Main){
                       showSuccessDialog()
                    }
                }else{
                    withContext(Dispatchers.Main){
                        showFailedDialog()
                    }
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    submitLoadingIcon.visibility = View.GONE
                    showToast(e.message!!)
                }
            }
        }
    }

    private fun showSuccessDialog() {

        val view: View = View.inflate(this, R.layout.dialog_submission_successful, null)

        val successDialog = AlertDialog.Builder(this)
            .setView(view)
            .show()
        submitLoadingIcon.visibility = View.GONE
        successDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun showFailedDialog() {
        val view: View = View.inflate(this, R.layout.dialog_submission_failed, null)

        val failedDialog = AlertDialog.Builder(this)
            .setView(view)
            .show()
        submitLoadingIcon.visibility = View.GONE

        failedDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun validationPassed():Boolean{
        var validationPassed = true
        firstNameLayout.error = ""
        lastNameLayout.error = ""
        emailLayout.error = ""
        linkLayout.error = ""

        if (firstNameET.text.isNullOrEmpty()){
            firstNameLayout.error = "Please fill in first name"
            validationPassed = false
        }
        if (lastNameET.text.isNullOrEmpty()){
            lastNameLayout.error = "Please fill in last name"
            validationPassed = false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.text.toString()).matches()){
            emailLayout.error = "Please fill in a valid email"
            validationPassed = false
        }
        if (linkET.text.isNullOrEmpty()){
            linkLayout.error = "Please fill in project link"
            validationPassed = false
        }

        return validationPassed
    }


    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}