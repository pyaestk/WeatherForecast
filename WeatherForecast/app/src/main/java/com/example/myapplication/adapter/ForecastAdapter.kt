package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.utils.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay
import com.example.myapplication.model.api.DailyForecast

class ForecastAdapter(
    private val tempDisplaySettingManager: TempDisplaySettingManager,
    private val clickHandler: (DailyForecast) -> Unit
): ListAdapter<DailyForecast, ForecastAdapter.DailyForecastViewHolder>(DIFF_CONFIG) {

    class DailyForecastViewHolder(view: View, private val tempDisplaySettingManager: TempDisplaySettingManager):RecyclerView.ViewHolder(view) {

        private val tempText: TextView = view.findViewById(R.id.tempText)
        private val desText: TextView = view.findViewById(R.id.desText)
        private val dateText: TextView = view.findViewById(R.id.dateText)
        private val icon: ImageView = view.findViewById(R.id.forecastIcon)
        fun bind(dailyForecast: DailyForecast) {
            tempText.text = formatTempForDisplay(dailyForecast.main.temp, tempDisplaySettingManager.getTempDisplaySetting())
            desText.text = dailyForecast.weather[0].description
            dateText.text = dailyForecast.dateTimeText

            val iconId = dailyForecast.weather[0].icon
            icon.load("http://openweathermap.org/img/wn/${iconId}@2x.png")
        }

    }

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.forecastitem, parent, false)
        return DailyForecastViewHolder(itemView, tempDisplaySettingManager)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }

}