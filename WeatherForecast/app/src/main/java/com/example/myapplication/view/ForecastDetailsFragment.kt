package com.example.myapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.utils.AppNavigator
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay

class ForecastDetailsFragment : Fragment() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator //'as' mean cast variable to another type
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forecast_detail, container, false)

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val temperature: TextView = view.findViewById(R.id.temptextView)
        val description: TextView = view.findViewById(R.id.destextView)

//        temperature.text = formatTempForDisplay(intent.getFloatExtra("temp", 0f), tempDisplaySettingManager.getTempDisplaySetting())
//        description.text = intent.getStringExtra("des")
        return view
    }


}