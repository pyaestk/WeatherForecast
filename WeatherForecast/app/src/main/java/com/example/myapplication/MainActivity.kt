 package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.DailyForecast
import com.example.myapplication.repository.ForecastRepo

 class MainActivity : AppCompatActivity() {

    private val forecastRepo = ForecastRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText: EditText = findViewById(R.id.zipcodeEditText)
        val submit: Button = findViewById(R.id.enterButton)

        submit.setOnClickListener{
            val zipCode: String = zipcodeEditText.text.toString()
            if (zipCode.length != 5) {
                Toast.makeText(this, "Enter valid zipcode", Toast.LENGTH_SHORT).show()
            } else {
                forecastRepo.loadForecast(zipCode)
            }
        }

        //adapter
        val recyclerView: RecyclerView = findViewById(R.id.forecastList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val forecastAdapter = ForecastAdapter {
            val msg = getString(R.string.forecast_clicked_format, it.temp, it.description)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = forecastAdapter

        //Live data part
        forecastRepo.weeklyForecast.observe(this, Observer { forecastItems ->
            //update list UI (adapter)
            forecastAdapter.submitList(forecastItems)
        })
        
    }
}