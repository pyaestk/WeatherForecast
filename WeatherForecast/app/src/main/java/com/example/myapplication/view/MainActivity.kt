 package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.showTempDisplayDialog
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

 class MainActivity : AppCompatActivity() {


     private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        //for app bar
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<MaterialToolbar>(R.id.materialToolbar).setTitle(R.string.app_name)
        findViewById<BottomNavigationView>(R.id.bottomNavigationbar).setupWithNavController(navController)

    }

//     //for menu
//     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//         val inflater: MenuInflater = menuInflater
//         inflater.inflate(R.menu.menu, menu)
//         return true
//     }
//
//     override fun onOptionsItemSelected(item: MenuItem): Boolean {
//         return when(item.itemId) {
//             R.id.tempDisplaySetting -> {
//                 showTempDisplayDialog(this, tempDisplaySettingManager)
////                Toast.makeText(this, "clicked menu item", Toast.LENGTH_SHORT).show()
//                 return true
//             }
//             else -> super.onOptionsItemSelected(item)
//         }
//     }


 }