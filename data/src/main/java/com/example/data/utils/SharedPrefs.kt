package com.example.data.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefs @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun setUserToken(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_TOKEN", id)
            commit()
        }
    }

    fun getUserToken(): String? {
        return sharedPreferences.getString("USER_TOKEN", "")
    }
}
