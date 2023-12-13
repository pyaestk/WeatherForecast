package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import coil.request.ImageRequest
import coil.request.ImageRequest.*
import com.example.myapplication.R

import com.example.myapplication.model.api.CurrentWeather
import com.example.myapplication.model.api.weatherDiscription
import com.example.myapplication.repository.ForecastRepo
import com.example.myapplication.repository.Location
import com.example.myapplication.repository.LocationRepo
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CurrentForecastFragment : Fragment() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepo = ForecastRepo()
    private lateinit var locationRepo: LocationRepo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false) 
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        view.findViewById<MaterialToolbar>(R.id.materialToolbar).setTitle("Current Forecast")

        // Inflate the layout for this fragment

        val tempText: TextView = view.findViewById(R.id.currentTempText)
        val locationName: TextView = view.findViewById(R.id.locaitonName)
        val weatherIcon: ImageView = view.findViewById(R.id.currentIcon)
        val weatherDiscription: TextView = view.findViewById(R.id.descriptionTextView)

        //Observing dailyForecast
        val currentWeatherObserver = Observer<CurrentWeather> { weather ->
            locationName.text = weather.name
            tempText.text = formatTempForDisplay(weather.forecast.temp, tempDisplaySettingManager.getTempDisplaySetting())
            val iconId = weather.weather[0].icon
            weatherIcon.load("http://openweathermap.org/img/wn/${iconId}@2x.png")
            weatherDiscription.text = weather.weather[0].description
        }
        forecastRepo.currentWeather.observe(viewLifecycleOwner, currentWeatherObserver)

        //fab
        val locationEntryButton: FloatingActionButton = view.findViewById(R.id.fab)
        locationEntryButton.setOnClickListener {
            val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
            findNavController().navigate(action)
        }

        locationRepo = LocationRepo(requireContext())
        //Observing Location
        val savedLocationObserver = Observer<Location> { savedLocation ->
            when(savedLocation) {
                is Location.Zipcode -> forecastRepo.loadCurrentForecast(savedLocation.zipcode)
            }
        }
        locationRepo.savedLocation.observe(viewLifecycleOwner, savedLocationObserver)

        return view
    }


}
