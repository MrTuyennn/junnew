package com.junnew.core.data.di.ultis

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       // val accessToken = sharedPreferences.getString("access_token", "") ?: ""
        val accessToken = "BQB18kT8XZe5G-EXRou6zE7AqhSX3kMXDu_HPZiJYmxbfdcFWRj2vcIwAHGNFfOq4JUfzS0FG7z5_OWrD4Rs6-l7lUygkLf6QlqpiAvonOIQJKT4_ixSQvF2KUa_f8LdaMZZvGB70_-6BkE_VO9QldcqZf1aAiPf8reTT18_MDAppjmRV4BX43lBjIGbdc5gV5cQR-bKOOWJLNCb8FQeFZvsRNlxOrG03MU3W6jWQQyzhxD75WhfhGVydmFJjHYLKemgmUhDAYUiV1RuWE5Wda-OOnlUvgifo0qsVphBFFnchQSieQahcCYMXlDbT7D3bZVV"
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(request)
    }
}