package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import coil.load
import com.example.myapplication.view.ForecastDetailsFragmentArgs

class ForecastDetailsViewModel:ViewModel() {

    private val _viewState = MutableLiveData<ForecastDetailsViewState>()
    val viewState: LiveData<ForecastDetailsViewState> = _viewState

    fun processArgs(args: ForecastDetailsFragmentArgs) {
         _viewState.value = ForecastDetailsViewState(
             temp = args.temp,
             des = args.description,
             date = args.date,
             icon = "http://openweathermap.org/img/wn/${args.icon}@2x.png"
         )
    }

}