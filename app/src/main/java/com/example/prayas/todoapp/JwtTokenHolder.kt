package com.example.prayas.todoapp

open class JwtTokenHolder private constructor() {

    companion object {

        private var holder: JwtTokenHolder? = null
        private var jwtToken: String? = null

        fun getInstance(): JwtTokenHolder? {
            if (holder == null) {
                holder = JwtTokenHolder()
            }
            return holder
        }
    }

    fun setToken(token: String) {
        jwtToken = token
    }
    fun getToken(): String? {
        return jwtToken
    }
    fun hasToken(): Boolean {
        return jwtToken != null;
    }
}