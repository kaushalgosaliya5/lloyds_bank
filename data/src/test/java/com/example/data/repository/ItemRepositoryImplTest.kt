package com.example.data.repository

import com.example.data.remote.ApiService
import com.example.domain.model.Item
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ItemRepositoryImplTest{

    private val apiService : ApiService = mock()

    @Test
    fun test_item_count_success() = runTest {

        `when`(apiService.getItemDetails())
            .thenReturn(getItemList())

        val repo = ItemRepositoryImpl(apiService)
        val response = repo.fetchItemDetails()

       assertEquals(getItemList().size,response.size)
    }

    @Test
    fun test_item_name_success() = runTest {

        `when`(apiService.getItemDetails())
            .thenReturn(getItemList())

        val repo = ItemRepositoryImpl(apiService)
        val response = repo.fetchItemDetails()

        assertEquals("banana",response.get(0).name)
    }


    private fun getItemList() : List<Item> {
         return listOf(Item(
             id = 0,
             name = "banana",
             imageUrl = "https://apple.jpg",
             description = "apple is red",
             price = "100"
         ))
    }
}