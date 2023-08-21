package com.lesa.pogodka.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.lesa.pogodka.R
import com.lesa.pogodka.ui.theme.DarkBlue
import com.lesa.pogodka.ui.theme.mainType

@Composable
fun CustomTextBold(
    text: String,
    fontSize: Int,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center
) {
    var multiplier by remember {
        mutableFloatStateOf(1f)
    }
    Text(
        text = text,
        maxLines = 1,
        fontSize = (fontSize * multiplier).sp,
        fontFamily = mainType,
        fontWeight = FontWeight.Bold,
        color = DarkBlue,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.medium_padding)),
        onTextLayout = {
            if (it.hasVisualOverflow) {
                multiplier *= 0.9f
            }
        }
    )
}

@Composable
fun CustomTextNorm(
    text: String,
    fontSize: Int
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontFamily = mainType,
        fontWeight = FontWeight.Normal,
        color = DarkBlue,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.small_padding))
    )
}
