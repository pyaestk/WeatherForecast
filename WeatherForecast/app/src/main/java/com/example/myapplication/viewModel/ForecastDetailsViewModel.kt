package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.view.ForecastDetailsFragmentArgs

class ForecastDetailsViewModel(args: ForecastDetailsFragmentArgs): ViewModel() {

    private val _viewState = MutableLiveData<ForecastDetailsViewState>()
    val viewState: LiveData<ForecastDetailsViewState> = _viewState

    init {
        _viewState.value = ForecastDetailsViewState(
            temp = args.temp,
            des = args.description,
            date = args.date,
            icon = "http://openweathermap.org/img/wn/${args.icon}@2x.png"
        )
    }

}

class ForecastDetailViewModelFactory(private val args: ForecastDetailsFragmentArgs): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(ForecastDetailsViewModel::class.java)) {
           return ForecastDetailsViewModel(args) as T
       } else {
           throw IllegalArgumentException("unknown view model")
       }
    }
}