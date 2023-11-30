package com.example.myapplication.utils

import android.content.Context

enum class TempDisplaySetting {
    Fahrenheit, Celsius
}
//shared preference
class TempDisplaySettingManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun getTempDisplaySetting(): TempDisplaySetting {

        val settingValue = preferences.getString("key_temp_setting", TempDisplaySetting.Fahrenheit.name) ?: TempDisplaySetting.Fahrenheit.name
        return TempDisplaySetting.valueOf(settingValue)

    }
    fun updateSetting(setting: TempDisplaySetting) {
        preferences.edit().putString("key_temp_setting", setting.name).commit()
    }


}