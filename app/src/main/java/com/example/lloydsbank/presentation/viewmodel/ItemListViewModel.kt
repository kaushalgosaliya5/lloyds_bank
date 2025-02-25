package com.example.lloydsbank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Constant
import com.example.domain.model.Item
import com.example.domain.usecase.FetchItemDetailsUseCase
import com.example.lloydsbank.presentation.state.ItemListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ItemListViewModel @Inject constructor( private val fetchItemListUseCase : FetchItemDetailsUseCase) : ViewModel() {

      private val _items = MutableStateFlow<List<Item>>(emptyList())

      private val _uiState = MutableStateFlow<ItemListState>(ItemListState.Loading)
      val uiState: StateFlow<ItemListState> = _uiState

      init {
          fetchItems()
      }

       private fun fetchItems(){
                viewModelScope.launch {
                    _uiState.value = try{
                        _items.value = fetchItemListUseCase.invoke()
                           ItemListState.Success(_items.value)
                    }catch ( e: Exception){
                         ItemListState.Error(Constant.ERROR_MESSAGE)
                    }
                }
       }
}