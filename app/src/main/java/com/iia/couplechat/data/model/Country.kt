package com.iia.couplechat.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("country_code")
    val countryCode: Int,
    @SerialName("short_name")
    val shortName: String,
    val name: String,
    val format: String,
    val url: String
)
