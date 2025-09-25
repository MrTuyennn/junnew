package com.junnew.core.data.di
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.junnew.core.data.remote.AuthApi
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides fun provideBaseUrl(): String = "Link Url"

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val log = HttpLoggingInterceptor().apply {
//            level = if (BuildConfig.DEBUG)
//                HttpLoggingInterceptor.Level.BODY
//            else
//                HttpLoggingInterceptor.Level.NONE
            HttpLoggingInterceptor.Level.NONE
        }

        // Nếu có token, bạn inject 1 TokenStorage rồi add header ở đây
        val auth: Interceptor = Interceptor { chain ->
            val req = chain.request().newBuilder()
                // .addHeader("Authorization", "Bearer ${tokenStorage.token}")
                .build()
            chain.proceed(req)
        }

        return OkHttpClient.Builder()
            .addInterceptor(log)
            .addInterceptor(auth)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttp: OkHttpClient,
        baseUrl: String,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)
}