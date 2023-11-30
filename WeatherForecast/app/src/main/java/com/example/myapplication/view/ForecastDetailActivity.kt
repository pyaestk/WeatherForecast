package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay

class ForecastDetailActivity : AppCompatActivity() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_detail)

        setTitle(R.string.forecast_detail)
//        supportActionBar?.title = "Forecast Details"

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        val temperature: TextView = findViewById(R.id.temptextView)
        val description: TextView = findViewById(R.id.destextView)
        
//        temperature.text = String.format("%.2f°", intent.getFloatExtra("temp", 0f))
        temperature.text = formatTempForDisplay(intent.getFloatExtra("temp", 0f), tempDisplaySettingManager.getTempDisplaySetting())
        description.text = intent.getStringExtra("des")
    }


}