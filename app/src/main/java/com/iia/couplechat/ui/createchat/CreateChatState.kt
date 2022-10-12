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
    val url: String = "",
    val codeSent: Boolean = false,
    val verificationId: String = "",
    val loading: Boolean = false

) {
    fun isValid(): Boolean =
        phoneNumber.isNotEmpty() && phoneNumber.length > 6 && countryCode.length > 1
}