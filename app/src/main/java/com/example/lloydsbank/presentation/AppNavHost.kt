package com.example.lloydsbank.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lloydsbank.presentation.ui.ItemDetailsScreen
import com.example.lloydsbank.presentation.ui.ItemListScreen
import com.example.lloydsbank.presentation.viewmodel.ItemListViewModel

@Composable
fun AppNavHost(navController: NavHostController){
       NavHost(navController, startDestination = "item_list") {
           composable("item_list") { backStackEntry ->
                val viewModel : ItemListViewModel = hiltViewModel(backStackEntry)
                ItemListScreen(viewModel = viewModel, navController = navController)
           }
           composable("itemDetails/{itemId}"){ backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
                ItemDetailsScreen(itemId = itemId, navController = navController)
           }
       }
}