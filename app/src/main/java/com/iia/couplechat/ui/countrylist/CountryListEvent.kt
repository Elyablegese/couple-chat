package com.iia.couplechat.ui.countrylist

sealed class CountryListEvent {
    class OnResultBack(val result: Int) : CountryListEvent()
}
