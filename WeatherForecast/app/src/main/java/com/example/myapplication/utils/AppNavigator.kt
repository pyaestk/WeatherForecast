package com.example.myapplication.utils

//servers as bridge between main actv and locaiton Entry Frgm
interface AppNavigator {
    fun navigateToCurrentForecast(zipcode: String )
    fun navigateToLocationEntry()
}