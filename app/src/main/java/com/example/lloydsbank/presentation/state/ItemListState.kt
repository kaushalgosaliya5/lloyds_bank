package com.example.lloydsbank.presentation.state

import com.example.domain.model.Item

data class ItemListState(
      var isLoading : Boolean = false,
      var data : List<Item>? = emptyList(),
      var error : String = ""
)