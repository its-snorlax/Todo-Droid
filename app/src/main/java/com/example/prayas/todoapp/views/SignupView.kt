package com.example.prayas.todoapp.views

interface SignupView {
    fun onSignupSucess()
    fun onSignupFailure()
    fun showProgressLoader(title: String, message: String)
}