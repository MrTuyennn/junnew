package com.junnew.core.data.di.ultis

import android.util.Log
import com.junnew.utils.constants.LogSystem
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class LoggingInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request()
            
            // Log request
            Log.d(LogSystem.LOG_LEVELS, "Request URL: ${request.url}")
            Log.d(LogSystem.LOG_LEVELS, "Request Method: ${request.method}")
            request.headers.forEach { header ->
                Log.d(LogSystem.LOG_LEVELS, "Request Header: ${header.first} = ${header.second}")
            }
            
            // Log request body if exists
            request.body?.let { body ->
                try {
                    val buffer = Buffer()
                    body.writeTo(buffer)
                    Log.d(LogSystem.LOG_LEVELS, "Request Body: ${buffer.readUtf8()}")
                } catch (e: IOException) {
                    Log.e(LogSystem.LOG_LEVELS, "Error reading request body: $e")
                }
            }
            
            val response = chain.proceed(request)
            
            // Log response
            Log.d(LogSystem.LOG_LEVELS, "Response Code: ${response.code}")
            Log.d(LogSystem.LOG_LEVELS, "Response Message: ${response.message}")
            response.headers.forEach { header ->
                Log.d(LogSystem.LOG_LEVELS, "Response Header: ${header.first} = ${header.second}")
            }
            
            // Log response body
            val responseBody = response.peekBody(1024 * 1024) // Peek up to 1MB
            Log.d(LogSystem.LOG_LEVELS, "Response Body: ${responseBody.string()}")
            
            return response
        } catch (e: Exception){
            Log.e(LogSystem.LOG_LEVELS, "Exception in LoggingInterceptor: $e", e)
            throw e
        }
    }
}