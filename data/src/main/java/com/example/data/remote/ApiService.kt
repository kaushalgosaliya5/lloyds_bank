package com.example.data.remote

import com.example.domain.model.Item
import retrofit2.http.GET

interface ApiService {
        @GET("9427c14a054baf569196")
        suspend fun getItemDetails() : List<Item>
}