package com.example.lloydsbank.presentation.viewmodel

import com.example.data.utils.Constant
import com.example.domain.model.Item
import com.example.domain.usecase.FetchItemDetailsUseCase
import com.example.domain.utils.ResultState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class ItemListViewModelTest{

     @get:Rule(order = 1)
     val mainDispatcherRule = MainDispatcherRule()

     private val fetchItemDetailsUseCase : FetchItemDetailsUseCase = mock()

     @Test
     fun test_success(){
          `when`(fetchItemDetailsUseCase.invoke())
              .thenReturn(flowOf(ResultState.Success(data = getItemList())))

         val viewModel = ItemListViewModel(fetchItemDetailsUseCase)
         viewModel.fetchItems()
         assertEquals(getItemList().get(0).name,viewModel.uiState.value.data?.get(0)?.name)
     }

    @Test
    fun test_fail(){
        `when`(fetchItemDetailsUseCase.invoke())
            .thenReturn(flowOf(ResultState.Error(Constant.ERROR_MESSAGE)))

        val viewModel = ItemListViewModel(fetchItemDetailsUseCase)
        viewModel.fetchItems()
        assertEquals(Constant.ERROR_MESSAGE,viewModel.uiState.value.error)
    }

    class MainDispatcherRule(val testDispatcher : TestDispatcher = UnconfinedTestDispatcher()) : TestWatcher(){
        override fun starting(description: Description?) {
            Dispatchers.setMain(UnconfinedTestDispatcher())
        }

        override fun finished(description: Description?) {
            Dispatchers.resetMain()
        }
    }

    private fun getItemList(): List<Item> {
        return listOf(
            Item(
                id = 1,
                name = "apple",
                imageUrl = "https://apple.jpg",
                description = "apple is red",
                price = "100"
            )
        )
    }
}