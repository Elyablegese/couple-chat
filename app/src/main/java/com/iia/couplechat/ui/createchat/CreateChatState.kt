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
    val code1: String = "",
    val code2: String = "",
    val code3: String = "",
    val code4: String = "",
    val code5: String = "",
    val code6: String = "",
) {
    fun isValid(): Boolean =
        phoneNumber.isNotEmpty() && phoneNumber.length > 6 && countryCode.length > 1

    fun isCodeValid(): Boolean =
        !(listOf(code1, code2, code3, code4, code5, code6).any { it.isEmpty() })

    val verificationCode
        get() = listOf(code1, code2, code3, code4, code5, code6).joinToString(separator = "")
}