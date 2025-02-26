package com.example.data.remote

import com.example.data.model.ItemDTO
import com.example.data.utils.Constant
import retrofit2.http.GET

interface ApiService {
    @GET(Constant.API_ID)
    suspend fun getItemDetails(): List<ItemDTO>

    @GET(Constant.API_ID)
    suspend fun getItemById(itemId: Int): ItemDTO
}