package com.junnew.core.di

import com.junnew.core.data.preference.PreferenceHelper
import com.junnew.core.data.preference.PreferenceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class SharePrefModule {
    @Binds
    @Singleton
    abstract fun binSharePreference(
        impl: PreferenceHelperImpl
    ): PreferenceHelper
}