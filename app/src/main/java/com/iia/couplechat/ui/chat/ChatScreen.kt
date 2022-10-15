package com.iia.couplechat.ui.chat

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.couplechat.ui.components.CoupleChatAppBar
import com.iia.couplechat.ui.navigation.ChatNavGraph
import com.iia.couplechat.ui.theme.CoupleChatShapes
import com.ramcosta.composedestinations.annotation.Destination

@ExperimentalMaterial3Api
@ChatNavGraph(start = true)
@Destination
@Composable
fun ChatScreen() {
    Scaffold(
        topBar = { CoupleChatAppBar(title = "Waiting for peers") }
    ) { paddingValues ->
        var expandedState by remember { mutableStateOf(true) }
        val transition =
            updateTransition(targetState = expandedState, label = "InvitationCard update animation")

        val widthWeight by transition.animateFloat(label = "Width modifier animation") { state ->
            if (state) 1f else .15f
        }
        val height by transition.animateDp(label = "Height dp") { state ->
            if (state) 228.dp else 64.dp
        }

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .height(height)
                        .fillMaxWidth(widthWeight)
                        .clip(CoupleChatShapes.large)
                        .clickable {
                            expandedState = !expandedState
                        }
                ) {
                    if (expandedState) {
                        InvitationCodeCard()
                    } else {
                        IconButton(
                            onClick = { expandedState = !expandedState },
                            colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            modifier = Modifier
                                .height(64.dp)
                                .width(64.dp)
                                .clip(shape = CoupleChatShapes.large)
                        ) {
                            Icon(imageVector = Icons.Filled.Key, contentDescription = "", tint = Color.Yellow)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun InvitationCodeCard() {
    Card {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Your invitation code...")
            Text(
                text = "X7HE5OIP",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.weight(1f)
            )
            InvitationCodeMenu {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Save,
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}

@Composable
fun InvitationCodeMenu(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary.copy(.05f),
    contentColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(56.dp)
            .fillMaxSize()
            .background(color = containerColor, shape = CoupleChatShapes.large)
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}

@Preview
@Composable
fun InvitationCodeMenuPreview() {
    InvitationCodeMenu {
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Filled.Key,
                contentDescription = "",
                tint = LocalContentColor.current
            )
        }
    }
}