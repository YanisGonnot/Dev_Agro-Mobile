package com.example.dev_agro.dto

import com.squareup.moshi.Json

data class RegisterDto(
    @Json(name = "login")
    val name: String,

    @Json(name = "mdp")
    val password: String
)
