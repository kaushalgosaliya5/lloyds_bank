package com.example.domain.usecase

import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class FetchItemDetailsUseCaseTest {

    private val itemRepository: ItemRepository = mock()

    @Test
    fun test_success_item_name() = runTest {
        `when`(itemRepository.fetchItemDetails())
            .thenReturn(getItemList())

        val useCase = FetchItemDetailsUseCase(itemRepository)
        val response = useCase.invoke()

        assertEquals(getItemList().get(1).name, response.last().data?.get(1)?.name)
    }

    @Test
    fun test_success_description_not_null() = runTest {
        `when`(itemRepository.fetchItemDetails())
            .thenReturn(getItemList())

        val useCase = FetchItemDetailsUseCase(itemRepository)
        val response = useCase.invoke()

        assertNotNull(response.last().data?.get(0)?.description)
    }

    @Test
    fun test_success_image_url_null() = runTest {
        `when`(itemRepository.fetchItemDetails())
            .thenReturn(getItemList())

        val useCase = FetchItemDetailsUseCase(itemRepository)
        val response = useCase.invoke()

        assertNull(response.last().data?.get(1)?.imageUrl)
    }

    private fun getItemList(): List<Item> {
        return listOf(
            Item(
                id = 1,
                name = "apple",
                imageUrl = "https://apple.jpg",
                description = "apple is red",
                price = "100"
            ),
            Item(
                id = 2,
                name = "banana",
                imageUrl = null,
                description = "banana is yellow",
                price = "40"
            )
        )
    }
}