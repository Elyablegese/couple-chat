package com.iia.couplechat.ui.welcome

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.couplechat.R
import com.iia.couplechat.ui.destinations.CreateChatPageDestination
import com.iia.couplechat.ui.theme.CoupleChatShapes
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@RootNavGraph(start = true)
@Destination
@Composable
fun WelcomePage(navigator: DestinationsNavigator) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = stringResource(R.string.welcome_description),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )
            FilledTonalButton(
                modifier = Modifier.fillMaxWidth(.65f),
                onClick = {
                    navigator.navigate(CreateChatPageDestination())
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = CoupleChatShapes.medium
            ) {
                Text(
                    text = stringResource(id = R.string.create_chat_button),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(.65f),
                onClick = {

                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                shape = CoupleChatShapes.medium,
                border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(id = R.string.join_chat_button),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun CreateChatPagePreview() {
    WelcomePage(navigator = EmptyDestinationsNavigator)
}