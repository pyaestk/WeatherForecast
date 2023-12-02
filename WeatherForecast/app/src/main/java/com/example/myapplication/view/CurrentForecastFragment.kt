package com.example.myapplication.view

import android.content.Context
import android.content.Intent
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
import com.example.myapplication.utils.AppNavigator
import com.example.myapplication.utils.TempDisplaySettingManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CurrentForecastFragment : Fragment() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepo = ForecastRepo()

    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator //'as' mean cast variable to another type
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val zipcode = arguments!!.getString(KEY_ZIPCODE) ?: ""
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)

        //fab
        val locationEntryButton: FloatingActionButton = view.findViewById(R.id.fab)
        locationEntryButton.setOnClickListener {

            appNavigator.navigateToLocationEntry()
            
        }

        //adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.forecastList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val forecastAdapter = ForecastAdapter(tempDisplaySettingManager) {
            showForecastDetail(it)
        }
        recyclerView.adapter = forecastAdapter

        //Live data part
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            //update list UI (adapter)
            forecastAdapter.submitList(forecastItems)
        }

        forecastRepo.weeklyForecast.observe(this, weeklyForecastObserver) //this

        forecastRepo.loadForecast(zipcode)

        return view
    }

    private fun showForecastDetail(forecast: DailyForecast) {

        appNavigator.navigateToForecastDetails(forecast)

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