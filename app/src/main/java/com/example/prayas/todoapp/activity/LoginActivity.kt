package com.example.prayas.todoapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.prayas.todoapp.R
import com.example.prayas.todoapp.Service.LoginService
import com.example.prayas.todoapp.ServiceBuilder
import com.example.prayas.todoapp.presenter.LoginPresenter
import com.example.prayas.todoapp.views.LoginView

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var progressBar: ProgressBar
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val SignInButton = findViewById<Button>(R.id.SignIn_Button)
        SignInButton.setOnClickListener { Signin() }

        loginPresenter = object : LoginPresenter(this, ServiceBuilder.build(LoginService::class.java)) {}
        progressBar = findViewById(R.id.login_progressbar)
    }

    private fun Signin() {
        val username = findViewById<EditText>(R.id.username_signin).text.toString()
        val password = findViewById<EditText>(R.id.password_signin).text.toString()
        loginPresenter.login(username, password)
    }

    override fun onFailure() {
        progressBar.visibility = View.INVISIBLE
        Toast.makeText(this,"Login Not Done",Toast.LENGTH_LONG).show()
    }

    override fun onSucess() {
        progressBar.visibility = View.INVISIBLE
        Toast.makeText(this,"Login Done",Toast.LENGTH_LONG).show()
    }

    override fun showProgressloader() {
        progressBar.visibility = View.VISIBLE
    }
}
