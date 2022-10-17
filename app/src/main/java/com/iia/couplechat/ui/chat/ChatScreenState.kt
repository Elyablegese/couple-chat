package com.iia.couplechat.ui.chat

import com.google.firebase.firestore.DocumentReference

data class ChatScreenState(
    val invitationCode: String = "",
    val invitationCodeVisible: Boolean = true,
    val joined: Boolean = false,
    val userDocumentReference: DocumentReference? = null,
    val changeInvitationCodeLoading: Boolean = false
)
