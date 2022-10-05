package com.iia.couplechat.ui.createchat

import EmptyCountry
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class CreateChatState(
    val phoneNumber: String = "",
    val countryName: String = "",
    val countryCode: String = "",
    val phoneNumberFormat: String = "",
    val url: String = ""
) {
    fun isValid(): Boolean =
        phoneNumber.isNotEmpty() && phoneNumber.length > 6 && countryCode.length > 1
}