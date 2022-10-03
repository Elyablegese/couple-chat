package com.iia.couplechat.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import countries

@ExperimentalMaterial3Api
@Destination
@Composable
fun CountryList(
    resultNavigator: ResultBackNavigator<String> = EmptyResultBackNavigator()
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Chose a Country") })
    }) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                items(countries) { country ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth().clickable {
                            resultNavigator.navigateBack(country.shortName)
                        }.padding(vertical = 8.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current).data(country.url)
                                .crossfade(true).build(),
                            contentDescription = "${country.name} Flag",
                            placeholder = painterResource(id = R.drawable.placeholder_flag),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(24.dp)
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