package com.iia.couplechat.ui.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.iia.couplechat.ui.components.CoupleChatAppBar

@ExperimentalMaterial3Api
@Composable
fun ChatScreen() {
    Scaffold(
        topBar = { CoupleChatAppBar(title = "Waiting for peers") }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}