 package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import com.example.myapplication.utils.AppNavigator
import com.example.myapplication.R
import com.example.myapplication.model.DailyForecast
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.showTempDisplayDialog

 class MainActivity : AppCompatActivity(), AppNavigator {


     private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        //fragment for location entry
//        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, LocationEntryFragment()).commit()
        
    }

     //for menu
     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         val inflater: MenuInflater = menuInflater
         inflater.inflate(R.menu.menu, menu)
         return true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when(item.itemId) {
             R.id.tempDisplaySetting -> {
                 showTempDisplayDialog(this, tempDisplaySettingManager)
//                Toast.makeText(this, "clicked menu item", Toast.LENGTH_SHORT).show()
                 return true
             }
             else -> super.onOptionsItemSelected(item)
         }
     }

     //App Navigation
     override fun navigateToCurrentForecast(zipcode: String) {
         val action = LocationEntryFragmentDirections.actionLocationEntryFragmentToCurrentForecastFragment2()
         findNavController(R.id.nav_host_fragment).navigate(action)
         
//         supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, CurrentForecastFragment.newInstance(zipcode)).commit()
//         forecastRepo.loadForecast(zipcode)
     }

     override fun navigateToLocationEntry() {
         val action = CurrentForecastFragmentDirections.actionCurrentForecastFragment2ToLocationEntryFragment()
         findNavController(R.id.nav_host_fragment).navigate(action)
//         supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, LocationEntryFragment()).commit()
     }

     override fun navigateToForecastDetails(forecast: DailyForecast) {
         val action = CurrentForecastFragmentDirections.actionCurrentForecastFragment2ToForecastDetailsFragment()
         findNavController(R.id.nav_host_fragment).navigate(action)

     }


 }