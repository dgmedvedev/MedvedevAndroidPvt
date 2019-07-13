package by.itacademy.pvt.dz8

import android.content.Context
import android.content.SharedPreferences
import by.itacademy.pvt.dz8.Dz8StudentListFragment.Companion.SHARED_PREFS_NAME
import by.itacademy.pvt.dz8.Dz8StudentListFragment.Companion.TEXT_KEY

class AppPrefManager(context: Context) {
    private val sharedPrefs: SharedPreferences = context
        .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUserText(text: String) {
        sharedPrefs
            .edit()
            .putString(TEXT_KEY, text)
            .apply()
    }

    fun getUserText(): String {
        return sharedPrefs.getString(TEXT_KEY, "") ?: ""
    }
}