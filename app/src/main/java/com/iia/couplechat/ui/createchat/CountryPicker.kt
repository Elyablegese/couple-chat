package com.iia.couplechat.ui.createchat

import androidx.compose.animation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iia.couplechat.R
import com.iia.couplechat.data.model.Country
import com.iia.couplechat.ui.theme.CoupleChatShapes

@ExperimentalAnimationApi
@Composable
fun CountryPicker(countryName: String, url: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = CoupleChatShapes.medium
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if (countryName.isEmpty()) {
                Text(text = "Country", modifier = Modifier.weight(1f))
            } else {
                AnimatedContent(targetState = countryName,
                    modifier = Modifier.weight(1f),
                    transitionSpec = {
                        if (targetState > initialState)
                            slideInVertically { -it } with slideOutVertically { it }
                        else
                            slideInVertically { it } with slideOutVertically { -it }
                    }
                ) { shortName ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current).data(url)
                                .crossfade(true).build(),
                            contentDescription = "$countryName Flag",
                            placeholder = painterResource(id = R.drawable.placeholder_flag),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = shortName)
                    }
                }
            }
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "")
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun CountryPickerPreview() {
    CountryPicker(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        countryName = "Jamaica",
        url = "https://flagcdn.com/24x18/jm.png"
    )
}