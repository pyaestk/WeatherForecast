package com.example.myapplication.model.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherMapService {
    @GET("/data/2.5/weather")
    fun currentWeather(
        @Query("zip") zipCode: String,
        @Query("units") units: String,
        @Query("appId") apiKey: String,
    ): Call<CurrentWeather>
}

fun createOpenWeatherMapService(): OpenWeatherMapService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(OpenWeatherMapService::class.java)
}