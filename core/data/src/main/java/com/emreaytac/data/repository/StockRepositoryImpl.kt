package com.emreaytac.data.repository

import com.emreaytac.di.Dispatchers
import com.emreaytac.websocket.SocketManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(socketManager: SocketManager, @Dispatchers.IO private val dispatcher: CoroutineDispatcher) {
}