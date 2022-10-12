package com.iia.couplechat.ui.verifynumber

data class VerifyNumberState(
    val code1: String = "",
    val code2: String = "",
    val code3: String = "",
    val code4: String = "",
    val code5: String = "",
    val code6: String = "",
    val loading: Boolean = false
){
    fun isCodeValid(): Boolean =
        !(listOf(code1, code2, code3, code4, code5, code6).any { it.isEmpty() })

    val verificationCode
        get() = listOf(code1, code2, code3, code4, code5, code6).joinToString(separator = "")
}
