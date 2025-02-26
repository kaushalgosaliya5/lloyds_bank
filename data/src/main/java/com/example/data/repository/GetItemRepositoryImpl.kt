package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.remote.ApiService
import com.example.domain.model.Item
import com.example.domain.repository.GetItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetItemRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GetItemRepository {

    private var item: Item? = null

    override suspend fun getItemById(itemId: Int): Item? {
        return withContext(Dispatchers.IO) {
            item = apiService.getItemDetails().toDomain()?.find { it.id == itemId }
            item
        }
    }

}