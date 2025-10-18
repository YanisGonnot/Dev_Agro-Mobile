// file: com/example/dev_agro/di/NetworkModule.kt
package com.example.dev_agro.di

import android.content.Context
import android.content.SharedPreferences
import com.example.dev_agro.network.AgentUploadApi
import com.example.dev_agro.network.UserApiService
import com.example.dev_agro.network.SessionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://dev-aggrobackend-production.up.railway.app/" // no 'api/' here

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
        encodeDefaults = true
    }

    @Provides @Singleton
    fun provideAuthInterceptor(sessionManager: SessionManager): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val builder = original.newBuilder()
        sessionManager.getAccessToken()?.let { token ->
            builder.header("Authorization", "Bearer $token")
        }
        if (original.header("Content-Type").isNullOrBlank()) {
            builder.header("Content-Type", "application/json")
        }
        chain.proceed(builder.build())
    }

    @Provides @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .build()

    @Provides @Singleton
    fun provideRetrofit(okHttp: OkHttpClient, json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // keep it at root; your paths already start with "api/"
            .client(okHttp)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides @Singleton
    fun provideAgentUploadApi(retrofit: Retrofit): AgentUploadApi =
        retrofit.create(AgentUploadApi::class.java)

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
}
