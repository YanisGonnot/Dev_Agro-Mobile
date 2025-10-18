package com.example.easyresto_android.logic.data.network

import com.example.dev_agro.logic.data.network.service.UserApiService
import com.example.dev_agro.network.SessionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class ApiClient(
    private val sessionManager: SessionManager
) {

    companion object {
        const val PROD_URL = "https://dev-aggrobackend-production.up.railway.app/api/"
    }

    private val json by lazy {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
            encodeDefaults = true
        }
    }

    private val contentType = "application/json".toMediaType()

    private val httpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(sessionManager))


        builder.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(PROD_URL) // ← use PROD by default
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    // Expose your services here
    val userApiService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}

/** Attaches Authorization header if a token is present. */
class AuthInterceptor(
    private val sessionManager: SessionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()
        val builder = original.newBuilder()

        sessionManager.getAccessToken()?.let { token ->
            builder.header("Authorization", "Bearer $token")
        }

        // Ensure JSON requests by default
        if (original.header("Content-Type").isNullOrBlank()) {
            builder.header("Content-Type", "application/json")
        }

        return chain.proceed(builder.build())
    }
}
