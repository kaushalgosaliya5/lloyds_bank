package com.example.domain.usecase

import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemByIdUseCase @Inject constructor(private val repository: ItemRepository) {
    suspend operator fun invoke(itemId: Int): Item? {
        return repository.getItemById(itemId)
    }
}