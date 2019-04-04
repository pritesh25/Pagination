package com.example.pagination

import android.content.Context
import android.util.Log
import android.widget.Toast

class Utils {

    companion object {

        private val mTag: String = this::class.java.simpleName

        const val RC_SIGN_IN = 9001

        /**
         * api key
         */
        const val KEY_WEATHER = "82af4295b345889c281640e43c8d0862"
        const val KEY_NEWS = "547d539011e8404c9adf0a7298e04041"

        /**
         * web service url
         */
        const val NEWS_URL = "https://newsapi.org/v2/"
        const val WEATHER_URL = "http://api.openweathermap.org/data/2.5/"

        /**
         * user information
         */
        private const val PREF_NAME = "Launcher_Pref"
        var USER_NAME = "Launcher_Pref_name"
        var USER_EMAIL = "Launcher_Pref_email"
        var USER_PROFILE = "Launcher_Pref_profile"

        /**
         * location
         */
        var USER_CITY = "Launcher_Pref_cityName"
        var USER_COUNTRY = "Launcher_Pref_countryCode"

        /**
         * weather information
         */
        var WEATHER_STATUS_TEXT = "weather_Pref_text"
        var WEATHER_STATUS_ICON = "weather_Pref_icon"

        var WEATHER_TEMP = "weather_Pref_temp"
        var WEATHER_MIN_TEMP = "weather_Pref_min_temp"
        var WEATHER_MAX_TEMP = "weather_Pref_max_temp"

        var WEATHER_PRESSURE = "weather_Pref_pressure"
        var WEATHER_HUMIDITY = "weather_Pref_humidity"

        var WEATHER_WIND_SPEED = "weather_Pref_wind_speed"
        var WEATHER_CLOUD = "weather_Pref_cloud"

        fun setData(cxt: Context?, key: String, value: String) {
            if (cxt != null) {
                val sharedPreferencesEditor = cxt.getSharedPreferences(PREF_NAME, 0).edit()
                sharedPreferencesEditor.putString(key, value)
                sharedPreferencesEditor.apply()
            } else {
                Log.d(mTag, "context is null")
            }
        }

        fun getData(cxt: Context, key: String): String {
            return cxt.getSharedPreferences(PREF_NAME, 0).getString(key, "")
        }

        fun t(cxt: Context?, message: String) {
            Toast.makeText(cxt, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun getColumn(cxt: Context, columnWidthDp: Int): Int {
        val displayMetrics = cxt.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }
}