 package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.ForecastAdapter
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
            showForecastDetail(it)
        }
        recyclerView.adapter = forecastAdapter

        //Live data part
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            //update list UI (adapter)
            forecastAdapter.submitList(forecastItems)
        }

        forecastRepo.weeklyForecast.observe(this, weeklyForecastObserver)
        
    }

     private fun showForecastDetail(forecast: DailyForecast) {
         val intent = Intent(this, ForecastDetailActivity::class.java)
         intent.putExtra("temp", forecast.temp)
         intent.putExtra("des", forecast.description)
         startActivity(intent)
     }
}