package com.iia.couplechat.ui.createchat

import EmptyCountry
import android.app.Activity
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iia.couplechat.ui.destinations.ProfilePageDestination
import com.iia.couplechat.ui.verifynumber.VerificationCode
import countries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit
import com.iia.couplechat.ui.verifynumber.VerificationCode.*
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterial3Api
@HiltViewModel
class CreateChatViewModel : ViewModel() {
    val uiState = MutableStateFlow(CreateChatState())
    private var auth: FirebaseAuth = Firebase.auth

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("TAG", "onVerificationCompleted: $credential")
        }

        override fun onVerificationFailed(firebaseException: FirebaseException) {
            Log.d("TAG", "onVerificationFailed: ", firebaseException)
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            uiState.value = uiState.value.copy(codeSent = true, verificationId = verificationId)
            Log.d("TAG", "onCodeSent: Code sent")
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
        val country =
            countries.firstOrNull { it.countryCode.toString() == countryCode } ?: EmptyCountry
        uiState.value = uiState.value.copy(
            countryName = country.name,
            countryCode = countryCode,
            phoneNumberFormat = country.format,
            url = country.url
        )
    }

    private fun sendVerificationCode(activity: Activity) {
        val phoneNumber = "+${uiState.value.countryCode}${uiState.value.phoneNumber}"
        Log.d("TAG", "sendVerificationCode: phone number $phoneNumber")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyPhoneNumber(verificationCode: String, activity: Activity, navigator: DestinationsNavigator) {
        val credential = PhoneAuthProvider.getCredential(uiState.value.verificationId, verificationCode)
        auth.signInWithCredential(credential).addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                Log.d("TAG", "verifyPhoneNumber: sign in success")
                navigator.navigate(ProfilePageDestination)
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    Log.d("TAG", "verifyPhoneNumber: verification code error")
                }
            }
        }
    }

    private fun verificationCodeChanged(verificationCode: VerificationCode, value: String) {
        when (verificationCode) {
            CODE1 -> uiState.value = uiState.value.copy(code1 = value)
            CODE2 -> uiState.value = uiState.value.copy(code2 = value)
            CODE3 -> uiState.value = uiState.value.copy(code3 = value)
            CODE4 -> uiState.value = uiState.value.copy(code4 = value)
            CODE5 -> uiState.value = uiState.value.copy(code5 = value)
            CODE6 -> uiState.value = uiState.value.copy(code6 = value)
        }
    }

    fun handleEvent(event: CreateChatEvent) {
        when (event) {
            is CreateChatEvent.CountryChanged -> countryNameChanged(event.countryName)
            is CreateChatEvent.PhoneNumberChanged -> phoneNumberChanged(event.phoneNumber)
            is CreateChatEvent.PhoneNumberFormatChanged -> phoneNumberFormatChanged(event.phoneNumberFormat)
            is CreateChatEvent.URLChanged -> urlChanged(event.url)
            is CreateChatEvent.CountryCodeChanged -> countryCodeChanged(event.countryCode)
            is CreateChatEvent.OnSendCode -> sendVerificationCode(event.activity)
            is CreateChatEvent.OnVerifyNumber -> verifyPhoneNumber(event.verificationCode, event.activity, event.navigator)
            is CreateChatEvent.VerificationCodeChanged -> verificationCodeChanged(event.verificationCode, event.value)
        }
    }


}