package com.example.myapplication.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting): String {

    return when(tempDisplaySetting) {
        TempDisplaySetting.Fahrenheit -> String.format("%.2f F°", temp)
        TempDisplaySetting.Celsius -> {
            val temp = (temp - 32f) * (5f/9f)
            String.format("%.2f C°", temp)
        }
    }
}

fun showTempDisplayDialog(context: Context, tempDisplaySettingManager: TempDisplaySettingManager) {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setTitle("Choose temperature unit")
        .setMessage("Choose your suitable temperature type to display")
        .setPositiveButton("F°") { _,_ ->
            tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)
            Toast.makeText(context, "using F°", Toast.LENGTH_SHORT).show()
        }
//            .setNeutralButton("C", object : DialogInterface.OnClickListener {
//                override fun onClick(p0: DialogInterface?, p1: Int) {
//                    TODO("Not yet implemented")
//                }
//            })
        .setNeutralButton("C°") { _,_ ->
            tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
            Toast.makeText(context, "using C°", Toast.LENGTH_SHORT).show()
        }
//        .setOnDismissListener{
//            Toast.makeText(context, "Setting will take effect on app restart", Toast.LENGTH_SHORT).show()
//        }

    dialogBuilder.show()
}