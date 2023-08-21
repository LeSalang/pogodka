package com.lesa.pogodka.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.lesa.pogodka.R
import com.lesa.pogodka.data.WeatherModel
import com.lesa.pogodka.ui.theme.DarkBlue
import com.lesa.pogodka.utils.MainCard
import com.lesa.pogodka.utils.TabLayout


@Composable
fun MainScreen(
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>,
    isPortrait: Boolean,
    onClickSync: () -> Unit,
    onClickSearch: () -> Unit
    ) {
    Image(
        painter = painterResource(id = R.drawable.pogodka_bg),
        contentDescription = "pogodka_bg",
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    if (isPortrait) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.big_padding))
        ) {
            MainCard(
                currentDay,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(dimensionResource(id = R.dimen.small_padding), DarkBlue, RectangleShape),
                onClickSync = onClickSync,
                onClickSearch = onClickSearch
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.big_padding)))
            //Spacer(modifier = Modifier.fillMaxSize(1f))
            TabLayout(
                daysList,
                currentDay,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(dimensionResource(id = R.dimen.small_padding), DarkBlue, RectangleShape)
            )
        }
    } else {
        Row(
            Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.big_padding))
        ) {
            MainCard(
                currentDay,
                modifier = Modifier
                    .fillMaxHeight()
                    .border(dimensionResource(id = R.dimen.small_padding), DarkBlue, RectangleShape)
                    .weight(1f),
                onClickSync = onClickSync,
                onClickSearch = onClickSearch
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.big_padding)))
            //Spacer(modifier = Modifier.fillMaxSize(1f))
            TabLayout(
                daysList,
                currentDay,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .border(dimensionResource(id = R.dimen.small_padding), DarkBlue, RectangleShape)
            )
            //Spacer(modifier = Modifier.fillMaxSize(1f))
        }
    }
}

