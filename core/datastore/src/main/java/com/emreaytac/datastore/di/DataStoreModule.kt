package com.emreaytac.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.emreaytac.datastore.UserPreferences
import com.emreaytac.datastore.UserPreferencesSerializer
import com.emreaytac.di.Dispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Dispatchers.Main
    fun provideDispatcherMain() = kotlinx.coroutines.Dispatchers.Main

    @Provides
    @Dispatchers.IO
    fun provideDispatcherIO() = kotlinx.coroutines.Dispatchers.IO

    @Provides
    @Dispatchers.Default
    fun provideDispatcherDefault() = kotlinx.coroutines.Dispatchers.Default

    @Provides
    @Singleton
    @ApplicationScope
    fun providesCoroutineScope(
        @Dispatchers.Default dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)


    @Provides
    @Singleton
    internal fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatchers.IO ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        userPreferencesSerializer: UserPreferencesSerializer,
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher)
        ) {
            context.dataStoreFile("user_preferences.pb")
        }
}