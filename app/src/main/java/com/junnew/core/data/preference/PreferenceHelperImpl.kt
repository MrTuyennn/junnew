package com.junnew.core.data.preference

import android.content.SharedPreferences
import javax.inject.Inject
import androidx.core.content.edit

class PreferenceHelperImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
): PreferenceHelper {
    override suspend fun saveLoggerInUserId(userId: String) {
        sharedPreferences.edit {
            putString(PrefConstants.PREF_USER_ID, userId)
        }
    }

    override fun getLoggerInUserId(): String? {
       return sharedPreferences.getString(PrefConstants.PREF_USER_ID,null)
    }

    override suspend fun saveAccessToken(accessToken: String) {
        sharedPreferences.edit {
            putString(PrefConstants.PREF_USER_TOKEN, accessToken)
        }
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(PrefConstants.PREF_USER_TOKEN, null)
    }

    override suspend fun saveLoggedIsStatus(status: Boolean) {
        sharedPreferences.edit {
            putBoolean(PrefConstants.PREF_LOGGED_IN_STATUS, status)
        }
    }

    override fun getLoggedIsStatus(): Boolean {
        return sharedPreferences.getBoolean(PrefConstants.PREF_LOGGED_IN_STATUS, false)
    }

    override suspend fun saveLoggedIdUserDetails(
        userId: String,
        accessToken: String,
        loginStatus: Boolean
    ) {
        saveLoggerInUserId(userId)
        saveAccessToken(accessToken)
        getLoggedIsStatus(loginStatus)
    }

    override suspend fun clearPreference() {
        sharedPreferences.edit {
            clear()
        }
    }
}