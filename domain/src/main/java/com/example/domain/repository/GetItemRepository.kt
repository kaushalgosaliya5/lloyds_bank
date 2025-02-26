package com.example.domain.repository

import com.example.domain.model.Item

interface GetItemRepository {
    suspend fun getItemById(itemId: Int): Item?
}