package com.lesa.pogodka.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.lesa.pogodka.R
import com.lesa.pogodka.data.WeatherModel
import com.lesa.pogodka.ui.theme.AccentYellowGhost
import com.lesa.pogodka.ui.theme.DarkBlue


@Composable
fun MainCard(
    currentDay: MutableState<WeatherModel>,
    modifier: Modifier,
    onClickSync: () -> Unit,
    onClickSearch: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(AccentYellowGhost),
        elevation = CardDefaults.cardElevation(10.dp),
        // border = DarkBlue,
        shape = RectangleShape
    ) {
        Column(
            modifier = modifier.padding(dimensionResource(id = R.dimen.medium_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomTextBold(
                    text = currentDay.value.time,
                    fontSize = 16,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onClickSearch.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = DarkBlue
                    )
                }
                IconButton(onClick = {
                    onClickSync.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        tint = DarkBlue
                    )
                }
                /*AsyncImage(
                    model = "https:${currentDay.value.icon}",
                    contentDescription = "",
                   // colorFilter = ColorFilter.tint(DarkBlue),
                    modifier = Modifier
                        .size(55.dp)
                        .padding(dimensionResource(id = R.dimen.small_padding))
                )*/
            }
            CustomTextBold(
                text = currentDay.value.city,
                fontSize = 20
            )
            CustomTextBold(
                text = currentDay.value.currentTemp.ifEmpty { "${currentDay.value.minTemp}..${currentDay.value.maxTemp}" },
                fontSize = 60
            )
            CustomTextBold(text = currentDay.value.condition, fontSize = 20)
            /*CustomTextNorm(
                text = if (currentDay.value.currentTemp.isNotEmpty())
                    "${currentDay.value.minTemp}..${currentDay.value.maxTemp}" else "",
                fontSize = 20
            )*/
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.small_padding)))
        }
    }
}