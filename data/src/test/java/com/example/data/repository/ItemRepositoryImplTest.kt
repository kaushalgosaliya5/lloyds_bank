package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.model.ItemDTO
import com.example.data.remote.ApiService
import com.example.domain.model.Item
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ItemRepositoryImplTest {

    private val apiService: ApiService = mock()

    @Test
    fun test_success_item_count() = runTest {

        `when`(apiService.getItemDetails())
            .thenReturn(getItemList())

        val repo = ItemRepositoryImpl(apiService)
        val response = repo.fetchItemDetails()

        assertEquals(getItemList().size, response.size)
    }

    @Test
    fun test_success_item_name() = runTest {

        `when`(apiService.getItemDetails())
            .thenReturn(getItemList())

        val repo = ItemRepositoryImpl(apiService)
        val response = repo.fetchItemDetails()

        assertEquals("apple", response.get(0).name)
    }

    @Test
    fun test_success_price_not_equal() = runTest {

        `when`(apiService.getItemDetails())
            .thenReturn(getItemList())

        val repo = ItemRepositoryImpl(apiService)
        val response = repo.fetchItemDetails()

        assertNotEquals("40", response.get(0).price)
    }

    @Test
    fun test_success_empty_list() = runTest {

        `when`(apiService.getItemDetails())
            .thenReturn(emptyList())

        val repo = ItemRepositoryImpl(apiService)
        val response = repo.fetchItemDetails()

        assertEquals(0, response.size)
    }

    @Test
    fun test_success_image_url_null_check() = runTest {

        `when`(apiService.getItemDetails())
            .thenReturn(getItemList())

        val repo = ItemRepositoryImpl(apiService)
        val response = repo.fetchItemDetails()

        assertNotNull(response.get(0).imageUrl)
    }

    private fun getItemList(): List<ItemDTO> {
        return listOf(
            ItemDTO(
                id = 1,
                name = "apple",
                imageUrl = "https://apple.jpg",
                description = "apple is red",
                price = "100"
            )
        )
    }
}