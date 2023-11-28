package com.example.myapplication

import android.content.Context

enum class TempDisplaySetting {
    Fahrenheit, Celsius
}
class TempDisplaySettingManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    fun updateSetting(setting: TempDisplaySetting) {
        preferences.edit().putString("key_temp_setting", setting.name).commit()
    }

    fun getTempDisplaySetting(): TempDisplaySetting {

        val settingValue = preferences.getString("key_temp_setting", TempDisplaySetting.Fahrenheit.name) ?:TempDisplaySetting.Fahrenheit.name
        return TempDisplaySetting.valueOf(settingValue)

    }
}