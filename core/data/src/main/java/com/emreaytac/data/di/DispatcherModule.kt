package com.emreaytac.data.di

import com.emreaytac.di.Dispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    @Dispatchers.Main
    fun provideDispatcherMain() = kotlinx.coroutines.Dispatchers.Main

    @Provides
    @Dispatchers.IO
    fun provideDispatcherIO() = kotlinx.coroutines.Dispatchers.IO

    @Provides
    @Dispatchers.Default
    fun provideDispatcherDefault() = kotlinx.coroutines.Dispatchers.Default

    // TODO: Use it
    /*@Provides
    @BaseUrl
    fun provideBaseUrl() = BuildConfig.BASE_URL*/

}