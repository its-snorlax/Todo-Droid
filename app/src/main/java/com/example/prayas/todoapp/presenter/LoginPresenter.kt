package com.example.prayas.todoapp.presenter

import com.example.prayas.todoapp.JwtTokenHolder
import com.example.prayas.todoapp.Service.LoginService
import com.example.prayas.todoapp.model.UserInfo
import com.example.prayas.todoapp.views.LoginView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class LoginPresenter(var loginView: LoginView, var loginService:LoginService ){

    fun login(username: String, password: String) {
        loginView.showProgressloader()
        val currentUser = object : UserInfo(username, password){}
        loginWith(currentUser)
    }

    private fun loginWith(currentUser: UserInfo) {
        loginService.login(currentUser).enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                loginView.onFailure()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    val jwtToken = response.headers().get("jwt_token")
                    if (jwtToken != null) {
                        JwtTokenHolder.getInstance()!!.setToken(jwtToken)
                    }
                    loginView.onSucess()
                }else{
                    loginView.onFailure()
                }
            }
        })
    }

}