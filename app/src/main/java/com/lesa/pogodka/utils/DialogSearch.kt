package com.lesa.pogodka.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.lesa.pogodka.R
import com.lesa.pogodka.ui.theme.AccentBlueGhost
import com.lesa.pogodka.ui.theme.AccentYellow
import com.lesa.pogodka.ui.theme.AccentYellowGhost
import com.lesa.pogodka.ui.theme.DarkBlue
import com.lesa.pogodka.ui.theme.DarkYellow
import com.lesa.pogodka.ui.theme.mainType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogSearch(
    dialogState: MutableState<Boolean>,
    onSubmit: (String) -> Unit
) {
    val dialogText = remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                onSubmit(dialogText.value)
                dialogState.value = false
            }) {
                CustomTextBold(text = "OK", fontSize = 16)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value = false
            }) {
                CustomTextBold(text = "Cancel", fontSize = 16)

            }
        },
        title = {
            Column(
                Modifier.fillMaxWidth()
            ) {
                CustomTextBold(text = "Write your city:", fontSize = 20)
                TextField(
                    value = dialogText.value,
                    onValueChange = {
                        dialogText.value = it
                    },
                    shape = RectangleShape,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = DarkYellow,
                        containerColor = AccentBlueGhost
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        onSubmit(dialogText.value)
                        dialogState.value = false
                    }),
                    textStyle = TextStyle(color = AccentYellow, fontFamily = mainType)
                )
            }
        },
        shape = RectangleShape,
        containerColor = AccentYellowGhost,
        textContentColor = DarkBlue,
        modifier = Modifier
            .border(dimensionResource(id = R.dimen.small_padding), DarkBlue, RectangleShape)
    )
}