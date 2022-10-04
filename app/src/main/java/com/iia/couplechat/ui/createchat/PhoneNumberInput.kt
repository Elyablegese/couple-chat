package com.iia.couplechat.ui.createchat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.couplechat.data.model.Country
import countries

@ExperimentalMaterial3Api
@Composable
fun PhoneNumberInput(
    country: Country,
    modifier: Modifier = Modifier,
    countryCodeChanged: (String) -> Unit = {},
    phoneNumberChanged: (String) -> Unit = {}
) {
    var countryCode by remember { mutableStateOf("${country.countryCode}") }
    var phoneNumber by remember { mutableStateOf("") }
    val focusManager: FocusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        TextField(
            value = countryCode,
            onValueChange = {
                countryCode = it
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
                phoneNumber = it
                phoneNumberChanged(it)
            },
            placeholder = {
                Text(text = country.format.replace('X', '0'))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.weight(.6f)
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PhoneNumberInputPreview() {
    PhoneNumberInput(
        country = Country(
            countryCode = 1876,
            shortName = "JM",
            name = "Jamaica",
            format = "XXX XXXX",
            url = "https://flagcdn.com/24x18/jm.png"
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    )
}