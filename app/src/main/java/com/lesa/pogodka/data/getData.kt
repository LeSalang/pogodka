package com.lesa.pogodka.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

const val API_KEY = "dca083611b3a451e925200834231408"
const val DAYS = 3

fun getData(
    city: String,
    context: Context,
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>
    ) {
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
            "&q=$city" +
            "&days=" +
            "$DAYS" +
            "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        {
            responce ->
                val list = getWeatherByDays(responce)
                currentDay.value = list[0]
                daysList.value = list
           // Log.d("myLog", "response = $responce")
        },
        {
            Log.d("myLog", "volley error $it")
        }
    )
    queue.add(sRequest)
}

fun getWeatherByDays(response: String): List<WeatherModel> {
    if (response.isEmpty()) return emptyList()
    val list = ArrayList<WeatherModel>()
    val mainObject = JSONObject(response)
    val city = mainObject.getJSONObject("location").getString("name")
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
    for (i in 0 until days.length()) {
        val item = days[i] as JSONObject
        list.add(
            WeatherModel(
                city = city,
                time = item.getString("date"),
                currentTemp = "",
                condition = item.getJSONObject("day").getJSONObject("condition")
                    .getString("text"),
                icon = item.getJSONObject("day").getJSONObject("condition")
                    .getString("icon"),
                maxTemp = item.getJSONObject("day"). getString("maxtemp_c").toFloat().toInt().toString() + "°C",
                minTemp = item.getJSONObject("day"). getString("mintemp_c").toFloat().toInt().toString() + "°C",
                hours = item.getJSONArray("hour").toString()
            )
        )
    }
    list[0] = list[0].copy(
        time = mainObject.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObject.getJSONObject("current").getString("temp_c").toFloat().toInt().toString()
                + "\u00B0C"
    )
    return list
}

fun getWeatherByHours(hours: String): List<WeatherModel> {
    if (hours.isEmpty()) return listOf()
    val hoursArray = JSONArray(hours)
    val list = ArrayList<WeatherModel>()
    for (i in 0 until hoursArray.length()){
        val item = hoursArray[i] as JSONObject
        list.add(
            WeatherModel(
                city = "",
                time = item.getString("time"),
                currentTemp = item.getString("temp_c").toFloat().toInt().toString() + "°C",
                condition = item.getJSONObject("condition").getString("text"),
                icon = item.getJSONObject("condition").getString("icon"),
                maxTemp = "",
                minTemp = "",
                hours = ""
            )
        )
    }
    return list
}