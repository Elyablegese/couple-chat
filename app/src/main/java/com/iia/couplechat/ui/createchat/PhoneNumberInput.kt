package com.iia.couplechat.ui.createchat

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import countries

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun PhoneNumberInput(
    countryCode: String,
    phoneNumber: String,
    phoneNumberFormat: String,
    modifier: Modifier = Modifier,
    countryCodeChanged: (String) -> Unit = {},
    phoneNumberChanged: (String) -> Unit = {},
) {
    val focusManager: FocusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        TextField(
            value = countryCode,
            onValueChange = {
                countryCodeChanged(it)
                if (countries.any { country -> country.countryCode.toString() == it })
                    focusManager.moveFocus(FocusDirection.Next)
            },
            leadingIcon = {
                Text(text = "+")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.weight(.4f)
        )

        TextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumberChanged(it)
            },
            placeholder = {
                Text(text = phoneNumberFormat.replace('X', '0'))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Previous
            ),
            keyboardActions = KeyboardActions(
                onPrevious = {
                    Log.d("TAG", "PhoneNumberInput: $phoneNumber")
                }
            ),
            singleLine = true,
            modifier = Modifier
                .weight(.6f)
                .onKeyEvent {
                    if (it.key == Key.Backspace && phoneNumber.length <= 1) {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    true
                }
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PhoneNumberInputPreview() {
    PhoneNumberInput(
        countryCode = "1876",
        phoneNumber = "",
        phoneNumberFormat = "XXX XXXX",
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    )
}