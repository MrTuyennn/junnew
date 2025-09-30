package com.junnew.utils.helper

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.junnew.utils.constants.LogSystem

data class Language(
    val code: String,
    val displayLanguage: String
)

val appLanguages = listOf(
    Language("en", "English"),
    Language("vi", "Vietnamese")
)

class AppLocaleManager {
    fun changeLanguage(context: Context, languageCode: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.e(LogSystem.LOG_LEVELS,">>>>>> $languageCode")
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(languageCode)
        } else {
            Log.e(LogSystem.LOG_LEVELS,"<<<<< $languageCode")
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
    }

    fun getLanguageCode(context: Context): String {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                ?.applicationLocales
                ?.get(0)
        } else {
            AppCompatDelegate.getApplicationLocales().get(0)
        }
        return locale?.language ?: getDefaultLanguageCode()
    }

    private fun getDefaultLanguageCode(): String {
        return  appLanguages.first().code
    }
}