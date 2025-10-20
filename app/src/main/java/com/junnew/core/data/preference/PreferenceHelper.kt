package com.junnew.core.data.preference

interface PreferenceHelper {
    suspend fun saveLoggerInUserId(userId: String)

    fun getLoggerInUserId(): String?

    suspend fun saveAccessToken(accessToken: String)

    fun getAccessToken(): String?

    suspend fun saveLoggedIsStatus(status: Boolean)

    fun getLoggedIsStatus(): Boolean

    suspend fun saveLoggedIdUserDetails(userId: String, accessToken: String, loginStatus: Boolean)

    suspend fun clearPreference()
}