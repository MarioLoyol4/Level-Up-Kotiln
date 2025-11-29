package com.example.level_up.data

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object{
        const val KEY_TOKEN = "auth_token"
        const val KEY_EMAIL = "user_email"
    }

    fun saveSession(token: String, email: String){
        val editor = prefs.edit()
        editor.putString(KEY_TOKEN, token)
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    fun clearSession(){
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}