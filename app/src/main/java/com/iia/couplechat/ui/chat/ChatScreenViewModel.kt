package com.iia.couplechat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import org.apache.commons.lang3.RandomStringUtils


class ChatScreenViewModel : ViewModel() {
    val uiState = MutableStateFlow(ChatScreenState())
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    init {
        firestore.collection("users")
            .whereEqualTo("userId", auth.uid)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {
                    userDocumentReferenceChanged(querySnapshot.documents[0].reference)
                }
            }
    }

    fun handleEvent(event: ChatScreenEvent) {
        when (event) {
            is ChatScreenEvent.InvitationCodeChanged -> invitationCodeChanged(event.invitationCode)
            is ChatScreenEvent.InvitationCodeVisibilityChanged -> invitationCodeVisibilityChanged(
                event.invitationCodeVisible
            )
            is ChatScreenEvent.JoinedChanged -> joinedChanged(event.joined)
            ChatScreenEvent.ChangeInvitationCode -> changeInvitationCode()
        }
    }

    private fun invitationCodeChanged(invitationCode: String) {
        uiState.value = uiState.value.copy(invitationCode = invitationCode)
    }

    private fun invitationCodeVisibilityChanged(invitationCodeVisible: Boolean) {
        uiState.value = uiState.value.copy(invitationCodeVisible = invitationCodeVisible)
    }

    private fun joinedChanged(joined: Boolean) {
        uiState.value = uiState.value.copy(joined = joined)
    }

    private fun userDocumentReferenceChanged(userDocumentReference: DocumentReference?) {
        uiState.value = uiState.value.copy(userDocumentReference = userDocumentReference)
        listenForUserDocumentReferenceChange()
    }

    private fun invitationCodeLoadingChanged(invitationCodeLoading: Boolean){
        uiState.value = uiState.value.copy(changeInvitationCodeLoading = invitationCodeLoading)
    }

    private fun changeInvitationCode() {
        invitationCodeLoadingChanged(true)
        val invitationCode = RandomStringUtils.random(8, true, true)

        uiState.value.userDocumentReference?.let { documentReference ->
            documentReference.update(mapOf("invitationCode" to invitationCode))
                .addOnSuccessListener {
                    invitationCodeChanged(invitationCode)
                    invitationCodeLoadingChanged(false)
                }
                .addOnFailureListener {
                    invitationCodeLoadingChanged(false)
                }
        }
        invitationCodeLoadingChanged(false)
    }

    private fun listenForUserDocumentReferenceChange() {
        uiState.value.userDocumentReference?.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.d("TAG", "listenForUserDocumentReferenceChange: $exception")
            }

            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites())
                "Local"
            else
                "Server"

            if (snapshot != null && snapshot.exists()) {
                Log.d(
                    "TAG",
                    "listenForUserDocumentReferenceChange: $source snapshot data is ${snapshot.data}"
                )
                snapshot.data?.let { data ->
                    joinedChanged((data["joined"] ?: false) as Boolean )
                    invitationCodeChanged(data["invitationCode"] as String)
                    Log.d(
                        "TAG",
                        "listenForUserDocumentReferenceChange: joined result ${data["joined"]}"
                    )
                }
            } else {
                Log.d("TAG", "listenForUserDocumentReferenceChange: snapshot data is null")
            }
        }
    }
}