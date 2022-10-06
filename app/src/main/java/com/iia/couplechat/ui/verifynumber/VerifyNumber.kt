package com.iia.couplechat.ui.verifynumber

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.couplechat.ui.theme.CoupleChatShapes
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Destination
@Composable
fun VerifyNumber(navigator: DestinationsNavigator) {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        var opt1 by remember { mutableStateOf("") }
        var opt2 by remember { mutableStateOf("") }
        var opt3 by remember { mutableStateOf("") }
        var opt4 by remember { mutableStateOf("") }
        var opt5 by remember { mutableStateOf("") }
        var opt6 by remember { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Verify your number",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = "We have sent SMS verification code to your number. Please enter below",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(.7f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val focusManager = LocalFocusManager.current

                OtpItem(
                    char = opt1,
                    onValueChange = {
                        opt1 = it
                    },
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                )
                OtpItem(
                    char = opt2,
                    onValueChange = {
                        opt2 = it
                    },
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    onPrevious = { focusManager.moveFocus(FocusDirection.Previous) }
                )
                OtpItem(
                    char = opt3,
                    onValueChange = {
                        opt3 = it
                    },
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    onPrevious = { focusManager.moveFocus(FocusDirection.Previous) }
                )
                OtpItem(
                    char = opt4,
                    onValueChange = {
                        opt4 = it
                    },
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    onPrevious = { focusManager.moveFocus(FocusDirection.Previous) }
                )
                OtpItem(
                    char = opt5,
                    onValueChange = {
                        opt5 = it
                    },
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    onPrevious = { focusManager.moveFocus(FocusDirection.Previous) }
                )
                OtpItem(
                    char = opt6,
                    onValueChange = {
                        opt6 = it
                    },
                    onPrevious = { focusManager.moveFocus(FocusDirection.Previous) }
                )
            }

            FilledTonalButton(
                modifier = Modifier.fillMaxWidth(.65f),
                onClick = {

                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = CoupleChatShapes.medium
            ) {
                Text(
                    text = "VERIFY",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun VerifyNumberPreview() {
    VerifyNumber(EmptyDestinationsNavigator)
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun OtpItem(
    char: String,
    onValueChange: (char: String) -> Unit,
    onNext: () -> Unit = {},
    onPrevious: () -> Unit = {}
) {
    OutlinedTextField(
        value = char.toString(),
        onValueChange = {
            onValueChange(it.last().toString())
        },
        textStyle = MaterialTheme.typography.labelMedium,
        shape = CoupleChatShapes.medium,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.primary,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = { onNext() }),
        modifier = Modifier
            .width(48.dp)
            .height(56.dp)
            .onKeyEvent {
                if (it.key == Key.Backspace)
                    onPrevious()
                else
                    onNext()
                true
            }

    )
}

@ExperimentalComposeUiApi
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OtpItemPreview() {
    OtpItem("6", onValueChange = {})
}