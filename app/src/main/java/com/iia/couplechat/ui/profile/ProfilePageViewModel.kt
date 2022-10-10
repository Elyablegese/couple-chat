package com.iia.couplechat.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.iia.couplechat.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class ProfilePageViewModel : ViewModel() {
    val uiState = MutableStateFlow(ProfilePageState())
    private val fireStore = Firebase.firestore
    private val currentUser = Firebase.auth.currentUser

    fun handleEvent(event: ProfilePageEvent) {
        when (event) {
            is ProfilePageEvent.FirstNameChanged -> firstNameChanged(event.firstName)
            is ProfilePageEvent.LastNameChanged -> lastNameChanged(event.lastName)
            ProfilePageEvent.OnSave -> save()
            is ProfilePageEvent.MessageChanged -> messageChanged(event.message)
        }
    }

    private fun firstNameChanged(firstName: String) {
        uiState.value = uiState.value.copy(firstName = firstName)
    }

    private fun lastNameChanged(lastName: String) {
        uiState.value = uiState.value.copy(lastName = lastName)
    }

    private fun save() {
        val user = User(
            userId = currentUser?.uid,
            firstName = uiState.value.firstName,
            lastName = uiState.value.lastName
        )
        fireStore.collection("users")
            .add(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    messageChanged("Successfully Saved")
                    Log.d("TAG", "save: success you son of a bitch")
                }
                else {
                    messageChanged("Not Successfully")
                    Log.d("TAG", "save: not success you son of a bitch")
                }
            }
            .addOnFailureListener { exception ->
                exception.message?.let { messageChanged(it) }
            }
    }

    private fun messageChanged(message: String) {
        uiState.value = uiState.value.copy(message = message)
    }
}