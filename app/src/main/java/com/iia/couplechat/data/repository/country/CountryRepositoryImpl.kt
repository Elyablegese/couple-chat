package com.iia.couplechat.data.repository.country

import com.iia.couplechat.data.db.CoupleChatDatabase
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val coupleChatDatabase: CoupleChatDatabase) :
    CountryRepository {
    override suspend fun getAllCountries() = coupleChatDatabase.countryDao().getAllCountries()

    override suspend fun getCountry(countryCode: Int) = coupleChatDatabase.countryDao().getCountry(countryCode)
}