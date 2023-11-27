package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.DailyForecast
import kotlin.random.Random

class ForecastRepo {

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()

    fun loadForecast(zipcode: String) {
        val randomValues = List(15) { Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map {
            DailyForecast(it, getTempDescription(it))
        }
        _weeklyForecast.setValue(forecastItems)
    }

    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    private fun getTempDescription(temp: Float) : String {
        return when(temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0 doesn't make sense"
            in 0f.rangeTo(32f) -> "Way too cold"
            in 32f.rangeTo(55f) -> "Colder than I would prefer"
            in 55f.rangeTo(65f) -> "Getting better"
            in 65f.rangeTo(80f) -> "That's the sweet spot!"
            in 80f.rangeTo(90f) -> "Getting a little warm"
            in 90f.rangeTo(100f) -> "Where's the A/C?"
            in 100f.rangeTo(Float.MIN_VALUE) -> "What is this, Arizona?"
            else -> "Does not compute"
        }
    }
    
}