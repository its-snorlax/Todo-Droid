package com.example.prayas.todoapp.Service

import com.example.prayas.todoapp.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    fun login(@Body currentUser: UserInfo) : Call<Void>
}