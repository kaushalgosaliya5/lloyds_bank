package com.example.lloydsbank.presentation.state

import com.example.domain.model.Item

sealed class ItemListState {
    data object Loading: ItemListState()
    data class Success(val items: List<Item>) : ItemListState()
    data class Error(val message:String) : ItemListState()
}