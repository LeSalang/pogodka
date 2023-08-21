package com.lesa.pogodka.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.lesa.pogodka.R
import com.lesa.pogodka.data.WeatherModel
import com.lesa.pogodka.ui.theme.DarkYellow

@Composable
fun MainList(
    list: List<WeatherModel>,
    currentDay: MutableState<WeatherModel>
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        itemsIndexed(
            list
        ){index, item ->
            ListItem(item, currentDay)
            Divider(
                color = DarkYellow,
                thickness = dimensionResource(id = R.dimen.micro_padding)
            )
        }
        //Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.big_padding)))
    }
}