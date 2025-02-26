package com.example.data.di

import com.example.data.utils.Constant
import com.example.data.remote.ApiService
import com.example.data.repository.GetItemRepositoryImpl
import com.example.data.repository.ItemRepositoryImpl
import com.example.domain.repository.GetItemRepository
import com.example.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideItemRepository(apiService: ApiService): ItemRepository {
        return ItemRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun privideGetItemRepository(apiService: ApiService): GetItemRepository {
        return GetItemRepositoryImpl(apiService)
    }
}