package com.iia.couplechat.ui.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iia.couplechat.data.repository.country.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val countryRepository: CountryRepository): ViewModel() {
    val uiState = MutableStateFlow(CountryListState())

    init {
        getAllCountries()
    }

    private fun getAllCountries(){
        viewModelScope.launch {
            val countries = countryRepository.getAllCountries()
            uiState.value = uiState.value.copy(countries = countries)
        }
    }

    fun handleEvent(event: CountryListEvent){
        when(event){
            is CountryListEvent.OnResultBack -> {
                uiState.value = uiState.value.copy(result = event.result)
            }
        }
    }
}