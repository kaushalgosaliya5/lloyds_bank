package com.example.lloydsbank.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lloydsbank.presentation.viewmodel.SplashViewModel

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel(), onTimeOut:() -> Unit){

     val isTimeOutCompleted by viewModel.isTimeoutCompleted.collectAsState()

     LaunchedEffect(isTimeOutCompleted) {
           if(isTimeOutCompleted){
                onTimeOut()
           }
     }

     Box( modifier =  Modifier
         .fillMaxSize()
         .background(Color.Gray),
          contentAlignment = Alignment.Center
     ){
          Text( text = " Lloyds Banking App ", style = MaterialTheme.typography.headlineLarge,
              color = Color.White)
    }

}