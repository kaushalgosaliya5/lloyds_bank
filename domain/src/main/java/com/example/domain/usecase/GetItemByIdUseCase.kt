package com.example.domain.usecase

import com.example.domain.model.Item
import com.example.domain.repository.GetItemRepository
import com.example.domain.utils.Constant
import com.example.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemByIdUseCase @Inject constructor(private val repository: GetItemRepository) {

    operator fun invoke(itemId: Int): Flow<ResultState<Item>> = flow {
        emit(ResultState.Loading(null))
        try {
            val response = repository.getItemById(itemId)
            emit(ResultState.Success(data = response))
        } catch (e: Exception) {
            emit(ResultState.Error(Constant.ERROR_MESSAGE))
        }
    }
}