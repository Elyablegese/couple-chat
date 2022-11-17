package com.iia.couplechat.ui.createchat

import com.iia.couplechat.ui.destinations.DirectionDestination

data class CreateChatState(
    val phoneNumber: String = "",
    val countryName: String = "",
    val countryCode: String = "",
    val phoneNumberFormat: String = "",
    val url: String = "",
    val codeSent: Boolean = false,
    val verificationId: String = "",
    val loading: Boolean = false,
    val direction: DirectionDestination? = null

) {
    fun isValid(): Boolean =
        phoneNumber.isNotEmpty() && phoneNumber.length > 6 && countryCode.length > 1
}