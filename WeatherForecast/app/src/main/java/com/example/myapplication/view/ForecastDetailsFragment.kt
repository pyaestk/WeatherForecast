package com.example.myapplication.view

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay
import com.google.android.material.appbar.MaterialToolbar

class ForecastDetailsFragment : Fragment() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    private val args: ForecastDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forecast_detail, container, false)

        view.findViewById<MaterialToolbar>(R.id.materialToolbar).setTitle("Forecast Detail")
        view.findViewById<MaterialToolbar>(R.id.materialToolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val temperature: TextView = view.findViewById(R.id.temptextView)
        val description: TextView = view.findViewById(R.id.destextView)

        temperature.text = formatTempForDisplay(args.temp, tempDisplaySettingManager.getTempDisplaySetting())
        description.text = args.description

        return view
    }


}