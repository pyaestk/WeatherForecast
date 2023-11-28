package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.utils.formatTempForDisplay

class ForecastDetailActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_detail)

//        setTitle(R.string.forecast_detail)
        supportActionBar?.title = "Forecast Details"

        val temperature: TextView = findViewById(R.id.temptextView)
        val description: TextView = findViewById(R.id.destextView)
        
//        temperature.text = String.format("%.2f°", intent.getFloatExtra("temp", 0f))
        temperature.text = formatTempForDisplay(intent.getFloatExtra("temp", 0f))
        description.text = intent.getStringExtra("des")
    }
}