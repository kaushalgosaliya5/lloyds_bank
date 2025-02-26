package com.example.lloydsbank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Constant
import com.example.domain.usecase.GetItemByIdUseCase
import com.example.domain.utils.ResultState
import com.example.lloydsbank.presentation.state.GetItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(private val getItemByIdUseCase: GetItemByIdUseCase) :
    ViewModel() {

    private val _item = MutableStateFlow<GetItemState>(GetItemState())
    val item: StateFlow<GetItemState> = _item

    fun fetchItem(itemId: String) {
        getItemByIdUseCase(itemId.toInt()).onEach {
            when (it) {
                is ResultState.Loading -> {
                    _item.value = GetItemState(isLoading = true)
                }

                is ResultState.Success -> {
                    _item.value = GetItemState(data = it.data)
                }

                is ResultState.Error -> {
                    _item.value = GetItemState(error = it.message ?: Constant.ERROR_MESSAGE)
                }
            }
        }.launchIn(viewModelScope)
    }
}