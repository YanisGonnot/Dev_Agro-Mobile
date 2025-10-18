package com.example.dev_agro.network.repo

import com.example.dev_agro.network.AgentUploadApi
import com.example.dev_agro.network.DescribeImageResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

interface AgentRepository {
    suspend fun describeImage(
        imageBytes: ByteArray,
        filename: String = "photo.jpg",
        mimeType: String = "image/jpeg"   // ✅ concrete MIME, not image/*
    ): String}

@Singleton
class AgentRepositoryImpl @Inject constructor(
    private val api: AgentUploadApi
) : AgentRepository {

    override suspend fun describeImage(
        imageBytes: ByteArray,
        filename: String,
        mimeType: String
    ): String {
        val rb = imageBytes.toRequestBody(mimeType.toMediaType())
        val part = MultipartBody.Part.createFormData("image", filename, rb)
        val res: DescribeImageResponse = api.describeImageUpload(part)

        if (!res.success) throw Exception("L’IA n’a pas pu décrire l’image.")
        return res.description ?: throw Exception("Réponse IA vide.")
    }
}