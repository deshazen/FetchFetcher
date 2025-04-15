package com.example.fetchfetcher.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fetchfetcher.model.Item

@Composable
fun ItemRow(item: Item) {
    Text(
        text = item.name ?: "Unknown",
        style = MaterialTheme.typography.bodyMedium
    )
}