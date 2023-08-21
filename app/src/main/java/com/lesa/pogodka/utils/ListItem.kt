package com.lesa.pogodka.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lesa.pogodka.R
import com.lesa.pogodka.data.WeatherModel

@Composable
fun ListItem(
    item: WeatherModel,
    currentDay: MutableState<WeatherModel>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(top = 2.dp)
            .padding(top = dimensionResource(id = R.dimen.small_padding))
            .clickable {
                if (item.hours.isEmpty()) return@clickable
                currentDay.value = item
            }
            //.border(dimensionResource(id = R.dimen.micro_padding), DarkBlue, RectangleShape)
            ,
        colors = CardDefaults.cardColors(Color.Transparent),
       // elevation = CardDefaults.cardElevation(10.dp),
        // border = DarkBlue,
        shape = RectangleShape
    ){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.medium_padding)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier.weight(2f)
            ) {
                CustomTextNorm(text = item.time, fontSize = 12)
                CustomTextNorm(text = item.condition, fontSize = 12)
            }
            CustomTextBold(
                text = item.currentTemp.ifEmpty { "${item.minTemp}..${item.maxTemp}" },
                fontSize = 18,
                modifier = Modifier.weight(2f)
            )
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "",
                Modifier
                    .size(35.dp)
                    .weight(1f)
            )
            /*
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "",
                Modifier.size(35.dp),
                tint = DarkBlue
            )
            */

        }
    }
}