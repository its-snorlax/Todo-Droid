package com.example.prayas.todoapp

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ServiceBuilder {

    companion object {

        fun <T> build(clazz: Class<T>): T {
            val retrofit = Retrofit.Builder().baseUrl(ApiParam.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .build()
            return retrofit.create(clazz)
        }

        private fun client(): OkHttpClient {
            val clientBuilder = OkHttpClient.Builder()
            clientBuilder.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    if(JwtTokenHolder.getInstance()!!.hasToken()){
                        val requestWithJwtToken = request.newBuilder()
                            .addHeader("jwt_token", JwtTokenHolder.getInstance()!!.getToken())
                            .build()
                        return chain.proceed(requestWithJwtToken)
                    }
                    return chain.proceed(request)
                }
            })
            return clientBuilder.build()
        }
    }
}