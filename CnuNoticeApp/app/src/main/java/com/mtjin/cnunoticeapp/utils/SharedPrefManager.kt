package com.mtjin.cnunoticeapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(CNU_APP, Context.MODE_PRIVATE)

    var univAuth: Boolean
        get() = sharedPref.getBoolean(UNIV_AUTH_KEY, false)
        set(value) {
            val editor = sharedPref.edit()
            editor.putBoolean(UNIV_AUTH_KEY, value)
            editor.apply()
        }

    var uuid: String
        get() = sharedPref.getString(UUID, "")!!
        set(value) {
            val editor = sharedPref.edit()
            editor.putString(UUID, value)
            editor.apply()
        }

    companion object {
        private const val CNU_APP = "CNU_APP"
        private const val UNIV_AUTH_KEY = "UNIV_AUTH_KEY" //대학교 인증
        private const val UUID = "UUID" //UUID
    }
}