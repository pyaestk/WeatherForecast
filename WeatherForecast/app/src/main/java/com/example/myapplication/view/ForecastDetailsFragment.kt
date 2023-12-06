package com.example.myapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.utils.AppNavigator
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay

class ForecastDetailsFragment : Fragment() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    private val args: ForecastDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forecast_detail, container, false)

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val temperature: TextView = view.findViewById(R.id.temptextView)
        val description: TextView = view.findViewById(R.id.destextView)

        temperature.text = formatTempForDisplay(args.temp, tempDisplaySettingManager.getTempDisplaySetting())
        description.text = args.description

        return view
    }


}