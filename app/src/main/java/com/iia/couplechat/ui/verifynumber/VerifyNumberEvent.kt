package com.iia.couplechat.ui.verifynumber

import android.app.Activity
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class VerifyNumberEvent {
    class VerificationCodeChanged(
        val verificationCode: VerificationCode,
        val value: String,
        val navigator: DestinationsNavigator,
        val activity: Activity
    ) : VerifyNumberEvent()
}
