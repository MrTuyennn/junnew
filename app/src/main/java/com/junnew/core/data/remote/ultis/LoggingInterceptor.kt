package com.junnew.core.data.di.ultis

import android.util.Log
import com.junnew.design_system.constants.LogSystem
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request()
            Log.d(LogSystem.LOG_LEVELS,"Request : $request")
            val response = chain.proceed(request)
            Log.d(LogSystem.LOG_LEVELS,"Response : $request")
            return response
        } catch (e: Exception){
            Log.e(LogSystem.LOG_LEVELS,"Exception : $e")
            throw  e
        }
    }
}