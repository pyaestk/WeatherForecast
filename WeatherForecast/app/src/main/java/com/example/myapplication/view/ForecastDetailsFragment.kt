package com.example.myapplication.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.myapplication.databinding.FragmentForecastDetailBinding
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay
import com.example.myapplication.viewModel.ForecastDetailsViewModel
import com.example.myapplication.viewModel.ForecastDetailsViewState

class ForecastDetailsFragment : Fragment() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    private val args: ForecastDetailsFragmentArgs by navArgs()

    private var _binding: FragmentForecastDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel = ForecastDetailsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForecastDetailBinding.inflate(inflater, container, false)
//        val view = inflater.inflate(R.layout.fragment_forecast_detail, container, false)
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())
        
        binding.materialToolbar.title = "Forecast Detail"
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


        //don't need anymore
/*        binding.dateTextView.text = args.date
        val iconId = args.icon
        binding.DetailImage.load("http://openweathermap.org/img/wn/${iconId}@2x.png")
        binding.temptextView.text = formatTempForDisplay(args.temp, tempDisplaySettingManager.getTempDisplaySetting())
        binding.destextView.text = args.description*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewStateObserver = Observer<ForecastDetailsViewState> { viewState ->
            binding.temptextView.text = formatTempForDisplay(viewState.temp, tempDisplaySettingManager.getTempDisplaySetting())
            binding.destextView.text = viewState.des
            binding.dateTextView.text = viewState.date
            binding.DetailImage.load(viewState.icon)
        }
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.processArgs(args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}