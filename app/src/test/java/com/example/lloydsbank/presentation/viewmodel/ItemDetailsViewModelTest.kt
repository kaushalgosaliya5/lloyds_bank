package com.example.lloydsbank.presentation.viewmodel

import com.example.data.utils.Constant
import com.example.domain.model.Item
import com.example.domain.usecase.GetItemByIdUseCase
import com.example.domain.utils.ResultState
import junit.framework.TestCase
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

class ItemDetailsViewModelTest {

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule()

    private val getItemByIdUseCase: GetItemByIdUseCase = mock()
    private val itemId = 1

    @Test
    fun test_success() {
        `when`(getItemByIdUseCase.invoke(itemId))
            .thenReturn(flowOf(ResultState.Success(data = getItem())))

        val viewModel = ItemDetailsViewModel(getItemByIdUseCase)
        viewModel.fetchItem(itemId.toString())
        TestCase.assertEquals(getItem().name, viewModel.item.value.data?.name)
    }

    @Test
    fun test_fail() {
        `when`(getItemByIdUseCase.invoke(itemId))
            .thenReturn(flowOf(ResultState.Error(Constant.ERROR_MESSAGE)))

        val viewModel = ItemDetailsViewModel(getItemByIdUseCase)
        viewModel.fetchItem(itemId.toString())
        TestCase.assertEquals(Constant.ERROR_MESSAGE, viewModel.item.value.error)
    }

    class MainDispatcherRule(val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()) :
        TestWatcher() {
        override fun starting(description: Description?) {
            Dispatchers.setMain(UnconfinedTestDispatcher())
        }

        override fun finished(description: Description?) {
            Dispatchers.resetMain()
        }
    }

    private fun getItem(): Item {
        return Item(
            id = 1,
            name = "banana",
            imageUrl = "https://banana.jpg",
            description = "banana color is yellow",
            price = "40"
        )
    }
}