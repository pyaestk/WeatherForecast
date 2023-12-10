package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.ForecastAdapter
import com.example.myapplication.model.DailyForecast
import com.example.myapplication.model.api.CurrentWeather
import com.example.myapplication.repository.ForecastRepo
import com.example.myapplication.repository.Location
import com.example.myapplication.repository.LocationRepo
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay
import com.example.myapplication.view.WeeklyForecastFragment.Companion.KEY_ZIPCODE
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

        val zipcode = arguments?.getString(KEY_ZIPCODE) ?: ""
        // Inflate the layout for this fragment

        val tempText: TextView = view.findViewById(R.id.currentTempText)
        val locationName: TextView = view.findViewById(R.id.locaitonName)

        //Observing dailyForecast
        val currentWeatherObserver = Observer<CurrentWeather> { weather ->
            locationName.text = weather.name
            tempText.text = formatTempForDisplay(weather.forecast.temp, tempDisplaySettingManager.getTempDisplaySetting())
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

    companion object {
        const val KEY_ZIPCODE = "key_zipcode" //defined as key
        fun newInstance(zipcode: String): CurrentForecastFragment {
            val fragment = CurrentForecastFragment()
            val bundle = Bundle()
            bundle.putString(KEY_ZIPCODE, zipcode)
            fragment.arguments = bundle
            return fragment
        }

    }

}
