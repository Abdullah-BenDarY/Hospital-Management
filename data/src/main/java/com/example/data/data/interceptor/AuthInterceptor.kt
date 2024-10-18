package com.example.data.data.interceptor

import com.example.data.utils.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sharedPrefs: SharedPrefs
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPrefs.getUserToken() ?: ""
        val newBuilder = chain.request().newBuilder()
        newBuilder.header("Authorization", token)
        return chain.proceed(newBuilder.build())
    }
}
