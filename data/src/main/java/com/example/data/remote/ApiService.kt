package com.example.data.remote

import com.example.data.model.ItemDTO
import com.example.data.utils.Constant
import com.example.domain.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET(Constant.API_ID)
    suspend fun getItemDetails(): List<ItemDTO>
}