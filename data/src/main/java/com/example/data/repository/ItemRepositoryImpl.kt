package com.example.data.repository

import com.example.data.remote.ApiService
import com.example.data.state.ResultState
import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ItemRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : ItemRepository {

    private var itemsList: List<Item>? = null

    override suspend fun fetchItemDetails(): List<Item> {
         return withContext(Dispatchers.IO){
              try{
                  itemsList = apiService.getItemDetails()
                  ResultState.Success(itemsList ?: emptyList())
                  itemsList ?: emptyList()
              }catch (exception: Exception){
                  ResultState.Error(exception)
                  emptyList()
              }
         }
    }

    override fun getItemById(itemId: Int): Item? {
        return itemsList?.find{ it.id == itemId }
    }

}