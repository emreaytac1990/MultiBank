package com.emreaytac.websocket.di

import com.emreaytac.websocket.SocketManager
import com.emreaytac.websocket.internal.SocketManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(WebSockets){
                pingInterval = 5_000
            }

            install(Logging) {
                level = LogLevel.INFO
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }

            engine {
                config {
                    connectTimeout(15, TimeUnit.SECONDS)
                    readTimeout(0, TimeUnit.SECONDS)
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideSocketManager(client: HttpClient): SocketManager{
        return SocketManagerImpl(client)
    }
}