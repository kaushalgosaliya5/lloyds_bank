package com.example.data.repository

import com.example.data.remote.ApiService
import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class ResultState<out T>{
     data class Success<out T>(val data:T) : ResultState<T>()
     data class Error(val exception: Throwable) : ResultState<Nothing>()
     object  Loading: ResultState<Nothing>()
}

class ItemRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : ItemRepository {

    private var cachedItems: List<Item>? = null

    override suspend fun fetchItemDetails(): List<Item> {
         return withContext(Dispatchers.IO){
              try{
                  cachedItems = apiService.getItemDetails()
                  ResultState.Success(cachedItems ?: emptyList())
                  cachedItems ?: emptyList()
              }catch (exception: Exception){
                  ResultState.Error(exception)
                  emptyList()
              }
         }
    }

    override fun getItemById(itemId: Int): Item? {
        return cachedItems?.find{ it.id == itemId }
    }

}