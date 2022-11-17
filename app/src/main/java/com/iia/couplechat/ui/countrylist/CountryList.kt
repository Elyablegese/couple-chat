package com.iia.couplechat.ui.countrylist

import android.graphics.BitmapFactory
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iia.couplechat.R
import com.iia.couplechat.ui.navigation.CreateChatNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator

@ExperimentalMaterial3Api
@CreateChatNavGraph
@Destination
@Composable
fun CountryList(
    resultNavigator: ResultBackNavigator<Int> = EmptyResultBackNavigator(),
    countryListViewModel: CountryListViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Chose a Country") })
    }) { paddingValues->
        val uiState by countryListViewModel.uiState.collectAsState()

        if (uiState.result != 0) {
            LaunchedEffect(uiState.result) {
                resultNavigator.navigateBack(uiState.result)
            }
        }
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp)
            ) {
                items(uiState.countries) { country ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                countryListViewModel.handleEvent(
                                    CountryListEvent.OnResultBack(
                                        country.countryCode
                                    )
                                )
                            }
                            .padding(vertical = 8.dp)
                    ) {
                        val assetManager = LocalContext.current.assets
                        val inputStream = assetManager.open(country.url)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(bitmap)
                                .crossfade(true)
                                .build(),
                            contentDescription = "${country.name} Flag",
                            placeholder = painterResource(id = R.drawable.placeholder_flag),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(16.dp)
                                .height(12.dp)
                        )
                        Text(text = country.name, modifier = Modifier.weight(1f))
                        Text(
                            text = "+${country.countryCode}",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun CountryListPreview() {
    CountryList()
}