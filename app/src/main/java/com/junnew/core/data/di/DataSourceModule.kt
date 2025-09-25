package com.junnew.core.data.di

import com.junnew.core.data.remote.AuthApi
import com.junnew.core.data.remote.datasource.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideUserRemoteDataSource(api: AuthApi): UserRemoteDataSource =
        UserRemoteDataSource(api)
}