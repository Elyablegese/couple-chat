package com.iia.couplechat.data.db

import androidx.room.Dao
import androidx.room.Query
import com.iia.couplechat.data.model.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries ORDER BY name ASC")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM countries where country_code = :countryCode")
    suspend fun getCountry(countryCode: Int): Country?
}