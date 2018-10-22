package br.com.anative.tcc.tcc_native.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil(context: Context) {

    private val PREFS_FILENAME = "br.com.anative.tcc.tcc_native"
    private val TOKEN = "token"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var token: String?
        get() = prefs.getString(TOKEN, "")
        set(token) = prefs.edit().putString(TOKEN, token).apply()
}