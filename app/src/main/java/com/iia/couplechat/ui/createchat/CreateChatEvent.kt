package com.iia.couplechat.ui.createchat

import android.app.Activity
import com.iia.couplechat.ui.verifynumber.VerificationCode
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class CreateChatEvent{
    class CountryChanged(val countryName: String): CreateChatEvent()
    class PhoneNumberChanged(val phoneNumber: String): CreateChatEvent()
    class CountryCodeChanged(val countryCode: String): CreateChatEvent()
    class PhoneNumberFormatChanged(val phoneNumberFormat: String): CreateChatEvent()
    class URLChanged(val url: String): CreateChatEvent()
    class OnSendCode(val activity: Activity): CreateChatEvent()
    class OnVerifyNumber(val verificationCode: String, val activity: Activity, val navigator: DestinationsNavigator): CreateChatEvent()
    class VerificationCodeChanged(val verificationCode: VerificationCode, val value: String): CreateChatEvent()
}
