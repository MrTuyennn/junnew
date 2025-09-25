package com.junnew.core.data.di.ultis

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       // val accessToken = sharedPreferences.getString("access_token", "") ?: ""
        val accessToken = ""
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(request)
    }
}