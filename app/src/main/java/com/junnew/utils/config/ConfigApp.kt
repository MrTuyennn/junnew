package com.junnew.utils.config

import com.junnew.BuildConfig
import okhttp3.internal.platform.Platform

class ConfigApp: Platform() {
    val baseUrl: String = BuildConfig.BASE_URL
    val versionName: String = BuildConfig.VERSION_NAME
    val versionCode: String = BuildConfig.VERSION_CODE.toString()
    val type: String = BuildConfig.FLAVOR
}

fun checkTypeFlaVor(flavor: String): String {
    when (flavor) {
        "dev" -> "dev"
        "prod" -> ""
    }
    return ""
}

