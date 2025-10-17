package com.example.dev_agro.dto

import com.squareup.moshi.Json

data class ProductDto(
    @Json(name = "id")
    val idPhoto: Long,

    @Json(name = "titre")
    val titre: String,

    @Json(name = "descriptif")
    val description: String,

    @Json(name = "url_image")
    val urlImg: String,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "id_u")
    val idUser: Long
)
