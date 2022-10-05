package com.iia.couplechat.ui.createchat

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iia.couplechat.R
import com.iia.couplechat.ui.destinations.CountryListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import countries

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Destination
@Composable
fun CreateChatPage(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<CountryListDestination, String> = EmptyResultRecipient(),
    createChatViewModel: CreateChatViewModel = viewModel()
) {
    val uiState by createChatViewModel.uiState.collectAsState()

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
        },
        floatingActionButton = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
            }
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

                resultRecipient.onNavResult { result ->
                    when (result) {
                        is NavResult.Canceled -> {}
                        is NavResult.Value -> {
                            createChatViewModel.handleEvent(CreateChatEvent.CountryChanged(countries.first { result.value == it.shortName }.name))
                        }
                    }
                }

                CountryPicker(
                    countryName = uiState.countryName,
                    url = uiState.url,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable {
                            navigator.navigate(CountryListDestination)
                        }
                )
                PhoneNumberInput(
                    countryCode = uiState.countryCode,
                    phoneNumberFormat = uiState.phoneNumberFormat,
                    phoneNumber = uiState.phoneNumber,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    countryCodeChanged = { countryCode ->
                        createChatViewModel.handleEvent(
                            CreateChatEvent.CountryCodeChanged(countryCode)
                        )

                    },
                    phoneNumberChanged = { phoneNumber ->
                        createChatViewModel.handleEvent(
                            CreateChatEvent.PhoneNumberChanged(phoneNumber)
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun CreateChatPagePreview() {
    CreateChatPage(navigator = EmptyDestinationsNavigator)
}