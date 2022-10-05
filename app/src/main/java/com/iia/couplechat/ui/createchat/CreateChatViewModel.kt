package com.iia.couplechat.ui.createchat

import EmptyCountry
import androidx.lifecycle.ViewModel
import countries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class CreateChatViewModel : ViewModel() {
    val uiState = MutableStateFlow(CreateChatState())

    private fun countryNameChanged(countryName: String) {
        val country = countries.firstOrNull { it.name == countryName } ?: EmptyCountry
        uiState.value = uiState.value.copy(
            countryName = countryName,
            countryCode = country.countryCode.toString(),
            phoneNumberFormat = country.format,
            url = country.url
        )
    }

    private fun phoneNumberChanged(phoneNumber: String) {
        uiState.value = uiState.value.copy(
            phoneNumber = phoneNumber
        )
    }

    private fun phoneNumberFormatChanged(phoneNumberFormat: String) {
        uiState.value = uiState.value.copy(
            phoneNumberFormat = phoneNumberFormat
        )
    }

    private fun urlChanged(url: String) {
        uiState.value = uiState.value.copy(
            url = url
        )
    }

    private fun countryCodeChanged(countryCode: String) {
        val country = countries.firstOrNull { it.countryCode.toString() == countryCode } ?: EmptyCountry
        uiState.value = uiState.value.copy(
            countryName = country.name,
            countryCode = countryCode,
            phoneNumberFormat = country.format,
            url = country.url
        )
    }

    fun handleEvent(event: CreateChatEvent) {
        when (event) {
            is CreateChatEvent.CountryChanged -> countryNameChanged(event.countryName)
            is CreateChatEvent.PhoneNumberChanged -> phoneNumberChanged(event.phoneNumber)
            is CreateChatEvent.PhoneNumberFormatChanged -> phoneNumberFormatChanged(event.phoneNumberFormat)
            is CreateChatEvent.URLChanged -> urlChanged(event.url)
            is CreateChatEvent.CountryCodeChanged -> countryCodeChanged(event.countryCode)
        }
    }
}