package com.example.domain.usecase

import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import com.example.domain.utils.Constant
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchItemDetailsUseCase @Inject constructor(private val repository: ItemRepository) {

    operator fun invoke(): Flow<Resource<List<Item>>> = flow {
        emit(Resource.Loading(null))
        try {
            val response = repository.fetchItemDetails()
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(Constant.ERROR_MESSAGE))
        }
    }

}