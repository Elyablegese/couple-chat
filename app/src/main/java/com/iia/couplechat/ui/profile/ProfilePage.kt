package com.iia.couplechat.ui.profile

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iia.couplechat.ui.navigation.ProfileNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@ProfileNavGraph(start = true)
@Destination
@ExperimentalMaterial3Api
@Composable
fun ProfilePage(
    profilePageViewModel: ProfilePageViewModel = viewModel()
) {
    val uiState by profilePageViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.message) {
        Log.d("TAG", "ProfilePage: effect called message: ${uiState.message}")
        if (uiState.message.isNotEmpty()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = uiState.message,
                    duration = SnackbarDuration.Indefinite,
                    actionLabel = "Ok"
                )
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                Snackbar(action = {
                    profilePageViewModel.handleEvent(ProfilePageEvent.MessageChanged(""))
                }) {
                    Text(text = snackbarData.visuals.message)
                }
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                actions = {
                    IconButton(
                        onClick = {
                            profilePageViewModel.handleEvent(ProfilePageEvent.OnSave)
                        },
                        enabled = uiState.isValid()
                    ) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = "")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {


                val lastNameFocusRequester = FocusRequester()

                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .width(164.dp)
                        .height(164.dp)
                        .clip(shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.PhotoCamera,
                        contentDescription = "",
                        modifier = Modifier
                            .width(68.dp)
                            .height(62.dp)
                    )
                }

                Text(
                    text = "Choose a photo",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = uiState.firstName,
                    onValueChange = {
                        profilePageViewModel.handleEvent(
                            ProfilePageEvent.FirstNameChanged(
                                it
                            )
                        )
                    },
                    label = { Text(text = "First Name") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { lastNameFocusRequester.requestFocus() }),
                )

                OutlinedTextField(
                    value = uiState.lastName,
                    onValueChange = {
                        profilePageViewModel.handleEvent(
                            ProfilePageEvent.LastNameChanged(
                                it
                            )
                        )
                    },
                    label = { Text(text = "Last Name") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { }),
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}