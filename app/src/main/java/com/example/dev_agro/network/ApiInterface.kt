package com.example.dev_agro.network

import kotlinx.serialization.Serializable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AgentUploadApi {
    @Multipart
    @POST("api/v1/agent/describe-image")
    suspend fun describeImageUpload(
        @Part image: MultipartBody.Part
    ): DescribeImageResponse
}

@Serializable
data class DescribeImageResponse(
    val success: Boolean,
    val filename: String? = null,
    val description: String? = null,
    val timestamp: String? = null,
    val fileSize: Long? = null
)
