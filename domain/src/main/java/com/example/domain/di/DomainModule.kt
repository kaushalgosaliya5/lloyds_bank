package com.example.domain.di

import com.example.domain.repository.GetItemRepository
import com.example.domain.repository.ItemRepository
import com.example.domain.usecase.FetchItemDetailsUseCase
import com.example.domain.usecase.GetItemByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideFetchItemDetailsUseCase(repository: ItemRepository): FetchItemDetailsUseCase {
        return FetchItemDetailsUseCase(repository)
    }

    @Provides
    fun provideGetItemByIdUseCase(repository: GetItemRepository): GetItemByIdUseCase {
        return GetItemByIdUseCase(repository)
    }

}