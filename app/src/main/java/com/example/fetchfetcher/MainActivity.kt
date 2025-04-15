package com.example.fetchfetcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fetchfetcher.ui.screens.ItemListScreen
import com.example.fetchfetcher.ui.components.ItemRow

import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchfetcher.model.Item
import com.example.fetchfetcher.ui.components.AppBar
import com.example.fetchfetcher.ui.theme.FetchFetcherTheme
import com.example.fetchfetcher.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {
    private val itemViewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchFetcherTheme {
                MainScreen(itemViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(itemViewModel: ItemViewModel) {
    Scaffold(
        topBar = { AppBar() },
        content = { paddingValues ->
            ItemListScreen(itemViewModel, paddingValues)
        }
    )
}
