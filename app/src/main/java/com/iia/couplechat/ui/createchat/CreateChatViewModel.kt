package com.iia.couplechat.ui.createchat

import EmptyCountry
import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import countries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

@HiltViewModel
class CreateChatViewModel : ViewModel() {
    val uiState = MutableStateFlow(CreateChatState())
    private var auth: FirebaseAuth = Firebase.auth

    private val callbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("TAG", "onVerificationCompleted: $credential")
        }

        override fun onVerificationFailed(firebaseException: FirebaseException) {
            Log.d("TAG", "onVerificationFailed: ", firebaseException)
        }

    }

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

    private fun sendVerificationCode(activity: Activity){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(uiState.value.phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun handleEvent(event: CreateChatEvent) {
        when (event) {
            is CreateChatEvent.CountryChanged -> countryNameChanged(event.countryName)
            is CreateChatEvent.PhoneNumberChanged -> phoneNumberChanged(event.phoneNumber)
            is CreateChatEvent.PhoneNumberFormatChanged -> phoneNumberFormatChanged(event.phoneNumberFormat)
            is CreateChatEvent.URLChanged -> urlChanged(event.url)
            is CreateChatEvent.CountryCodeChanged -> countryCodeChanged(event.countryCode)
            is CreateChatEvent.OnDone -> sendVerificationCode(event.activity)
        }
    }
}