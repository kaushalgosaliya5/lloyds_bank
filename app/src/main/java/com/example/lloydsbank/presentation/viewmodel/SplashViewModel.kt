package com.example.lloydsbank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _isTimeoutCompleted = MutableStateFlow(true)
    val isTimeoutCompleted = _isTimeoutCompleted.asStateFlow()

    init {
        startSplashTimeout()
    }

    private fun startSplashTimeout() {
        viewModelScope.launch {
            delay(3.seconds)
            _isTimeoutCompleted.value = false
        }
    }

}