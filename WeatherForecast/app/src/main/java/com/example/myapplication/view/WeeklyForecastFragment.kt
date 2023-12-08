package com.example.myapplication.view

import android.os.Bundle
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
import com.example.myapplication.model.DailyForecast
import com.example.myapplication.repository.ForecastRepo
import com.example.myapplication.utils.TempDisplaySettingManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeeklyForecastFragment : Fragment() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepo = ForecastRepo()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val zipcode = arguments?.getString(KEY_ZIPCODE) ?: ""
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weekly_forecast, container, false)

        //fab
        val locationEntryButton: FloatingActionButton = view.findViewById(R.id.fab)
        locationEntryButton.setOnClickListener {

            val action = WeeklyForecastFragmentDirections.actionWeeklyForecastFragmentToLocationEntryFragment()
            findNavController().navigate(action)
            
        }

        //adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.forecastList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val forecastAdapter = ForecastAdapter(tempDisplaySettingManager) {

            val action = WeeklyForecastFragmentDirections.actionWeeklyForecastFragmentToForecastDetailsFragment(it.temp, it.description)
            findNavController().navigate(action)
        }
        recyclerView.adapter = forecastAdapter

        //Live data part
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            //update list UI (adapter)
            forecastAdapter.submitList(forecastItems)
        }

        forecastRepo.weeklyForecast.observe(viewLifecycleOwner, weeklyForecastObserver) //this

        forecastRepo.loadForecast(zipcode)

        return view
    }


    companion object {
        const val KEY_ZIPCODE = "key_zipcode" //defined as key
        fun newInstance(zipcode: String): WeeklyForecastFragment {
            val fragment = WeeklyForecastFragment()
            val bundle = Bundle()
            bundle.putString(KEY_ZIPCODE, zipcode)
            fragment.arguments = bundle
            return fragment
        }

    }

}