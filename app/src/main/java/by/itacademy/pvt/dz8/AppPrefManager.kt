package by.itacademy.pvt.dz8

import android.content.Context
import android.content.SharedPreferences

class AppPrefManager(context: Context) {
    private val sharedPrefsName = "SHARED_PREFS_NAME"
    private val textKey = "TEXT_KEY"

    private val sharedPrefs: SharedPreferences = context
        .getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE)

    fun saveUserText(text: String) {
        sharedPrefs
            .edit()
            .putString(textKey, text)
            .apply()
    }

    fun getUserText(): String {
        return sharedPrefs.getString(textKey, "") ?: ""
    }
}