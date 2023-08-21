package com.lesa.pogodka

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import com.lesa.pogodka.data.WeatherModel
import com.lesa.pogodka.data.getData
import com.lesa.pogodka.screens.MainScreen
import com.lesa.pogodka.ui.theme.PogodkaTheme
import com.lesa.pogodka.utils.DialogSearch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PogodkaTheme {
                val configuration = LocalConfiguration.current
                val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
                val daysList = remember {
                    mutableStateOf(listOf<WeatherModel>())
                }
                val dialogState = remember {
                    mutableStateOf(false)
                }
                val currentDay = remember {
                    mutableStateOf(WeatherModel(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ))
                }
                val city = remember {
                    mutableStateOf("Kolbasa")
                }
                if (dialogState.value) DialogSearch(
                    dialogState, onSubmit = {
                        getData(it, this, daysList, currentDay)
                        city.value = it
                    })
                getData(city.value, this, daysList, currentDay)
                MainScreen(
                    daysList,
                    currentDay,
                    isPortrait,
                    onClickSync = {
                        getData(
                            city.value,
                            this@MainActivity,
                            daysList,
                            currentDay
                        )
                    },
                    onClickSearch = {
                        dialogState.value = true
                    }
                )
            }
        }
    }
}

