package com.iia.couplechat.ui.profile

import android.net.Uri

data class ProfilePageState(
    val firstName: String = "",
    val lastName: String = "",
    val photoUrl: String = "",
    val message: String = "",
    val shouldShowPermission: Boolean = false,
    val permissionGranted: Boolean = false,
    val imageUri: Uri? = null,
    val loading: Boolean = false
){
    fun isValid() = firstName.isNotEmpty()
}
