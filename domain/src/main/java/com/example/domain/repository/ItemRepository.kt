package com.example.domain.repository

import com.example.domain.model.Item

interface ItemRepository {
    suspend fun fetchItemDetails() : List<Item>
    fun getItemById(itemId: Int): Item?
}