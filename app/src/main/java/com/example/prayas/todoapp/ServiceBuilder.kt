package com.example.prayas.todoapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ServiceBuilder{

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
            clientBuilder.addInterceptor { chain ->
                val request = chain.request()
                chain.proceed(request)
            }
            return clientBuilder.build()
        }
    }
}