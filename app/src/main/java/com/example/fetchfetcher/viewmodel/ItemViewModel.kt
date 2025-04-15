package com.example.fetchfetcher.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.fetchfetcher.repository.ItemRepository
import com.example.fetchfetcher.model.Item
import android.util.Log


class ItemViewModel : ViewModel() {
    private val itemRepository = ItemRepository()

    // LiveData or State will hold the list of items
    private val _items = mutableStateOf<List<Item>>(emptyList())
    val items: State<List<Item>> = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            val fetchedItems = itemRepository.getItems()
            // Filter out items with a null or blank name and group them by listId
            val filteredItems = fetchedItems
                .filter { !it.name.isNullOrBlank() }
                .sortedWith(compareBy({ it.listId }, { it.name }))

            _items.value = filteredItems

            //test logging
            Log.d("DEBUG: ItemViewModel", "Filtered items: ${filteredItems}")
            Log.d("DEBUG", "Fetched items: ${fetchedItems.size}")
        }
    }
}
