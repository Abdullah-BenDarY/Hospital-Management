package com.example.hospitalmanagement.utils

import android.content.Context
import android.content.SharedPreferences


object SharedPrefs {
    private const val PREFS_NAME = "user_data"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setNurseId(id: Int) {
        with(sharedPreferences.edit()) {
            putInt("NURSE_ID", id)
            commit()
        }
    }

    fun getNurseId(): Int {
        return sharedPreferences.getInt("NURSE_ID" , 0)
    }

}
