package com.example.data.remote

import com.example.domain.model.Item
import retrofit2.http.GET

interface ApiService {
        @GET("866592d4df655060f42c")
        suspend fun getItemDetails() : List<Item>
}