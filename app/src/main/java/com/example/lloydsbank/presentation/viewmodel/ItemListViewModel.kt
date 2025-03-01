package com.example.lloydsbank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Constant
import com.example.domain.usecase.FetchItemDetailsUseCase
import com.example.domain.utils.ResultState
import com.example.lloydsbank.presentation.state.ItemListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ItemListViewModel @Inject constructor(private val fetchItemListUseCase: FetchItemDetailsUseCase) :
    ViewModel() {

    private val _uiState = MutableSharedFlow<ItemListState>(0)
    val uiState: SharedFlow<ItemListState> = _uiState

    fun fetchItems() {
        fetchItemListUseCase().onEach {
            when (it) {
                is ResultState.Loading -> {
                    _uiState.emit(ItemListState(isLoading = true))
                }

                is ResultState.Success -> {
                    _uiState.emit(ItemListState(data = it.data))
                }

                is ResultState.Error -> {
                    _uiState.emit(ItemListState(error = it.message ?: Constant.ERROR_MESSAGE))
                }
            }

        }.launchIn(viewModelScope)
    }
}