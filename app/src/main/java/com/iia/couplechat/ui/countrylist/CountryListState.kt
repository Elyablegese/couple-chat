package com.iia.couplechat.ui.countrylist

import com.iia.couplechat.data.model.Country

data class CountryListState(
    val countries: List<Country> = emptyList(),
    val result: Int = 0
)
