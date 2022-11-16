package com.iia.couplechat.ui.createchat

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.couplechat.ui.theme.CoupleChatShapes
import countries

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

    BasicTextField(
        value = phoneNumber,
        onValueChange = phoneNumberChanged,
        decorationBox = { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = phoneNumber,
                innerTextField = {
                    Row(
                        verticalAlignment = CenterVertically,
                    ) {
                        Text(text = "+")
                        BasicTextField(
                            value = countryCode,
                            onValueChange = {
                                countryCodeChanged(it)
                                if (countries.any { country -> country.countryCode.toString() == it })
                                    focusManager.moveFocus(FocusDirection.Next)
                            },
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                            singleLine = true,
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
//                            modifier = Modifier.width(42.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.primary.copy(.47f)
                                )
                                .height((TextFieldDefaults.MinHeight.value * 40 / 100).dp)
                                .width(1.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        innerTextField()
                    }
                },
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                label = {
                    Text(text = "")
                },
                placeholder = {
                    Text(text = phoneNumberFormat)
                },
                container = {
                    TextFieldDefaults.OutlinedBorderContainerBox(
                        enabled = true,
                        isError = false,
                        interactionSource = remember { MutableInteractionSource() },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(.5f)
                        ),
                        shape = CoupleChatShapes.medium,
                        focusedBorderThickness = 3.dp,
                        unfocusedBorderThickness = 2.dp
                    )
                }
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .heightIn(min = TextFieldDefaults.MinHeight)
            .widthIn(min = TextFieldDefaults.MinWidth)
            .padding(8.dp)
    )
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