package com.example.domain.usecase

import com.example.domain.model.Item
import com.example.domain.repository.GetItemRepository
import com.example.domain.repository.ItemRepository
import com.example.domain.utils.Constant
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemByIdUseCase @Inject constructor(private val repository: GetItemRepository) {

    operator fun invoke(itemId: Int): Flow<Resource<Item>> = flow {
        emit(Resource.Loading(null))
        try {
            val response = repository.getItemById(itemId)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(Constant.ERROR_MESSAGE))
        }
    }
}