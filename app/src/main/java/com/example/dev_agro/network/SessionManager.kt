package com.example.dev_agro.network

import android.content.SharedPreferences

class SessionManager(private val prefs: SharedPreferences) {
    fun getAccessToken(): String? = prefs.getString("access_token", null)
    fun setAccessToken(token: String?) {
        prefs.edit().putString("access_token", token).apply()
    }
}