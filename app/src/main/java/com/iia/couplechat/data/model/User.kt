package com.iia.couplechat.data.model

data class User(
    val userId: String?,
    val firstName: String,
    val lastName: String = "",
    val profilePictureUri: String = ""
)
