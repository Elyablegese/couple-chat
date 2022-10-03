package com.iia.couplechat.ui.createchat

import EmptyCountry
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.couplechat.R
import com.iia.couplechat.ui.destinations.CountryListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import countries

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Destination
@Composable
fun CreateChatPage(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<CountryListDestination, String> = EmptyResultRecipient()
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = stringResource(id = R.string.create_chat)) },
                navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(.75f)
            ) {
                var country by remember { mutableStateOf(EmptyCountry) }

                resultRecipient.onNavResult { result ->
                    when (result) {
                        is NavResult.Canceled -> {}
                        is NavResult.Value -> {
                            country = countries.first { result.value == it.shortName }
                        }
                    }
                }

                CountryPicker(
                    country = country,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable {
                            navigator.navigate(CountryListDestination)
                        }
                )
                PhoneNumberInput(
                    country = country,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    countryCodeChanged = { countryCode ->
                        try {
                            country = countries.firstOrNull { it.countryCode == countryCode.toInt() } ?: EmptyCountry
                        } catch (e: Exception) {
                            println(e.message)
                        }
                    }
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun CreateChatPagePreview() {
    CreateChatPage(EmptyDestinationsNavigator)
}