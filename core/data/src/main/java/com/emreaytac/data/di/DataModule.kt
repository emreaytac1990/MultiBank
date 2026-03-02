package com.emreaytac.data.di

import com.emreaytac.data.repository.StockRepositoryImpl
import com.emreaytac.data.repository.UserDataRepositoryImpl
import com.emreaytac.domain.repository.StockRepository
import com.emreaytac.domain.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsUserDataRepository(
        userDataRepository: UserDataRepositoryImpl,
    ): UserDataRepository

    @Binds
    internal abstract fun bindsStockRepository(
        stockRepository: StockRepositoryImpl,
    ): StockRepository

}