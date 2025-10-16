package com.example.dev_agro.dto

import com.squareup.moshi.Json

data class RetourDto(
    @Json(name = "id")
    val idUser: Long,

    @Json(name = "token")
    val tokenUser: String
)