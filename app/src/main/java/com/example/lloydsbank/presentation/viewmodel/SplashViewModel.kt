package com.example.lloydsbank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

      private val _isTimeoutCompleted = MutableStateFlow(false)
      val isTimeoutCompleted: StateFlow<Boolean> = _isTimeoutCompleted

      init{
          startSplashTimeout()
      }

      private fun startSplashTimeout(){
            viewModelScope.launch {
                  delay(3000)
                  _isTimeoutCompleted.value = true
            }
      }


}