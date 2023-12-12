package com.example.myapplication.model.api

import com.squareup.moshi.Json

data class Forecast(val temp: Float)
data class Coordinates(
    val lat: Float,
    val lon: Float
)

data class weatherDiscription(
    val main: String,
    val description: String,
    val icon: String
)

data class CurrentWeather (
    val name: String,
    val coord: Coordinates,
    @field:Json(name = "main") val forecast: Forecast,
    @field:Json(name = "weather") val weather: List<weatherDiscription>
)