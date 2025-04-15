package com.example.fetchfetcher.network

import com.example.fetchfetcher.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}
