package com.example.fetchfetcher.repository

import com.example.fetchfetcher.network.RetrofitInstance
import com.example.fetchfetcher.model.Item

class ItemRepository {
    private val api = RetrofitInstance.api

    suspend fun getItems(): List<Item> {
        return api.getItems()
    }
}
