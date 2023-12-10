package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.BuildConfig
import com.example.myapplication.model.api.CurrentWeather
import com.example.myapplication.model.api.createOpenWeatherMapService
import com.example.myapplication.model.DailyForecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class ForecastRepo {

    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather> = _currentWeather

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadCurrentForecast(zipcode: String) {
        val call = createOpenWeatherMapService().currentWeather(zipcode, "imperial", BuildConfig.OPEN_WEATHER_MAP_API_KEY)
        call.enqueue(object : Callback<CurrentWeather> {
            override fun onResponse(
                call: Call<CurrentWeather>,
                response: Response<CurrentWeather>
            ) {
                val weatherResponse = response.body()
                if (weatherResponse!=null) {
                    _currentWeather.value = weatherResponse!!
                }

            }

            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                Log.e(ForecastRepo::class.java.simpleName, "error loading current weather", t)
            }


        })
    }
    fun loadWeeklyForecast(zipcode: String) {
        val randomValues = List(15) { Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map {
            DailyForecast(it, getTempDescription(it))
        }
        _weeklyForecast.setValue(forecastItems)
    }

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