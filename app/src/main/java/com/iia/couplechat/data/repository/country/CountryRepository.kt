package com.iia.couplechat.data.repository.country

import com.iia.couplechat.data.model.Country

interface CountryRepository {
    suspend fun getAllCountries(): List<Country>
    suspend fun getCountry(countryCode: Int): Country?
}