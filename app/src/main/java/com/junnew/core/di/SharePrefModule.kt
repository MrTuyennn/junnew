package com.junnew.core.di

import android.content.Context
import android.content.SharedPreferences
import com.junnew.core.data.preference.PreferenceHelper
import com.junnew.core.data.preference.PreferenceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SharePrefModule {
    
    @Binds
    @Singleton
    abstract fun binSharePreference(
        impl: PreferenceHelperImpl
    ): PreferenceHelper
    
    companion object {
        @Provides
        @Singleton
        fun provideSharedPreferences(
            @ApplicationContext context: Context
        ): SharedPreferences {
            return context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        }
    }
}