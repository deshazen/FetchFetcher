package com.example.fetchfetcher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fetchfetcher.ui.components.ItemRow
import com.example.fetchfetcher.ui.theme.FetchPurple
import com.example.fetchfetcher.viewmodel.ItemViewModel

@Composable
fun ItemListScreen(
    viewModel: ItemViewModel,
    paddingValues: PaddingValues
) {
    val items = viewModel.items.value
    val groupedItems = items.groupBy { it.listId }.toSortedMap()
    val listIds = groupedItems.keys.toList()

    var currentIndex by rememberSaveable { mutableStateOf(0) }

    // get current listid and items based on currentIndex
    val currentListId = listIds.getOrNull(currentIndex)
    val currentItems = if (currentListId != null) {
        groupedItems[currentListId] ?: emptyList()
    } else { emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Text(
            text = "Group: $currentListId",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(currentItems) { item ->
                ItemRow(item)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0
            ) {
                Text("Previous")
            }
            Button(
                onClick = { if (currentIndex < listIds.size - 1) currentIndex++ },
                enabled = currentIndex < listIds.size - 1
            ) {
                Text("Next")
            }
        }
    }
}