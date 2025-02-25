package com.example.lloydsbank.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.data.utils.Constant
import com.example.lloydsbank.presentation.ui.ItemDetailsScreen
import com.example.lloydsbank.presentation.ui.ItemListScreen
import com.example.lloydsbank.presentation.viewmodel.ItemListViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Constant.ITEM_LIST) {

        composable(Constant.ITEM_LIST) { backStackEntry ->
            val viewModel: ItemListViewModel = hiltViewModel(backStackEntry)
            ItemListScreen(viewModel = viewModel, navController = navController)
        }
        composable("${Constant.ITEM_DETAILS}/{${Constant.ITEM_ID}}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString(Constant.ITEM_ID) ?: ""
            ItemDetailsScreen(itemId = itemId, navController = navController)
        }
    }
}