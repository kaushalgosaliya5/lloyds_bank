package com.example.data.mappers

import com.example.data.model.ItemDTO
import com.example.domain.model.Item

fun List<ItemDTO>.toDomain() :List<Item>{
    return map{
        Item(
            id = it.id,
            name = it.name,
            imageUrl = it.imageUrl,
            price = it.price,
            description = it.description
        )
    }
}