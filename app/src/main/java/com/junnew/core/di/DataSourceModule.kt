package com.junnew.core.data.di

import com.junnew.core.data.remote.service.AuthApi
import com.junnew.core.data.remote.datasource.UserRemoteDataSource
import com.junnew.core.di.qualifiers.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        api: AuthApi,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): UserRemoteDataSource =
        UserRemoteDataSource(api, coroutineDispatcher)
}