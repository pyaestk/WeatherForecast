package com.example.myapplication.utils

import com.example.myapplication.model.DailyForecast

//servers as bridge between main actv and locaiton Entry Frgm
interface AppNavigator {
    fun navigateToCurrentForecast(zipcode: String )
    fun navigateToLocationEntry()

    fun navigateToForecastDetails(forecast: DailyForecast)
}