package com.example.myapplication.model.api

import com.squareup.moshi.Json

data class Main(
    val temp: Float,
    val feels_like: Float,
    @Json(name = "temp_min") val tempMin: Float,
    @Json(name = "temp_max") val tempMax: Float,
    val pressure: Int,
    @Json(name = "sea_level") val seaLevel: Int,
    @Json(name = "grnd_level") val groundLevel: Int,
    val humidity: Int,
    @Json(name = "temp_kf") val tempKf: Float
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)


data class DailyForecast(
    @field:Json(name = "dt_txt") val dateTimeText: String,
    val main: Main,
    val weather: List<Weather>
)

data class WeeklyForecast(
    @field:Json(name = "list") val list: List<DailyForecast>
)