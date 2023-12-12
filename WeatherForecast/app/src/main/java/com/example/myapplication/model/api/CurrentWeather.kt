package com.example.myapplication.model.api

import com.squareup.moshi.Json

data class Forecast(
    val temp: Float
)
data class Coordinates(
    val lat: Float,
    val lon: Float
)

data class Wind(
    val speed: Float,
    val deg: Float,
    val gust: Float
)

data class CurrentWeather (
    val name: String,
    val coord: Coordinates,
    @field:Json(name = "main") val forecast: Forecast,
    @field:Json(name = "wind") val wind: Wind
)