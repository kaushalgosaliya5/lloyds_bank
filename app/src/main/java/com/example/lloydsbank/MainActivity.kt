package com.example.lloydsbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.lloydsbank.presentation.AppNavHost
import com.example.lloydsbank.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        installSplashScreen()
            .apply {
                setKeepOnScreenCondition{
                     viewModel.isTimeoutCompleted.value
                }
            }

        setContent {
           val navController = rememberNavController()
            AppNavHost(navController = navController)
        }
    }
}












