package com.iia.couplechat.ui.profile

import android.net.Uri

sealed class ProfilePageEvent{
    class FirstNameChanged(val firstName: String): ProfilePageEvent()
    class LastNameChanged(val lastName: String): ProfilePageEvent()
    class MessageChanged(val message: String): ProfilePageEvent()
    object OnSave: ProfilePageEvent()
    class ShowPermissionChanged(val shouldShowPermission: Boolean): ProfilePageEvent()
    class PermissionGrantedChanged(val permissionGranted: Boolean): ProfilePageEvent()
    class ImageUriChanged(val imageUri: Uri?): ProfilePageEvent()
    class LoadingChanged(val loading: Boolean): ProfilePageEvent()
}
