package com.example.myapplication.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.utils.AppNavigator

class LocationEntryFragment : Fragment() {

    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator //'as' mean cast variable to another type
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_entry, container, false)
        val zipcodeEditText: EditText = view.findViewById(R.id.zipcodeEditText)
        val submit: Button = view.findViewById(R.id.enterButton)

        submit.setOnClickListener{
            val zipCode: String = zipcodeEditText.text.toString()
            if (zipCode.length != 5) {
                Toast.makeText(requireContext(), "Enter valid zipcode", Toast.LENGTH_SHORT).show()
            } else {
                appNavigator.navigateToCurrentForecast(zipCode)
//                forecastRepo.loadForecast(zipCode)
            }
        }
        return view
    }
}