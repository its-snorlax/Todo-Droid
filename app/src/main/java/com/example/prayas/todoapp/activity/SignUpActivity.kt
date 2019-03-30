package com.example.prayas.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.prayas.todoapp.R
import com.example.prayas.todoapp.Service.SignupService
import com.example.prayas.todoapp.ServiceBuilder
import com.example.prayas.todoapp.presenter.SignupPresenter
import com.example.prayas.todoapp.views.SignupView

class SignUpActivity : AppCompatActivity(), SignupView {
    private lateinit var progressBar : ProgressBar

    private lateinit var signupPresenter: SignupPresenter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signupButton = findViewById<Button>(R.id.signup_button)
        signupButton.setOnClickListener { onSubmitClick() }

        progressBar = findViewById(R.id.signup_progressbar)
        signupPresenter = object : SignupPresenter(this, ServiceBuilder.build(SignupService::class.java) ) {}
    }


    private fun onSubmitClick() {
        val userName = findViewById<EditText>(R.id.username_signup).text.toString()
        val password = findViewById<EditText>(R.id.password_signup).text.toString()
        progressBar = findViewById(R.id.signup_progressbar)
        signupPresenter.signup(userName, password)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }


    override fun onSignupSucess() {
        progressBar.visibility = View.GONE
        Toast.makeText(this,"Signup done",Toast.LENGTH_LONG).show()
        val startLoginActivity = Intent(this, LoginActivity::class.java)
        startActivity(startLoginActivity)
        finish()
    }

    override fun onSignupFailure() {
        progressBar.visibility =  View.GONE
        Toast.makeText(this,"SignuUp not done",Toast.LENGTH_LONG).show()

    }

}
