package com.example.lloydsbank.presentation.state

import com.example.domain.model.Item

data class GetItemState(
    var isLoading : Boolean = false,
    var data : Item? = null,
    var error : String = ""
)