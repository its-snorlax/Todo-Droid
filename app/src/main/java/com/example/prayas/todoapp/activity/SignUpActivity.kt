package com.example.prayas.todoapp.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.prayas.todoapp.R
import com.example.prayas.todoapp.R.layout.activity_signup
import com.example.prayas.todoapp.Service.SignupService
import com.example.prayas.todoapp.ServiceBuilder
import com.example.prayas.todoapp.presenter.SignupPresenter
import com.example.prayas.todoapp.views.SignupView

class SignUpActivity : AppCompatActivity(), SignupView {

    private lateinit var progressDialog : ProgressDialog
    private lateinit var signupPresenter: SignupPresenter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(activity_signup)

        val signupButton = findViewById<Button>(R.id.signup_button)
        signupButton.setOnClickListener { signupProgress() }

        progressDialog = object : ProgressDialog(this){}
        signupPresenter = object : SignupPresenter(this, ServiceBuilder.build(SignupService::class.java) ) {}
    }

    private fun signupProgress() {
        val userNameSignup = findViewById<EditText>(R.id.username_signup).text.toString()
        val passwordSignup = findViewById<EditText>(R.id.password_signup).text.toString()
        signupPresenter.signup(userNameSignup, passwordSignup)
    }

    override fun showProgressLoader(title: String, message: String) {
        progressDialog.setTitle(title)
        progressDialog.setMessage(message)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)
        progressDialog.show()
    }


    override fun onSignupSucess() {
        Toast.makeText(this,"Signup done",Toast.LENGTH_LONG).show()
        progressDialog.dismiss()
    }

    override fun onSignupFailure() {
        Toast.makeText(this,"Not done",Toast.LENGTH_LONG).show()
        progressDialog.dismiss()
    }

}
