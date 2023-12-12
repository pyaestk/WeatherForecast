package com.example.myapplication.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.ForecastAdapter
import com.example.myapplication.model.api.DailyForecast
import com.example.myapplication.model.api.WeeklyForecast
import com.example.myapplication.repository.ForecastRepo
import com.example.myapplication.repository.Location
import com.example.myapplication.repository.LocationRepo
import com.example.myapplication.utils.TempDisplaySettingManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeeklyForecastFragment : Fragment() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepo = ForecastRepo()
    private lateinit var locationRepo: LocationRepo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_weekly_forecast, container, false)
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())
        val zipcode = arguments?.getString(KEY_ZIPCODE) ?: ""

        //adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.forecastList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val forecastAdapter = ForecastAdapter(tempDisplaySettingManager) {
            showForecastDetails(it)
        }
        recyclerView.adapter = forecastAdapter

        val weeklyForecastObserver = Observer<WeeklyForecast> { weeklyForecast ->
            //update list UI (adapter)
            Log.d("WeeklyForecastFragment", "Observer triggered: $weeklyForecast")
            forecastAdapter.submitList(weeklyForecast.daily)
        }

        forecastRepo.weeklyForecast.observe(viewLifecycleOwner, weeklyForecastObserver) //this


        //fab
        val locationEntryButton: FloatingActionButton = view.findViewById(R.id.fab)
        locationEntryButton.setOnClickListener {

            val action = WeeklyForecastFragmentDirections.actionWeeklyForecastFragmentToLocationEntryFragment()
            findNavController().navigate(action)

        }

        locationRepo = LocationRepo(requireContext())
        val savedLocationObserver = Observer<Location> { savedLocation ->
            when(savedLocation) {
                is Location.Zipcode -> forecastRepo.loadWeeklyForecast(savedLocation.zipcode)
            }
        }
        locationRepo.savedLocation.observe(viewLifecycleOwner, savedLocationObserver)

        return view
    }

    private fun showForecastDetails(forecast: DailyForecast) {
        val temp = forecast.temp.max
        val description = forecast.weather[0].description
        val action = WeeklyForecastFragmentDirections.actionWeeklyForecastFragmentToForecastDetailsFragment(temp, description)
        findNavController().navigate(action)
    }

    companion object {
        const val KEY_ZIPCODE = "key_zipcode"

        fun newInstance(zipcode: String) : WeeklyForecastFragment {
            val fragment = WeeklyForecastFragment()

            val args = Bundle()
            args.putString(KEY_ZIPCODE, zipcode)
            fragment.arguments = args

            return fragment
        }
    }

}