package com.iia.couplechat.ui.profile

data class ProfilePageState(
    val firstName: String = "",
    val lastName: String = "",
    val photoUrl: String = "",
    val message: String = "",
){
    fun isValid() = firstName.isNotEmpty()
}
