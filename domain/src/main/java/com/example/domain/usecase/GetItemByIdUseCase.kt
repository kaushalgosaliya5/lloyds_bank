package com.example.domain.usecase

import com.example.domain.model.Item
import com.example.domain.repository.GetItemRepository
import com.example.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemByIdUseCase @Inject constructor(private val repository: GetItemRepository) {
    suspend operator fun invoke(itemId: Int): Item? {
        return repository.getItemById(itemId)
    }
}