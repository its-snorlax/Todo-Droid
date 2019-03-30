package com.example.prayas.todoapp.presenter

import com.example.prayas.todoapp.Service.SignupService
import com.example.prayas.todoapp.model.UserInfo
import com.example.prayas.todoapp.views.SignupView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class SignupPresenter(var signupView: SignupView, var signupService: SignupService) {
    fun signup(username: String, password: String) {
        signupView.showProgressBar()
        var currentUser = object : UserInfo(username, password) {}
        doSignupRequest(currentUser)
    }

    private fun doSignupRequest(currentUser: UserInfo) {
        signupService.signup(currentUser).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                signupView.onSignupFailure()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    signupView.onSignupSucess()
                }else{
                    signupView.onSignupFailure()
                }
            }

        })
    }
}