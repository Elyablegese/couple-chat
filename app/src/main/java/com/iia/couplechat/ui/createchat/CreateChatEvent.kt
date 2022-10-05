package com.iia.couplechat.ui.createchat

sealed class CreateChatEvent{
    class CountryChanged(val countryName: String): CreateChatEvent()
    class PhoneNumberChanged(val phoneNumber: String): CreateChatEvent()
    class CountryCodeChanged(val countryCode: String): CreateChatEvent()
    class PhoneNumberFormatChanged(val phoneNumberFormat: String): CreateChatEvent()
    class URLChanged(val url: String): CreateChatEvent()
}
