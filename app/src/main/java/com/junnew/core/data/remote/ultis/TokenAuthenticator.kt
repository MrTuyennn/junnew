package com.junnew.core.data.di.ultis

import android.content.SharedPreferences
import com.junnew.core.data.remote.service.AuthApi
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import androidx.core.content.edit

class TokenAuthenticator(
    private val apiService: AuthApi,
    private val sharedPreferences: SharedPreferences
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
       synchronized(this) {
           val currentAccessToken = sharedPreferences.getString("access_token", null)
           val refreshToken = sharedPreferences.getString("refresh_token", null) ?: return null

           if (currentAccessToken != response.request.header("Authorization")?.removePrefix("Bearer ")) {
               return response.request.newBuilder()
                   .header("Authorization", "Bearer $currentAccessToken")
                   .build()
           }

           val newTokensResponse = apiService.refresh(refreshToken)
           if (newTokensResponse.accessToken == "") {
               return null // Refresh failed, trigger logout
           }

           // Save new tokens
           sharedPreferences.edit {
               putString("access_token", newTokensResponse.accessToken)
                   .putString("refresh_token", newTokensResponse.refreshToken)
           }

           // Retry the original request with new token
           return response.request.newBuilder()
               .header("Authorization", "Bearer ${newTokensResponse.accessToken}")
               .build()
       }
    }
}