package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.repository.Location
import com.example.myapplication.repository.LocationRepo

class LocationEntryFragment : Fragment() {

    private lateinit var locationRepo: LocationRepo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        locationRepo = LocationRepo(requireContext())

        val view = inflater.inflate(R.layout.fragment_location_entry, container, false)
        val zipcodeEditText: EditText = view.findViewById(R.id.zipcodeEditText)
        val submit: Button = view.findViewById(R.id.enterButton)

        submit.setOnClickListener{
            val zipCode: String = zipcodeEditText.text.toString()
            if (zipCode.length != 5) {
                Toast.makeText(requireContext(), "Enter valid zipcode", Toast.LENGTH_SHORT).show()
            } else {
                locationRepo.saveLocation(Location.Zipcode(zipCode))
                findNavController().navigateUp()
//                appNavigator.navigateToCurrentForecast(zipCode)
//                forecastRepo.loadForecast(zipCode)
            }
        }
        return view
    }
}