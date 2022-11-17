package com.iia.couplechat.ui.createchat

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.iia.couplechat.R
import com.iia.couplechat.ui.components.CoupleChatAppBar
import com.iia.couplechat.ui.components.LoadingIcon
import com.iia.couplechat.ui.destinations.CountryListDestination
import com.iia.couplechat.ui.navigation.CreateChatNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@CreateChatNavGraph(start = true)
@Destination
@Composable
fun CreateChatPage(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<CountryListDestination, Int> = EmptyResultRecipient(),
    createChatViewModel: CreateChatViewModel
) {
    val uiState by createChatViewModel.uiState.collectAsState()
    val activity = LocalContext.current as Activity

    resultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                createChatViewModel.handleEvent(CreateChatEvent.CountryCodeChanged(result.value))
            }
        }
    }

    if (uiState.direction != null) {
        LaunchedEffect(uiState.direction){
            navigator.navigate(uiState.direction!!)
        }
    }
    Scaffold(
        topBar = {
            CoupleChatAppBar(
                title = stringResource(id = R.string.create_chat),
                navigationIcon = Icons.Default.ArrowBack
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = uiState.isValid() && uiState.codeSent.not(),
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                FloatingActionButton(
                    onClick = {
                        createChatViewModel.handleEvent(CreateChatEvent.OnSendCode(activity))
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ) {
                    LoadingIcon(loading = uiState.loading, imageVector = Icons.Default.ArrowForward)
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(.75f)
            ) {
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
                        createChatViewModel.handleEvent(CreateChatEvent.CountryCodeChanged(countryCode.toInt()))
                    },
                    phoneNumberChanged = { phoneNumber ->
                        createChatViewModel.handleEvent(CreateChatEvent.PhoneNumberChanged(phoneNumber))
                    }
                )
            }
        }

    }
}
