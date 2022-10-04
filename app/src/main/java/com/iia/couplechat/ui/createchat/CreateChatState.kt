package com.iia.couplechat.ui.createchat

import EmptyCountry
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class CreateChatState {
    var country = EmptyCountry
    var phoneNumber = ""
}

@Composable
fun rememberCreateChatState() = remember { mutableStateOf(CreateChatState()) }