package com.example.dev_agro.utils

import android.content.SharedPreferences
import com.example.dev_agro.dto.RetourDto

fun saveUser(user : RetourDto, myPref : SharedPreferences){
    myPref.edit()
        .putLong(KEY_USER_ID, user.idUser)
        .putString(KEY_USER_TOKEN, user.tokenUser)
        .apply()
}