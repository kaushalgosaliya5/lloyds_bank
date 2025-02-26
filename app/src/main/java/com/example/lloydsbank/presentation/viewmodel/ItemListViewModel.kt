package com.example.lloydsbank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Constant
import com.example.domain.usecase.FetchItemDetailsUseCase
import com.example.domain.utils.Resource
import com.example.lloydsbank.presentation.state.ItemListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ItemListViewModel @Inject constructor(private val fetchItemListUseCase: FetchItemDetailsUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<ItemListState>(ItemListState())
    val uiState: StateFlow<ItemListState> = _uiState

    init {
        fetchItems()
    }

    fun fetchItems() {
        fetchItemListUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _uiState.value = ItemListState(isLoading = true)
                }

                is Resource.Success -> {
                    _uiState.value = ItemListState(data = it.data)
                }

                is Resource.Error -> {
                    _uiState.value = ItemListState(error = it.message ?: Constant.ERROR_MESSAGE)
                }
            }

        }.launchIn(viewModelScope)
    }
}