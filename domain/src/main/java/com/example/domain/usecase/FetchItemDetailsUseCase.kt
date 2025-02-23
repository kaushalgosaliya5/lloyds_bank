package com.example.domain.usecase

import com.example.domain.repository.ItemRepository
import javax.inject.Inject

class FetchItemDetailsUseCase @Inject constructor( private val repository: ItemRepository){
     suspend operator fun invoke() = repository.fetchItemDetails()
}