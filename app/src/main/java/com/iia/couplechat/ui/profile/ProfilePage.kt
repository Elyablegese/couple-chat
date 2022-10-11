package com.iia.couplechat.ui.profile

import android.Manifest
import android.net.Uri
import android.util.Log
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.iia.couplechat.ui.navigation.ProfileNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
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
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            profilePageViewModel.handleEvent(ProfilePageEvent.ImageUriChanged(it))
        }

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

    LaunchedEffect(cameraPermissionState.status) {
        when (cameraPermissionState.status) {
            is PermissionStatus.Denied -> profilePageViewModel.handleEvent(
                ProfilePageEvent.ShowPermissionChanged(true)
            )
            PermissionStatus.Granted -> profilePageViewModel.handleEvent(
                ProfilePageEvent.ShowPermissionChanged(false)
            )
        }
    }

    if (uiState.shouldShowPermission) {
        AlertDialog(
            onDismissRequest = {
                profilePageViewModel.handleEvent(ProfilePageEvent.ShowPermissionChanged(false))
            },
            confirmButton = {
                TextButton(onClick = {
                    cameraPermissionState.launchPermissionRequest()
                    profilePageViewModel.handleEvent(ProfilePageEvent.ShowPermissionChanged(false))
                }) {
                    Text(text = "OK")
                }
            },
            text = {
                Text(text = "We need a camera permission to take picture or pick image from gallery")
            },
            title = {
                Text(text = "Confirmation")
            },
            icon = {
                Icon(imageVector = Icons.Default.ConfirmationNumber, contentDescription = "")
            }
        )
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
                Crossfade(uiState.imageUri) { uri ->
                    val size = Size(164, 164)
                    if (uri == null) {
                        IconButton(
                            onClick = { launcher.launch("image/*") },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .width(size.width.dp)
                                .height(size.height.dp)
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
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(uri)
                                    .build()
                            ),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(size.width.dp)
                                .height(size.height.dp)
                                .clip(CircleShape)
                                .clickable {
                                    launcher.launch("image/*")
                                }
                        )
                    }
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

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalMaterial3Api
@Preview
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}