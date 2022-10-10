package com.iia.couplechat.ui.profile

sealed class ProfilePageEvent{
    class FirstNameChanged(val firstName: String): ProfilePageEvent()
    class LastNameChanged(val lastName: String): ProfilePageEvent()
    class MessageChanged(val message: String): ProfilePageEvent()
    object OnSave: ProfilePageEvent()
}
