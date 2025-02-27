package com.example.domain.usecase

import com.example.domain.model.Item
import com.example.domain.repository.GetItemRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetItemByIdUseCaseTest{

    private val getItemRepository : GetItemRepository = mock()
    private val itemId = 0;

    @Test
    fun test_success_item_name() = runTest {
        `when`(getItemRepository.getItemById(itemId))
            .thenReturn(getItem())

        val useCase = GetItemByIdUseCase(getItemRepository)
        val response = useCase.invoke(itemId)

        assertEquals(getItem().name, response.last().data?.name)
    }

    @Test
    fun test_success_description_not_null() = runTest {
        `when`(getItemRepository.getItemById(itemId))
            .thenReturn(getItem())

        val useCase = GetItemByIdUseCase(getItemRepository)
        val response = useCase.invoke(itemId)

        assertNotNull(response.last().data?.description)
    }

    @Test
    fun test_success_image_url_not_null() = runTest {
        `when`(getItemRepository.getItemById(itemId))
            .thenReturn(getItem())

        val useCase = GetItemByIdUseCase(getItemRepository)
        val response = useCase.invoke(itemId)

        assertNotNull(response.last().data?.imageUrl)
    }

    private fun getItem(): Item {
        return Item(
                id = 1,
                name = "apple",
                imageUrl = "https://apple.jpg",
                description = "apple is red",
                price = "100"
         )
    }

}