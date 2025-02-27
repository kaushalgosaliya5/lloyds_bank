package com.example.data.mappers

import com.example.data.model.ItemDTO
import com.example.domain.model.Item
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class MapperTest {

    @Test
    fun test_success_item_list_to_item_list_dto_object() = runTest {

        val item = getItemList()
        val itemDTO = getItemDTOList()

        assertEquals(item, itemDTO.toDomain())
    }

    @Test
    fun test_success_name() = runTest {

        val item = getItemList()
        val itemDTO = getItemDTOList()

        assertEquals(item.get(0).name, itemDTO.toDomain().get(0).name)
    }

    @Test
    fun test_success_price() = runTest {

        val item = getItemList()
        val itemDTO = getItemDTOList()

        assertEquals(item.get(1).price, itemDTO.toDomain().get(1).price)
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

    private fun getItemDTOList(): List<ItemDTO> {
        return listOf(
            ItemDTO(
                id = 1,
                name = "apple",
                imageUrl = "https://apple.jpg",
                description = "apple is red",
                price = "100"
            ),
            ItemDTO(
                id = 2,
                name = "banana",
                imageUrl = null,
                description = "banana is yellow",
                price = "40"
            )
        )
    }

}