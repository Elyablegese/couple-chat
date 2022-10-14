package com.iia.couplechat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TestScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
        Text(text = "Display Large", style = MaterialTheme.typography.displayLarge)
        Text(text = "Display Medium", style = MaterialTheme.typography.displayMedium)
        Text(text = "Display Small", style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Title Large", style = MaterialTheme.typography.titleLarge)
        Text(text = "Title Medium", style = MaterialTheme.typography.titleMedium)
        Text(text = "Title Small", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Body Large", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Body Medium", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Body Small", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Label Large", style = MaterialTheme.typography.labelLarge)
        Text(text = "Label Medium", style = MaterialTheme.typography.labelMedium)
        Text(text = "Label Small", style = MaterialTheme.typography.labelSmall)
    }
}

@Preview
@Composable
fun TestScreenPreview() {
    TestScreen()
}