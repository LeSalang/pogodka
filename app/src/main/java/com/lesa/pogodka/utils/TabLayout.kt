package com.lesa.pogodka.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.lesa.pogodka.R
import com.lesa.pogodka.data.WeatherModel
import com.lesa.pogodka.data.getWeatherByHours
import com.lesa.pogodka.ui.theme.AccentYellowGhost
import com.lesa.pogodka.ui.theme.DarkBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>,
    modifier: Modifier
) {
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        tabList.size
    }
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(AccentYellowGhost),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            TabRow(
                selectedTabIndex = tabIndex,
                indicator = {tabPosition ->
                            TabRowDefaults.Indicator(
                                Modifier
                                    .tabIndicatorOffset(tabPosition[tabIndex])
                                    .fillMaxSize(0.125f),
                                    color = DarkBlue,
                                //height = 10.dp
                            )
                },
                containerColor = Color.Transparent,
                contentColor = DarkBlue,
                modifier = Modifier
                    .border(dimensionResource(id = R.dimen.small_padding), DarkBlue, RectangleShape)
            ) {
                tabList.forEachIndexed { index, text ->
                    Tab(
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            CustomTextBold(text = text, fontSize = 14)
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                Modifier
                    .fillMaxSize()

                    .background(Color.Transparent)
            ) {index ->
                val list = when(index){
                    0 -> getWeatherByHours(currentDay.value.hours)
                    //else -> getWeatherByHours(currentDay.value.hours)
                    else -> daysList.value
                }
                MainList(list = list, currentDay = currentDay)
            }
        }
    }
}