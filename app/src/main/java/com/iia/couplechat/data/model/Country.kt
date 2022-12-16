package com.iia.couplechat.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "countries")
@Serializable
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerialName("country_code")
    @ColumnInfo(name = "country_code")
    val countryCode: Int,
    @SerialName("short_name")
    @ColumnInfo(name = "short_name")
    val shortName: String,
    val name: String,
    val format: String,
    val url: String
)
