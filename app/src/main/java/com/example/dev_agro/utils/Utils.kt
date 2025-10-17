package com.example.dev_agro.utils

import android.content.SharedPreferences
import com.example.dev_agro.dto.ProductDto
import com.example.dev_agro.dto.RetourDto

fun saveUser(user : RetourDto, myPref : SharedPreferences){
    myPref.edit()
        .putLong(KEY_USER_ID, user.idUser)
        .putString(KEY_USER_TOKEN, user.tokenUser)
        .apply()
}

fun fakeProductList(count: Int = 6): List<ProductDto> =
    List(count) { index ->
        ProductDto(
            idPhoto = index.toLong(),
            titre = "Produit $index",
            description = "Description du produit $index",
            urlImg = "https://via.placeholder.com/150",
            createdAt = "2025-10-16",
            idUser = 1L
        )
    }
