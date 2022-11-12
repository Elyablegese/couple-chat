package com.iia.couplechat.ui.components

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.couplechat.ui.theme.CoupleChatShapes

@ExperimentalMaterial3Api
@Composable
fun CoupleChatOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: InteractionSource = remember { MutableInteractionSource() },
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: @Composable (()-> Unit)? = null,
    trailingIcon: @Composable (()-> Unit)? = null,
) {
    val colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(.5f)
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .heightIn(min = TextFieldDefaults.MinHeight)
            .widthIn(min = TextFieldDefaults.MinWidth),
        decorationBox = { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
                isError = isError,
                container = {
                    TextFieldDefaults.OutlinedBorderContainerBox(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = CoupleChatShapes.medium,
                        unfocusedBorderThickness = 2.dp,
                        focusedBorderThickness = 3.dp
                    )
                },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon
            )
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
        cursorBrush = SolidColor(value = MaterialTheme.colorScheme.onBackground)
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun CoupleChatOutlinedTextFieldPreview() {
    CoupleChatOutlinedTextField(
        value = "XYDJHTS",
        onValueChange = {},
        enabled = true,
        singleLine = true,
        isError = false,
        visualTransformation = VisualTransformation.None,
        interactionSource = remember { MutableInteractionSource() }
    )
}