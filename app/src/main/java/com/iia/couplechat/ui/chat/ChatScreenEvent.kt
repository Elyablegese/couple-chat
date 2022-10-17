package com.iia.couplechat.ui.chat

sealed class ChatScreenEvent{
    class InvitationCodeChanged(val invitationCode: String): ChatScreenEvent()
    class InvitationCodeVisibilityChanged(val invitationCodeVisible: Boolean): ChatScreenEvent()
    class JoinedChanged(val joined: Boolean): ChatScreenEvent()
    object ChangeInvitationCode: ChatScreenEvent()
}
